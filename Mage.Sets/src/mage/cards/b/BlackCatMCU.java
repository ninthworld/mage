/*
 *  
 * Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 * 
 */
package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.DiesSourceTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.SourceOnBattlefieldControlUnchangedCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.continuous.GainControlTargetEffect;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.abilities.keyword.AgilityAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterArtifactPermanent;
import mage.filter.predicate.permanent.ControllerIdPredicate;
import mage.game.Game;
import mage.game.events.DamagedPlayerEvent;
import mage.game.events.FlipCoinEvent;
import mage.game.events.GameEvent;
import mage.players.Player;
import mage.target.TargetPermanent;
import mage.target.common.TargetOpponent;

import java.util.UUID;

/**
 *
 * @author NinthWorld
 */
public final class BlackCatMCU extends CardImpl {

    public BlackCatMCU(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{R}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CAT);
        this.subtype.add(SubType.ROGUE);
        this.power = new MageInt(4);
        this.toughness = new MageInt(2);

        // Your opponents can’t win coin flips.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new BlackCatEffect()));

        // Agility
        this.addAbility(new AgilityAbility());

        // Whenever Black Cat deals combat damage to a player, you may gain control of target artifact that player controls for as long as you control Black Cat.
        this.addAbility(new BlackCatTriggeredAbility());
    }

    public BlackCatMCU(final BlackCatMCU card) {
        super(card);
    }

    @Override
    public BlackCatMCU copy() {
        return new BlackCatMCU(this);
    }
}

class BlackCatEffect extends ReplacementEffectImpl {

    public BlackCatEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "Your opponents can’t win coin flips.";
    }

    public BlackCatEffect(final BlackCatEffect effect) {
        super(effect);
    }

    @Override
    public BlackCatEffect copy() {
        return new BlackCatEffect(this);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        FlipCoinEvent flipCoinEvent = (FlipCoinEvent) event;
        flipCoinEvent.setResult(false);
        return false;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.FLIP_COIN;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        return game.getPlayer(event.getPlayerId()).hasOpponent(source.getControllerId(), game);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return false;
    }
}

class BlackCatTriggeredAbility extends TriggeredAbilityImpl {

    public BlackCatTriggeredAbility() {
        super(Zone.BATTLEFIELD,
                new ConditionalContinuousEffect(
                    new GainControlTargetEffect(Duration.Custom),
                    new SourceOnBattlefieldControlUnchangedCondition(),
                    "gain control of target artifact that player controls for as long as you control {this}"),
                true);
    }

    public BlackCatTriggeredAbility(final BlackCatTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public BlackCatTriggeredAbility copy() {
        return new BlackCatTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_PLAYER;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        DamagedPlayerEvent damageEvent = (DamagedPlayerEvent) event;
        if (damageEvent.isCombatDamage() && event.getSourceId().equals(this.getSourceId())) {
            FilterArtifactPermanent filter = new FilterArtifactPermanent("artifact that player controls");
            filter.add(new ControllerIdPredicate(event.getPlayerId()));
            filter.setMessage("artifact controlled by " + game.getPlayer(event.getTargetId()).getLogName());

            this.getTargets().clear();
            this.addTarget(new TargetPermanent(filter));
            return true;
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever {this} deals combat damage to a player, you may gain control of target artifact that player controls for as long as you control {this}.";
    }
}