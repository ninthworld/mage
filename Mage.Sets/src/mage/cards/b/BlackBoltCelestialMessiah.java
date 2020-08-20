package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.TapAllEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.DefendingPlayerControlsPredicate;

/**
 *
 * @author NinthWorld
 */
public final class BlackBoltCelestialMessiah extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("creatures defending player controls");

    static {
        filter.add(DefendingPlayerControlsPredicate.instance);
    }

    public BlackBoltCelestialMessiah(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.NOBLE);
        this.power = new MageInt(4);
        this.toughness = new MageInt(5);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Generator
        this.addAbility(new GeneratorAbility());

        // Whenever Black Bolt, Celestial Messiah attacks, you may pay {E} {E} {E}. If you do, tap all creatures defending player controls.
        this.addAbility(new AttacksTriggeredAbility(new DoIfCostPaid(new TapAllEffect(filter), new PayEnergyCost(3)), false));
    }

    private BlackBoltCelestialMessiah(final BlackBoltCelestialMessiah card) {
        super(card);
    }

    @Override
    public BlackBoltCelestialMessiah copy() {
        return new BlackBoltCelestialMessiah(this);
    }
}
