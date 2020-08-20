package mage.abilities.costs.common;

import mage.abilities.Ability;
import mage.abilities.costs.Cost;
import mage.abilities.costs.VariableCostImpl;
import mage.counters.CounterType;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author NinthWorld
 */
public class PayAnyEnergyCost extends VariableCostImpl {

    public PayAnyEnergyCost() {
        super("energy to pay");
        this.text = "Pay any amount of {E}";
    }

    public PayAnyEnergyCost(final PayAnyEnergyCost cost) {
        super(cost);
    }

    @Override
    public PayAnyEnergyCost copy() {
        return new PayAnyEnergyCost(this);
    }

    @Override
    public Cost getFixedCostsFromAnnouncedValue(int xValue) {
        return new PayEnergyCost(xValue);
    }

    @Override
    public int getMaxValue(Ability source, Game game) {
        int maxValue = 0;
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            maxValue = controller.getCounters().getCount(CounterType.ENERGY);
        }
        return maxValue;
    }

}
