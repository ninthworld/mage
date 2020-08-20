package mage.cards.d;

import java.util.*;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.AttacksEachCombatStaticAbility;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.FixedCondition;
import mage.abilities.condition.LockedInCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerCount;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.RequirementEffect;
import mage.abilities.effects.common.AttacksIfAbleTargetPlayerSourceEffect;
import mage.abilities.effects.common.combat.AttacksIfAbleAllEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author NinthWorld
 */
public final class DraxTheDestroyer extends CardImpl {

    public DraxTheDestroyer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{R}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.AVATAR);
        this.power = new MageInt(5);
        this.toughness = new MageInt(7);

        // If any player controls a creature named Thanos, the Mad Titan, Drax the Destroyer must attack that player each combat if able.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new DraxContinuousEffect()));

        // Whenever Drax the Destroyer attacks, he gets +X/+0 until end of turn, where X is his power.
        this.addAbility(new AttacksTriggeredAbility(
                new BoostSourceEffect(new SourcePermanentPowerCount(), StaticValue.get(0), Duration.EndOfTurn),
                false, "Whenever {this} attacks, he gets +X/+0 until end of turn, where X is his power."));
    }

    private DraxTheDestroyer(final DraxTheDestroyer card) {
        super(card);
    }

    @Override
    public DraxTheDestroyer copy() {
        return new DraxTheDestroyer(this);
    }
}

class DraxContinuousEffect extends ContinuousEffectImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(new NamePredicate("Thanos, the Mad Titan"));
    }

    public DraxContinuousEffect() {
        super(Duration.WhileOnBattlefield, Layer.RulesEffects, SubLayer.NA, Outcome.Detriment);
        this.staticText = "If any player controls a creature named Thanos, the Mad Titan, {this} must attack that player each combat if able";
    }

    public DraxContinuousEffect(final DraxContinuousEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        List<Permanent> permanents = game.getBattlefield().getAllActivePermanents(filter, game);
        if (permanents != null && permanents.size() > 0) {
            for (Permanent permanent : permanents) {
                if (permanent != null) {
                    game.addEffect(new AttacksIfAblePlayerSourceEffect(permanent.getControllerId()), source);
                }
            }
        }
        return true;
    }

    @Override
    public DraxContinuousEffect copy() {
        return new DraxContinuousEffect(this);
    }
}

class AttacksIfAblePlayerSourceEffect extends RequirementEffect {

    protected UUID playerId;

    public AttacksIfAblePlayerSourceEffect(UUID playerId) {
        super(Duration.EndOfTurn);
        this.playerId = playerId;
    }

    public AttacksIfAblePlayerSourceEffect(final AttacksIfAblePlayerSourceEffect effect) {
        super(effect);
        this.playerId = effect.playerId;
    }

    @Override
    public AttacksIfAblePlayerSourceEffect copy() {
        return new AttacksIfAblePlayerSourceEffect(this);
    }

    @Override
    public boolean applies(Permanent permanent, Ability source, Game game) {
        return permanent.getId().equals(source.getSourceId());
    }

    @Override
    public boolean mustAttack(Game game) {
        return true;
    }

    @Override
    public boolean mustBlock(Game game) {
        return false;
    }

    @Override
    public UUID mustAttackDefender(Ability source, Game game) {
        return playerId;
    }

}