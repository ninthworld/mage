
package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbility;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.Cost;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.CardsInControllerHandCount;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.combat.CantBlockSourceEffect;
import mage.abilities.effects.common.continuous.SetPowerSourceEffect;
import mage.abilities.effects.common.continuous.SetPowerToughnessSourceEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.cards.*;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterLandPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetAmount;
import mage.target.TargetCard;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreaturePermanent;
import mage.target.common.TargetCreaturePermanentAmount;
import mage.target.targetadjustment.TargetAdjuster;

import java.util.List;
import java.util.UUID;

/**
 * @author NinthWorld
 */
public final class AvalancheMCU extends CardImpl {

    private static final FilterLandPermanent filter = new FilterLandPermanent("Mountain you control");

    static {
        filter.add(SubType.MOUNTAIN.getPredicate());
        filter.add(TargetController.YOU.getControllerPredicate());
    }

    public AvalancheMCU(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(0);
        this.toughness = new MageInt(3);

        // Avalanche's power is equal to the number of Mountains you control.
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new SetPowerSourceEffect(new PermanentsOnBattlefieldCount(filter, 0), Duration.EndOfGame)));

        // Generator
        this.addAbility(new GeneratorAbility());

        // Whenever Avalanche attacks, you may pay any amount of {E}. If you do, up to X target creatures can’t block this turn, where X is the amount of {E} paid this way.
        this.addAbility(new AttacksTriggeredAbility(new AvalachePaysEffect(), true));
    }

    public AvalancheMCU(final AvalancheMCU card) {
        super(card);
    }

    @Override
    public AvalancheMCU copy() {
        return new AvalancheMCU(this);
    }
}

class AvalachePaysEffect extends OneShotEffect {

    public AvalachePaysEffect() {
        super(Outcome.Benefit);
        staticText = "you may pay any amount of {E}. If you do, up to X target creatures can't block this turn, where X is the amount of {E} paid this way.";
    }

    public AvalachePaysEffect(final AvalachePaysEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Card sourceCard = game.getCard(source.getSourceId());
        if (controller != null && sourceCard != null) {
            int payEnergy = controller.getAmount(0, controller.getCounters().getCount(CounterType.ENERGY), "Pay how much energy?", game);
            if (payEnergy > 0) {
                controller.removeCounters(CounterType.ENERGY.getName(), payEnergy, source, game);
                game.informPlayers(sourceCard.getName() + ": " + controller.getLogName() + " pays " + payEnergy + " energy");

                TargetCreaturePermanent target = new TargetCreaturePermanent(0, payEnergy);

                if (controller.chooseTarget(Outcome.Benefit, target, source, game)) {
                    List<UUID> targets = target.getTargets();
                    for (UUID targetId : targets) {
                        Permanent permanent = game.getPermanent(targetId);
                        if (permanent != null) {
                            permanent.addAbility(new SimpleStaticAbility(new CantBlockSourceEffect(Duration.EndOfTurn)));
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public AvalachePaysEffect copy() {
        return new AvalachePaysEffect(this);
    }

}