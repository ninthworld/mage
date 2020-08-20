package mage.cards.h;

import java.util.*;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Gender;
import mage.abilities.common.BeginningOfYourEndStepTriggeredAbility;
import mage.abilities.common.DealtDamageToSourceTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileAndReturnTransformedSourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.RadioactiveAbility;
import mage.abilities.keyword.TransformAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.players.Player;
import mage.watchers.Watcher;

/**
 *
 * @author NinthWorld
 */
public final class Hulk extends CardImpl {

    public Hulk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.GAMMA);
        this.subtype.add(SubType.BERSERKER);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        this.color.setGreen(true);
        this.color.setRed(true);

        this.nightCard = true;
        this.transformable = true;

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // Radioactive
        this.addAbility(new RadioactiveAbility());

        // <i>Enrage</i> - Whenever Hulk is dealt damage, put a +1/+1 counter on him.
        this.addAbility(new DealtDamageToSourceTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance()), false, true));

        // At the beginning of your end step, if Hulk didn't deal or receive damage this turn, exile him, then return him to the battlefield transformed under his owner's control.
        this.addAbility(new TransformAbility());
        this.addAbility(new BeginningOfYourEndStepTriggeredAbility(new HulkEffect(), false), new HulkWatcher());
    }

    private Hulk(final Hulk card) {
        super(card);
    }

    @Override
    public Hulk copy() {
        return new Hulk(this);
    }
}

class HulkWatcher extends Watcher {

    private boolean dealtDamage;
    private boolean receivedDamage;

    public HulkWatcher() {
        super(WatcherScope.GAME);
        this.dealtDamage = false;
        this.receivedDamage = false;
    }

    @Override
    public void watch(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.CLEANUP_STEP_POST) {
            this.dealtDamage = false;
            this.receivedDamage = false;
        }

        if (event.getType() == GameEvent.EventType.DAMAGED_CREATURE && event.getTargetId() == this.getSourceId()) {
            this.receivedDamage = true;
        }

        if (event.getType() == GameEvent.EventType.DAMAGED_CREATURE || event.getType() == GameEvent.EventType.DAMAGED_PLAYER || event.getType() == GameEvent.EventType.DAMAGED_PLANESWALKER) {
            if (event.getSourceId() == this.getSourceId()) {
                this.dealtDamage = true;
            }
        }
    }

    public boolean dealtOrReceivedDamage() {
        return this.dealtDamage || this.receivedDamage;
    }
}

class HulkEffect extends OneShotEffect {

    public HulkEffect() {
        super(Outcome.Transform);
        this.staticText = "if Hulk didn't deal or receive damage this turn, exile him, then return him to the battlefield transformed under his owner's control.";
    }

    public HulkEffect(final HulkEffect effect) {
        super(effect);
    }

    @Override
    public HulkEffect copy() {
        return new HulkEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Card sourceCard = game.getCard(source.getSourceId());
        HulkWatcher watcher = game.getState().getWatcher(HulkWatcher.class);
        if (controller != null && sourceCard != null && watcher != null) {
            if (!watcher.dealtOrReceivedDamage()) {
                new ExileAndReturnTransformedSourceEffect(Gender.MALE).apply(game, source);
            }
            return true;
        }
        return false;
    }
}
