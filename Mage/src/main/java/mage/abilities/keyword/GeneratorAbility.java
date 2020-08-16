package mage.abilities.keyword;

import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.constants.TargetController;

/**
 *
 * @author NinthWorld
 */
public class GeneratorAbility extends BeginningOfUpkeepTriggeredAbility {

    public GeneratorAbility() {
        super(new GetEnergyCountersControllerEffect(1), TargetController.YOU, false);
    }

    public GeneratorAbility(final GeneratorAbility ability) {
        super(ability);
    }

    @Override
    public BeginningOfUpkeepTriggeredAbility copy() {
        return new GeneratorAbility(this);
    }

    @Override
    public String getRule() {
        return "Generator <i>(At the beginning of your upkeep, you get {E}.)</i>";
    }
}