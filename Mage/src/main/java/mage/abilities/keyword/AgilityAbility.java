package mage.abilities.keyword;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.constants.Duration;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.combat.CombatGroup;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

/**
 * @author NinthWorld
 */
public class AgilityAbility extends TriggeredAbilityImpl {

    public AgilityAbility() {
        super(Zone.BATTLEFIELD, new BoostSourceEffect(1, 1, Duration.EndOfTurn), false);
    }

    public AgilityAbility(final AgilityAbility ability) {
        super(ability);
    }

    @Override
    public AgilityAbility copy() {
        return new AgilityAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DECLARE_BLOCKERS_STEP;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent source = game.getPermanent(getSourceId());
        if (source != null) {
            if (source.isBlocked(game)) {
                return true;
            }
            for (CombatGroup group : game.getCombat().getGroups()) {
                if (group.getBlockers().contains(getSourceId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Agility <i>(Whenever this creature blocks or becomes blocked, it gets +1/+1 until end of turn.)</i>";
    }
}