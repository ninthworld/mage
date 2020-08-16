package mage.cards.a;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ColorlessManaCost;
import mage.abilities.effects.common.continuous.BoostAllEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.keyword.VigilanceAbility;
import mage.abilities.mana.AnyColorManaAbility;
import mage.constants.Duration;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.MulticoloredPredicate;

/**
 *
 * @author NinthWorld
 */
public final class AvengersTower extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Multicolored creatures you control");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(MulticoloredPredicate.instance);
    }

    public AvengersTower(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setGold(true);

        this.nightCard = true;
        this.transformable = true;

        // {T}: Add one mana of any color.
        this.addAbility(new AnyColorManaAbility());

        // {3}, {T}: Multicolored creatures you control get +1/+1 and gain vigilance until end of turn.
        Ability ability = new SimpleActivatedAbility(new BoostAllEffect(1, 1, Duration.EndOfTurn, filter, false), new CompositeCost(new ColorlessManaCost(3), new TapSourceCost(), "{3}, {T}"));
        ability.addEffect(new GainAbilityAllEffect(VigilanceAbility.getInstance(), Duration.EndOfTurn, filter));
        this.addAbility(ability);
    }

    private AvengersTower(final AvengersTower card) {
        super(card);
    }

    @Override
    public AvengersTower copy() {
        return new AvengersTower(this);
    }
}
