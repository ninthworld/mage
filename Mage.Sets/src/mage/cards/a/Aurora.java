package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.*;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.NamePredicate;

/**
 *
 * @author NinthWorld
 */
public final class Aurora extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("you control a creature named Northstar");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(new NamePredicate("Northstar"));
    }

    public Aurora(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.ADVISOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Unstable
        this.addAbility(new UnstableAbility());

        // Aurora gets +1/+1 and has lifelink as long as you control a creature named Northstar.
        Effect boostEffect = new ConditionalContinuousEffect(
                new BoostSourceEffect(1, 0, Duration.WhileOnBattlefield),
                new PermanentsOnTheBattlefieldCondition(filter),
                "Aurora gets +1/+1");
        Effect gainAbilityEffect = new ConditionalContinuousEffect(
                new GainAbilitySourceEffect(LifelinkAbility.getInstance(), Duration.WhileOnBattlefield),
                new PermanentsOnTheBattlefieldCondition(filter),
                "and has lifelink as long as you control a creature named Northstar");
        Ability ability = new SimpleStaticAbility(Zone.BATTLEFIELD, boostEffect);
        ability.addEffect(gainAbilityEffect);
        this.addAbility(ability);
    }

    private Aurora(final Aurora card) {
        super(card);
    }

    @Override
    public Aurora copy() {
        return new Aurora(this);
    }
}
