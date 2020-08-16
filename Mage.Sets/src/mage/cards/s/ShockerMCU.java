package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealsDamageToAPlayerTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerCount;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetArtifactPermanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author NinthWorld
 */
public final class ShockerMCU extends CardImpl {

    public ShockerMCU(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(5);

        // Generator
        this.addAbility(new GeneratorAbility());

        // Pay {E}: Destroy target artifact.
        Ability ability1 = new SimpleActivatedAbility(new DestroyTargetEffect(), new PayEnergyCost(1));
        ability1.addTarget(new TargetArtifactPermanent());
        this.addAbility(ability1);

        // Pay {E}{E}, {T}: Shocker deals damage equal to his power to target creature.
        Ability ability2 = new SimpleActivatedAbility(new DamageTargetEffect(new SourcePermanentPowerCount()), new CompositeCost(new PayEnergyCost(2), new TapSourceCost(), "Pay {E}{E}, {T}"));
        ability2.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability2);
    }

    public ShockerMCU(final ShockerMCU card) {
        super(card);
    }

    @Override
    public ShockerMCU copy() {
        return new ShockerMCU(this);
    }
}