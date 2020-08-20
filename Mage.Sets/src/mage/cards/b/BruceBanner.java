package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.DrawCardControllerTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.abilities.keyword.TransformAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;

/**
 *
 * @author NinthWorld
 */
public final class BruceBanner extends CardImpl {

    public BruceBanner(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCIENTIST);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.h.Hulk.class;

        // Whenever you draw a card, you get {E}.
        this.addAbility(new DrawCardControllerTriggeredAbility(new GetEnergyCountersControllerEffect(1), false));

        // Pay {E} {E} {E} {E} {E}: Transform Bruce Banner.
        this.addAbility(new TransformAbility());
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(true), new PayEnergyCost(5)));

    }

    private BruceBanner(final BruceBanner card) {
        super(card);
    }

    @Override
    public BruceBanner copy() {
        return new BruceBanner(this);
    }
}
