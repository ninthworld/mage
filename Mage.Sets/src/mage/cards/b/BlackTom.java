package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DiesTriggeredAbility;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.cards.basiclands.BasicLand;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.WitherAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class BlackTom extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("basic lands you control");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(SuperType.BASIC.getPredicate());
        filter.add(CardType.LAND.getPredicate());
    }

    public BlackTom(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B/G}{B/G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.PLANT);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(5);

        // Wither
        this.addAbility(WitherAbility.getInstance());

        // When Black Tom dies, put a number of -1/-1 counters on target creature equal to the number of basic lands you control.
        Ability ability = new DiesTriggeredAbility(new AddCountersTargetEffect(CounterType.M1M1.createInstance(), new PermanentsOnBattlefieldCount(filter)));
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private BlackTom(final BlackTom card) {
        super(card);
    }

    @Override
    public BlackTom copy() {
        return new BlackTom(this);
    }
}
