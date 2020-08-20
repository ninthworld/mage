package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.FightTargetSourceEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.constants.*;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.target.common.TargetArtifactPermanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class BenGrimmTheThing extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Vehicles you control");
    private static final FilterCreaturePermanent filterNotControlled = new FilterCreaturePermanent("creature you don't control");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(SubType.VEHICLE.getPredicate());
        filterNotControlled.add(TargetController.NOT_YOU.getControllerPredicate());
    }

    public BenGrimmTheThing(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{R/G}{R/G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // Vehicles you control get +1/+1.
        this.addAbility(new SimpleStaticAbility(new BoostControlledEffect(1, 1, Duration.WhileOnBattlefield, filter)));

        // When Ben Grimm, the Thing enters the battlefield or attacks, choose one -
        // • Destroy target artifact.
        Ability ability = new EntersBattlefieldAbility(new DestroyTargetEffect());
        ability.addTarget(new TargetArtifactPermanent());

        // • Ben Grimm, the Thing fights target creature you don't control.
        Mode mode = new Mode();
        mode.addEffect(new FightTargetSourceEffect());
        mode.addTarget(new TargetCreaturePermanent(filterNotControlled));
        ability.addMode(mode);

        this.addAbility(ability);
    }

    private BenGrimmTheThing(final BenGrimmTheThing card) {
        super(card);
    }

    @Override
    public BenGrimmTheThing copy() {
        return new BenGrimmTheThing(this);
    }
}
