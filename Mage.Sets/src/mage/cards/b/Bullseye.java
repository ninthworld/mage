package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerCount;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.UnstableAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.ReachAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.common.TargetControlledPermanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Bullseye extends CardImpl {

    public Bullseye(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{B}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Deathtouch
        this.addAbility(DeathtouchAbility.getInstance());

        // Reach
        this.addAbility(ReachAbility.getInstance());

        // Unstable
        this.addAbility(new UnstableAbility());

        // {T}, Sacrifice an artifact: Bullseye deals damage to target creature equal to his power.
        Ability ability = new SimpleActivatedAbility(
                new DamageTargetEffect(new SourcePermanentPowerCount()),
                new CompositeCost(
                        new TapSourceCost(),
                        new SacrificeTargetCost(new TargetControlledPermanent(StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACT)),
                        "{T}, Sacrifice an artifact"));
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private Bullseye(final Bullseye card) {
        super(card);
    }

    @Override
    public Bullseye copy() {
        return new Bullseye(this);
    }
}
