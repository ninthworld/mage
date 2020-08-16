package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.constants.*;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.AnotherTargetPredicate;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class PeterRasputin extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(AnotherPredicate.instance);
        filter.add(TargetController.YOU.getControllerPredicate());
    }

    public PeterRasputin(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.c.Colossus.class;

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // {2}{W}, {T}: Another target creature you control gains indestructible until end of turn. Transform Peter Rasputin.
        Ability ability = new SimpleActivatedAbility(new GainAbilityTargetEffect(IndestructibleAbility.getInstance(), Duration.EndOfTurn), new CompositeCost(new ManaCostsImpl("{2}{W}"), new TapSourceCost(), "{2}{W}, {T}"));
        ability.addTarget(new TargetCreaturePermanent(filter));
        ability.addEffect(new TransformSourceEffect(true));
        this.addAbility(ability);
    }

    private PeterRasputin(final PeterRasputin card) {
        super(card);
    }

    @Override
    public PeterRasputin copy() {
        return new PeterRasputin(this);
    }
}
