package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.common.DealsDamageToACreatureTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.keyword.ArsenalAbility;
import mage.abilities.keyword.ProtectionAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;

/**
 *
 * @author NinthWorld
 */
public final class Blade extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("non-Human creature");

    static {
        filter.add(Predicates.not(SubType.HUMAN.getPredicate()));
    }

    public Blade(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W/B}{W/B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VAMPIRE);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // protection from white
        this.addAbility(ProtectionAbility.from(ObjectColor.WHITE));

        // Arsenal 1
        this.addAbility(new ArsenalAbility(1));

        // Whenever Blade deals damage to a non-Human creature, you may pay {2}{W}. If you do, exile that creature.
        this.addAbility(new DealsDamageToACreatureTriggeredAbility(new DoIfCostPaid(new ExileTargetEffect(), new ManaCostsImpl("{2}{W}")), false, false, true, filter));
    }

    private Blade(final Blade card) {
        super(card);
    }

    @Override
    public Blade copy() {
        return new Blade(this);
    }
}
