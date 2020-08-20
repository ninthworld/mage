package mage.cards.d;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.ProtectionAbility;
import mage.constants.*;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.target.common.TargetAttackingCreature;

/**
 *
 * @author NinthWorld
 */
public final class Dagger extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(new NamePredicate("Cloak"));
    }

    public Dagger(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.ROGUE);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // {1}{W}: Put a -1/-1 counter on target attacking creature.
        Ability ability = new SimpleActivatedAbility(new AddCountersTargetEffect(CounterType.M1M1.createInstance()), new ManaCostsImpl("{1}{W}"));
        ability.addTarget(new TargetAttackingCreature());
        this.addAbility(ability);

        // Dagger has protection from black as long as you control a creature named Cloak.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD,
                new ConditionalContinuousEffect(
                        new GainAbilitySourceEffect(ProtectionAbility.from(ObjectColor.BLACK)),
                        new PermanentsOnTheBattlefieldCondition(filter),
                        "{this} has protection from black as long as you control a creature named Cloak.")));
    }

    private Dagger(final Dagger card) {
        super(card);
    }

    @Override
    public Dagger copy() {
        return new Dagger(this);
    }
}
