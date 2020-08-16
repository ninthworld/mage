
package mage.abilities.keyword;

import mage.abilities.Ability;
import mage.abilities.StaticAbility;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.effects.common.cost.SpellsCostModificationThatTargetSourceEffect;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.FilterSpell;
import mage.game.Game;
import mage.game.combat.CombatGroup;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.Target;
import mage.util.CardUtil;

import java.util.Collection;

/**
 * @author NinthWorld
 */
public class ArsenalAbility extends StaticAbility {

    private static final FilterCard filter = new FilterCard();

    static {
        filter.add(SubType.AURA.getPredicate());
    }

    private int value;

    public ArsenalAbility(int value) {
        super(Zone.BATTLEFIELD, new SpellsCostModificationThatTargetSourceEffect(value, filter, TargetController.YOU));
        this.addEffect(new ArsenalEquipEffect(value));
        this.value = value;
    }

    public ArsenalAbility(final ArsenalAbility ability) {
        super(ability);
        this.value = ability.value;
    }

    @Override
    public ArsenalAbility copy() {
        return new ArsenalAbility(this);
    }

    @Override
    public String getRule() {
        return "Arsenal " + value + " <i>(Aura spells you cast and equip abilities you activate that target this creature cost {" + value + "} less to cast or activate.)</i>";
    }
}

class ArsenalEquipEffect extends CostModificationEffectImpl {

    private int value;

    public ArsenalEquipEffect(int value) {
        super(Duration.Custom, Outcome.Benefit, CostModificationType.REDUCE_COST);
        staticText = "equip abilities you activate that target this creature cost {" + value + "} less to activate";
        this.value = value;
    }

    public ArsenalEquipEffect(final ArsenalEquipEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        CardUtil.reduceCost(abilityToModify, this.value);
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (abilityToModify instanceof EquipAbility
                && abilityToModify.isControlledBy(source.getControllerId())) {
            if (game != null && game.inCheckPlayableState()) {
                return !abilityToModify.getTargets().isEmpty() &&
                        abilityToModify.getTargets().get(0).canTarget(source.getSourceId(), abilityToModify, game);
            } else {
                return abilityToModify
                        .getTargets()
                        .stream()
                        .map(Target::getTargets)
                        .flatMap(Collection::stream)
                        .anyMatch(source.getSourceId()::equals);
            }

        }
        return false;
    }

    @Override
    public ArsenalEquipEffect copy() {
        return new ArsenalEquipEffect(this);
    }
}
