package mage.cards.d;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.keyword.ArsenalAbility;
import mage.constants.*;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.game.Game;
import mage.game.events.FlipCoinEvent;
import mage.game.events.GameEvent;

/**
 *
 * @author NinthWorld
 */
public final class Domino extends CardImpl {

    public Domino(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.MERCENARY);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Arsenal 1
        this.addAbility(new ArsenalAbility(1));

        // If you would flip a coin, instead flip two coins and ignore one.
        this.addAbility(new SimpleStaticAbility(new DominoEffect()));
    }

    private Domino(final Domino card) {
        super(card);
    }

    @Override
    public Domino copy() {
        return new Domino(this);
    }
}


class DominoEffect extends ReplacementEffectImpl {

    public DominoEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "If you would flip a coin, instead flip two coins and ignore one.";
    }

    private DominoEffect(final DominoEffect effect) {
        super(effect);
    }

    @Override
    public DominoEffect copy() {
        return new DominoEffect(this);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        FlipCoinEvent flipCoinEvent = (FlipCoinEvent) event;
        flipCoinEvent.setFlipCount(2 * flipCoinEvent.getFlipCount());
        return false;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.FLIP_COIN;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        return source.isControlledBy(event.getPlayerId());
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return false;
    }
}
