package mage.cards.g;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.keyword.GeneratorAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.filter.StaticFilters;
import mage.filter.common.FilterLandPermanent;
import mage.filter.predicate.permanent.DefendingPlayerControlsPredicate;
import mage.target.common.TargetLandPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Guardian extends CardImpl {

    private static final FilterLandPermanent filter = new FilterLandPermanent("land defending player controls");

    static {
        filter.add(DefendingPlayerControlsPredicate.instance);
    }

    public Guardian(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Generator
        this.addAbility(new GeneratorAbility());

        // Whenever Guardian attacks, you may pay {E} {E} {E}. If you do, destroy target land defending player controls.
        Ability ability = new AttacksTriggeredAbility(new DoIfCostPaid(new DestroyTargetEffect(), new PayEnergyCost(3)), false);
        ability.addTarget(new TargetLandPermanent(filter));
        this.addAbility(ability);
    }

    private Guardian(final Guardian card) {
        super(card);
    }

    @Override
    public Guardian copy() {
        return new Guardian(this);
    }
}
