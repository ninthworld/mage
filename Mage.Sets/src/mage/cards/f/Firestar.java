package mage.cards.f;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.DamageMultiEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetAnyTargetAmount;

/**
 *
 * @author NinthWorld
 */
public final class Firestar extends CardImpl {

    public Firestar(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.ADVISOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Generator
        this.addAbility(new GeneratorAbility());

        // Pay {E} {E}, {T}: Firestar deals 2 damage divided as you choose among one or two targets.
        Ability ability = new SimpleActivatedAbility(new DamageMultiEffect(2), new PayEnergyCost(2));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetAnyTargetAmount(2));
    }

    private Firestar(final Firestar card) {
        super(card);
    }

    @Override
    public Firestar copy() {
        return new Firestar(this);
    }
}
