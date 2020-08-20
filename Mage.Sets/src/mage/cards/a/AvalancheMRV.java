
package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.PayAnyEnergyCost;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.combat.CantBlockTargetEffect;
import mage.abilities.effects.common.continuous.SetPowerSourceEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.cards.*;
import mage.constants.*;
import mage.filter.common.FilterLandPermanent;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetadjustment.XValueTargetsAdjuster;

import java.util.UUID;

/**
 * @author NinthWorld
 */
public final class AvalancheMRV extends CardImpl {

    private static final FilterLandPermanent filter = new FilterLandPermanent("Mountain you control");

    static {
        filter.add(SubType.MOUNTAIN.getPredicate());
        filter.add(TargetController.YOU.getControllerPredicate());
    }

    public AvalancheMRV(UUID ownerId, CardSetInfo setInfo) {
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
        Ability ability = new AttacksTriggeredAbility(new DoIfCostPaid(
                new CantBlockTargetEffect(Duration.EndOfTurn),
                new PayAnyEnergyCost()),
                true,
                "Whenever {this} attacks, you may pay any amount of {E}. If you do, up to X target creatures can’t block this turn, where X is the amount of {E} paid this way.");
        ability.addTarget(new TargetCreaturePermanent());
        ability.setTargetAdjuster(XValueTargetsAdjuster.instance);
        this.addAbility(ability);
    }

    public AvalancheMRV(final AvalancheMRV card) {
        super(card);
    }

    @Override
    public AvalancheMRV copy() {
        return new AvalancheMRV(this);
    }
}