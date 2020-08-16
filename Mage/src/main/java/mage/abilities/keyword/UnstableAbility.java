package mage.abilities.keyword;

import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.effects.common.FlipCoinEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.constants.Duration;
import mage.constants.TargetController;
/**
 * @author NinthWorld
 */
public class UnstableAbility extends BeginningOfUpkeepTriggeredAbility {

    public UnstableAbility() {
        super(new FlipCoinEffect(new BoostControlledEffect(1, 1, Duration.EndOfTurn), new BoostControlledEffect(-1, -1, Duration.EndOfTurn)), TargetController.YOU, false);
    }

    public UnstableAbility(final UnstableAbility ability) {
        super(ability);
    }

    @Override
    public BeginningOfUpkeepTriggeredAbility copy() {
        return new UnstableAbility(this);
    }

    @Override
    public String getRule() {
        return "Unstable <i>(At the beginning of your upkeep, flip a coin. If you win, this creature gets +1/+1 until end of turn. If you lose, it gets -1/-1 until end of turn instead.)</i>";
    }
}