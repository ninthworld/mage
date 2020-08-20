package mage.target.targetadjustment;

import mage.abilities.Ability;
import mage.abilities.costs.VariableCost;
import mage.filter.FilterPermanent;
import mage.game.Game;
import mage.target.TargetPermanent;

/**
 *
 * @author NinthWorld
 */
public enum XValueTargetsAdjuster implements TargetAdjuster {
    instance;

    @Override
    public void adjustTargets(Ability ability, Game game) {
        int xValue = 0;
        for (VariableCost cost : ability.getCosts().getVariableCosts()) {
            xValue += cost.getAmount();
        }
        FilterPermanent permanentFilter = ((TargetPermanent) ability.getTargets().get(0)).getFilter();
        ability.getTargets().clear();
        ability.addTarget(new TargetPermanent(xValue, permanentFilter));
    }
}
