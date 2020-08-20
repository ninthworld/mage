package mage.cards.d;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.GainLifeControllerTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Dazzler extends CardImpl {

    public Dazzler(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // Whenever you gain life, you get {E}.
        this.addAbility(new GainLifeControllerTriggeredAbility(new GetEnergyCountersControllerEffect(1), false));

        // Pay {E} {E}: Creatures you control get +1/+1 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostControlledEffect(1, 1, Duration.EndOfTurn), new PayEnergyCost(2)));
    }

    private Dazzler(final Dazzler card) {
        super(card);
    }

    @Override
    public Dazzler copy() {
        return new Dazzler(this);
    }
}
