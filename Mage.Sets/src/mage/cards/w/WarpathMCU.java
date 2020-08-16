
package mage.cards.w;

import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.effects.common.DamageAllEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.AgilityAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.permanent.BlockedPredicate;
import mage.filter.predicate.permanent.BlockingPredicate;

import java.util.UUID;

/**
 *
 * @author NinthWorld
 */
public final class WarpathMCU extends CardImpl {

    public WarpathMCU(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{R}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Agility
        this.addAbility(new AgilityAbility());

        // Whenever Warpath deals combat damage to a player, put a +1/+1 counter on him.
        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance()), false));

    }

    public WarpathMCU(final WarpathMCU card) {
        super(card);
    }

    @Override
    public WarpathMCU copy() {
        return new WarpathMCU(this);
    }
}
