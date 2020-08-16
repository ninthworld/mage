/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.abilities.effects.keyword;

import mage.abilities.Ability;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.token.ClueArtifactToken;
import mage.game.permanent.token.InventionArtifactToken;

/**
 *
 * @author NinthWorld
 */
public class InventEffect extends CreateTokenEffect {

    public InventEffect() {
        super(new InventionArtifactToken());
        this.staticText = "Invent. <i>(Create a colorless Invention artifact token with \"{2}, Sacrifice this artifact: You get {E}{E}; or put a +1/+1 counter on target creature.\")</i>";
    }

    public InventEffect(final InventEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        if (super.apply(game, source)) {
            game.fireEvent(GameEvent.getEvent(GameEvent.EventType.INVENTED, source.getSourceId(), source.getSourceId(), source.getControllerId()));
            return true;
        }
        return false;
    }

    @Override
    public InventEffect copy() {
        return new InventEffect(this);
    }
}
