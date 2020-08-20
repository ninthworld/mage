package mage.cards.w;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayAnyEnergyCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.cards.a.ArchdemonOfGreed;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Warlock extends CardImpl {

    public Warlock(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{3}{U}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.SHAPESHIFTER);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        // At the beginning of your upkeep, pay {E}. If you can't, put a -1/-1 counter on Warlock.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new WarlockEffect(), TargetController.YOU, false));

        // {1}, Sacrifice an artifact: You get {E}.
        this.addAbility(new SimpleActivatedAbility(
                new GetEnergyCountersControllerEffect(1),
                new CompositeCost(
                        new GenericManaCost(1),
                        new SacrificeTargetCost(new TargetControlledPermanent(StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACT)),
                        "{1}, Sacrifice an artifact")));
    }

    private Warlock(final Warlock card) {
        super(card);
    }

    @Override
    public Warlock copy() {
        return new Warlock(this);
    }
}

class WarlockEffect extends OneShotEffect {

    public WarlockEffect() {
        super(Outcome.Detriment);
        this.staticText = "pay {E}. If you can't, put a -1/-1 counter on {this}.";
    }

    public WarlockEffect(final WarlockEffect effect) {
        super(effect);
    }

    @Override
    public WarlockEffect copy() {
        return new WarlockEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player != null) {
            if (player.getCounters().getCount(CounterType.ENERGY) > 0) {
                player.removeCounters(CounterType.ENERGY.getName(), 1, source, game);
            }
            else {
                new AddCountersSourceEffect(CounterType.M1M1.createInstance()).apply(game, source);
            }
            return true;
        }
        return false;
    }
}