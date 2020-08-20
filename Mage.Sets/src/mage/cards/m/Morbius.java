package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.DealsCombatDamageToACreatureTriggeredAbility;
import mage.abilities.effects.common.RemoveAllCountersSourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.counters.Counter;
import mage.counters.CounterType;

/**
 *
 * @author NinthWorld
 */
public final class Morbius extends CardImpl {

    public Morbius(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W/B}{W/B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.VAMPIRE);
        this.subtype.add(SubType.SCIENTIST);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // At the beginning of your upkeep, put a -1/-1 counter on Morbius.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new AddCountersSourceEffect(CounterType.M1M1.createInstance()), TargetController.YOU, false));

        // Whenever Morbius deals combat damage to a creature, remove all -1/-1 counters from him.
        this.addAbility(new DealsCombatDamageToACreatureTriggeredAbility(new RemoveAllCountersSourceEffect(CounterType.M1M1), false));
    }

    private Morbius(final Morbius card) {
        super(card);
    }

    @Override
    public Morbius copy() {
        return new Morbius(this);
    }
}
