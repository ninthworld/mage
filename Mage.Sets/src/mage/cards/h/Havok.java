package mage.cards.h;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.effects.common.DamageAllEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.keyword.GeneratorAbility;
import mage.abilities.keyword.UnstableAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.DefendingPlayerControlsPredicate;
import mage.target.common.TargetLandPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Havok extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("creature defending player controls");

    static {
        filter.add(DefendingPlayerControlsPredicate.instance);
    }

    public Havok(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Unstable
        this.addAbility(new UnstableAbility());

        // Generator
        this.addAbility(new GeneratorAbility());

        // Whenever Havok attacks, you may pay {E} {E}. If you do, he deals 2 damage to each creature defending player controls.
        this.addAbility(new AttacksTriggeredAbility(new DoIfCostPaid(new DamageAllEffect(2, filter), new PayEnergyCost(2)), false));
    }

    private Havok(final Havok card) {
        super(card);
    }

    @Override
    public Havok copy() {
        return new Havok(this);
    }
}
