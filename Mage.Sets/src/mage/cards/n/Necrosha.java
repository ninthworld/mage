package mage.cards.n;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.mana.BlackManaAbility;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Necrosha extends CardImpl {

    private static final FilterCard filter = new FilterCard("creature card in your graveyard");

    static {
        filter.add(CardType.CREATURE.getPredicate());
    }

    public Necrosha(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setBlack(true);

        this.nightCard = true;
        this.transformable = true;

        // {T}: Add {B}.
        this.addAbility(new BlackManaAbility());

        // {3}, {T}: Put a -1/-1 counter on target creature. You gain 1 life.
        Ability ability1 = new SimpleActivatedAbility(new AddCountersTargetEffect(CounterType.M1M1.createInstance()), new CompositeCost(new GenericManaCost(3), new TapSourceCost(), "{3}, {T}"));
        ability1.addTarget(new TargetCreaturePermanent());
        ability1.addEffect(new GainLifeEffect(1));
        this.addAbility(ability1);

        // {1}{B}, Pay 1 life, {T}: Return target creature card from your graveyard to your hand.
        Ability ability2 = new SimpleActivatedAbility(new ReturnFromGraveyardToHandTargetEffect(), new CompositeCost(new ManaCostsImpl("{1}{B}"), new CompositeCost(new PayLifeCost(1), new TapSourceCost(), ""), "{1}{B}, Pay 1 life, {T}"));
        ability2.addTarget(new TargetCardInYourGraveyard(filter));
        this.addAbility(ability2);
    }

    private Necrosha(final Necrosha card) {
        super(card);
    }

    @Override
    public Necrosha copy() {
        return new Necrosha(this);
    }
}
