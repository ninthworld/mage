package mage.cards.j;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.BoostAllOfChosenSubtypeEffect;
import mage.abilities.keyword.TransformAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class JamesRhodes extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Vehicles you control");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(SubType.VEHICLE.getPredicate());
    }

    public JamesRhodes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R/W}{R/W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.w.WarMachine.class;

        // Vehicles you control get +1/+1.
        this.addAbility(new SimpleStaticAbility(new BoostAllOfChosenSubtypeEffect(1, 1, Duration.WhileOnBattlefield, filter, false)));

        // At the beginning of your upkeep, if you control three or more artifacts, transform James Rhodes.
        this.addAbility(new TransformAbility());
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new ConditionalOneShotEffect(
                    new TransformSourceEffect(true),
                    new PermanentsOnTheBattlefieldCondition(StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACT, ComparisonType.MORE_THAN, 2)),
                TargetController.YOU, false));
    }

    private JamesRhodes(final JamesRhodes card) {
        super(card);
    }

    @Override
    public JamesRhodes copy() {
        return new JamesRhodes(this);
    }
}
