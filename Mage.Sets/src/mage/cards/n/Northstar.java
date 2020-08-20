package mage.cards.n;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.combat.CantBeBlockedByCreaturesSourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.LifelinkAbility;
import mage.constants.*;
import mage.abilities.keyword.DoubleStrikeAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.AbilityPredicate;
import mage.filter.predicate.mageobject.NamePredicate;

/**
 *
 * @author NinthWorld
 */
public final class Northstar extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("you control a creature named Aurora");
    private static final FilterCreaturePermanent hasteFilter = new FilterCreaturePermanent("creatures with haste");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(new NamePredicate("Aurora"));
        hasteFilter.add(new AbilityPredicate(HasteAbility.class));
    }

    public Northstar(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.ADVISOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Double strike
        this.addAbility(DoubleStrikeAbility.getInstance());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Northstar can't be blocked except by creatures with haste.
        this.addAbility(new SimpleStaticAbility(new CantBeBlockedByCreaturesSourceEffect(hasteFilter, Duration.WhileOnBattlefield)));

        // Northstar gets +1/+1 and has lifelink as long as you control a creature named Aurora.
        Effect boostEffect = new ConditionalContinuousEffect(
                new BoostSourceEffect(1, 0, Duration.WhileOnBattlefield),
                new PermanentsOnTheBattlefieldCondition(filter),
                "{this} gets +1/+1");
        Effect gainAbilityEffect = new ConditionalContinuousEffect(
                new GainAbilitySourceEffect(LifelinkAbility.getInstance(), Duration.WhileOnBattlefield),
                new PermanentsOnTheBattlefieldCondition(filter),
                "and has lifelink as long as you control a creature named Aurora");
        Ability ability = new SimpleStaticAbility(Zone.BATTLEFIELD, boostEffect);
        ability.addEffect(gainAbilityEffect);
        this.addAbility(ability);
    }

    private Northstar(final Northstar card) {
        super(card);
    }

    @Override
    public Northstar copy() {
        return new Northstar(this);
    }
}
