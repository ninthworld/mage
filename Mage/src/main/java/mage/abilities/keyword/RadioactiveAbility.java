package mage.abilities.keyword;

import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.constants.TargetController;

/**
 *
 * @author NinthWorld
 */
public class RadioactiveAbility extends BeginningOfUpkeepTriggeredAbility {

    public RadioactiveAbility() {
        super(new GetEnergyCountersControllerEffect(2), TargetController.YOU, false);
        this.addEffect(new LoseLifeSourceControllerEffect(1));
    }

    public RadioactiveAbility(final RadioactiveAbility ability) {
        super(ability);
    }

    @Override
    public BeginningOfUpkeepTriggeredAbility copy() {
        return new RadioactiveAbility(this);
    }

    @Override
    public String getRule() {
        return "Radioactive <i>(At the beginning of your upkeep, you get {E}{E} and lose 1 life.)</i>";
    }
}