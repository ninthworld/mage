package mage.cards.b;

import java.util.UUID;

import mage.abilities.common.ActivateIfConditionActivatedAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.mana.AddManaOfAnyColorEffect;
import mage.abilities.mana.ActivateIfConditionManaAbility;
import mage.abilities.mana.AnyColorManaAbility;
import mage.abilities.mana.ColorlessManaAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class BaxterBuilding extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
    }

    public BaxterBuilding(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setGold(true);

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // {T}: Add one mana of any color. Activate this ability only if you control two or more creatures.
        this.addAbility(new ActivateIfConditionManaAbility(
                Zone.BATTLEFIELD,
                new AddManaOfAnyColorEffect(1),
                new TapSourceCost(),
                new PermanentsOnTheBattlefieldCondition(filter, ComparisonType.MORE_THAN, 1)));

        // {4}, {T}: Creatures you control get +2/+2 until end of turn. Activate this ability only if you control four or more creatures.
        this.addAbility(new ActivateIfConditionActivatedAbility(
                Zone.BATTLEFIELD,
                new BoostControlledEffect(2, 2, Duration.EndOfTurn, filter),
                new CompositeCost(new GenericManaCost(4), new TapSourceCost(), "{4}, {T}"),
                new PermanentsOnTheBattlefieldCondition(filter, ComparisonType.MORE_THAN, 3)));
    }

    private BaxterBuilding(final BaxterBuilding card) {
        super(card);
    }

    @Override
    public BaxterBuilding copy() {
        return new BaxterBuilding(this);
    }
}
