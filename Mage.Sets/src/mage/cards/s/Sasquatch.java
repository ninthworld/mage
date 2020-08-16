package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealtDamageToSourceTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.counter.AddCountersAllEffect;
import mage.cards.e.Enrage;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.AnotherPredicate;

/**
 *
 * @author NinthWorld
 */
public final class Sasquatch extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("creature you control");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
    }

    public Sasquatch(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.GAMMA);
        this.subtype.add(SubType.BEAST);
        this.power = new MageInt(6);
        this.toughness = new MageInt(4);

        this.color.setGreen(true);

        this.nightCard = true;
        this.transformable = true;

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // <i>Enrage</i> - Whenever Sasquatch is dealt damage, put a +1/+1 counter on each creature you control.
        this.addAbility(new DealtDamageToSourceTriggeredAbility(new AddCountersAllEffect(CounterType.P1P1.createInstance(), filter), false, true));

        // {T}: Transform Sasquatch.
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(false), new TapSourceCost()));
    }

    private Sasquatch(final Sasquatch card) {
        super(card);
    }

    @Override
    public Sasquatch copy() {
        return new Sasquatch(this);
    }
}
