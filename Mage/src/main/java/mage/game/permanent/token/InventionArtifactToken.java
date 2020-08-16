
package mage.game.permanent.token;

import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.target.common.TargetCreaturePermanent;
import mage.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author NinthWorld
 */
public final class InventionArtifactToken extends TokenImpl {

    public InventionArtifactToken() {
        super("Invention", "colorless Invention artifact token with \"{2}, Sacrifice this artifact: You get {E}{E}; or put a +1/+1 counter on target creature.\"");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.ARTIFACT);
        subtype.add(SubType.INVENTION);

        // {2}, Sacrifice this artifact: You get {E}{E}; or put a +1/+1 counter on target creature.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD,
                new GetEnergyCountersControllerEffect(2),
                new CompositeCost(new GenericManaCost(2), new SacrificeSourceCost(), "{2}, Sacrifice this artifact"));

        Mode mode = new Mode(new AddCountersTargetEffect(CounterType.P1P1.createInstance()));
        mode.addTarget(new TargetCreaturePermanent());

        ability.addMode(mode);

        this.addAbility(ability);
    }

    public InventionArtifactToken(final InventionArtifactToken token) {
        super(token);
    }

    @Override
    public InventionArtifactToken copy() {
        return new InventionArtifactToken(this);
    }
}
