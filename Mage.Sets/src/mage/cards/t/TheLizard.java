package mage.cards.t;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.RemoveCounterCost;
import mage.abilities.costs.common.RemoveCountersSourceCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.RegenerateSourceEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.TransformAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.counters.CounterType;
import mage.filter.StaticFilters;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author NinthWorld
 */
public final class TheLizard extends CardImpl {

    public TheLizard(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.LIZARD);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        this.transformable = true;
        this.nightCard = true;

        // Remove a +1/+1 counter from The Lizard: Regenerate The Lizard.
        this.addAbility(new SimpleActivatedAbility(new RegenerateSourceEffect(), new RemoveCountersSourceCost(CounterType.P1P1.createInstance())));

        // Sacrifice another creature: Transform The Lizard.
        this.addAbility(new TransformAbility());
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(false), new SacrificeTargetCost(new TargetControlledPermanent(StaticFilters.FILTER_CONTROLLED_ANOTHER_CREATURE))));
    }

    private TheLizard(final TheLizard card) {
        super(card);
    }

    @Override
    public TheLizard copy() {
        return new TheLizard(this);
    }
}
