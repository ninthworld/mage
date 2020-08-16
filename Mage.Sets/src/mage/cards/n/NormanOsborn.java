package mage.cards.n;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfEndStepTriggeredAbility;
import mage.abilities.common.DiesCreatureTriggeredAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.MorbidCondition;
import mage.abilities.effects.common.DamageControllerEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.constants.*;
import mage.abilities.keyword.ExploitAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.watchers.common.CreaturesDiedWatcher;
import mage.watchers.common.MorbidWatcher;

/**
 *
 * @author NinthWorld
 */
public final class NormanOsborn extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
    }

    public NormanOsborn(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.MERCHANT);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.g.GreenGoblin.class;

        // Exploit
        this.addAbility(new ExploitAbility());

        // Whenever a creature you control dies, Norman Osborn deals 1 damage to you and you draw a card.
        Ability ability = new DiesCreatureTriggeredAbility(new DamageControllerEffect(1), false, filter);
        ability.addEffect(new DrawCardSourceControllerEffect(1));
        this.addAbility(ability);

        // At the beginning of each end step, if two or more creatures died this turn, transform Norman Osborn.
        this.addAbility(new BeginningOfEndStepTriggeredAbility(Zone.BATTLEFIELD, new TransformSourceEffect(true),
                TargetController.ANY, NormanOsbornCondition.instance, true));
    }

    private NormanOsborn(final NormanOsborn card) {
        super(card);
    }

    @Override
    public NormanOsborn copy() {
        return new NormanOsborn(this);
    }
}

enum NormanOsbornCondition implements Condition {

    instance;

    @Override
    public boolean apply(Game game, Ability source) {
        CreaturesDiedWatcher watcher = game.getState().getWatcher(CreaturesDiedWatcher.class);
        return watcher != null && watcher.getAmountOfCreaturesDiedThisTurn() >= 2;
    }

    @Override
    public String toString() {
        return "if two or more creatures died this turn";
    }

}
