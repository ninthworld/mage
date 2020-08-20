package mage.cards.b;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.AsThoughManaEffect;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.combat.CantBeBlockedSourceEffect;
import mage.cards.*;
import mage.constants.*;
import mage.game.ExileZone;
import mage.game.Game;
import mage.players.ManaPoolItem;
import mage.players.Player;
import mage.target.targetpointer.FixedTarget;
import mage.util.CardUtil;

/**
 *
 * @author NinthWorld
 */
public final class BlackWidow extends CardImpl {

    protected static final String VALUE_PREFIX = "ExileZones";

    public BlackWidow(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U/B}{U/B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.AGENT);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Whenever Black Widow deals combat damage to a player, exile the top card of that player's library face down.
        // For as long as that card remains exiled, you may look at it, you may cast it, and you may spend mana as though it were mana of any type to cast that spell.
        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new BlackWidowEffect(), false, true));
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new BlackWidowLookEffect()));

        // {2}{U}: Black Widow can't be blocked this turn.
        this.addAbility(new SimpleActivatedAbility(new CantBeBlockedSourceEffect(Duration.EndOfTurn), new ManaCostsImpl("{2}{U}")));
    }

    private BlackWidow(final BlackWidow card) {
        super(card);
    }

    @Override
    public BlackWidow copy() {
        return new BlackWidow(this);
    }
}

class BlackWidowEffect extends OneShotEffect {

    public BlackWidowEffect() {
        super(Outcome.Benefit);
        this.staticText = "exile the top card of that player's library face down. For as long as that card remains exiled, you may look at it, you may cast it, and you may spend mana as though it were mana of any type to cast that spell.";
    }

    public BlackWidowEffect(final BlackWidowEffect effect) {
        super(effect);
    }

    @Override
    public BlackWidowEffect copy() {
        return new BlackWidowEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Player targetPlayer = game.getPlayer(getTargetPointer().getFirst(game, source));
        MageObject sourceObject = source.getSourceObject(game);
        if (controller != null && targetPlayer != null && sourceObject != null) {
            Card card = targetPlayer.getLibrary().getFromTop(game);
            if (card != null) {
                UUID exileZoneId = CardUtil.getExileZoneId(game, source.getSourceId(), source.getSourceObjectZoneChangeCounter());
                card.setFaceDown(true, game);
                if (controller.moveCardsToExile(card, source, game, false, exileZoneId, sourceObject.getIdName())) {
                    card.setFaceDown(true, game);
                    Set<UUID> exileZones = (Set<UUID>) game.getState().getValue(BlackWidow.VALUE_PREFIX + source.getSourceId().toString());
                    if (exileZones == null) {
                        exileZones = new HashSet<>();
                        game.getState().setValue(BlackWidow.VALUE_PREFIX + source.getSourceId().toString(), exileZones);
                    }
                    exileZones.add(exileZoneId);

                    ContinuousEffect effect = new BlackWidowCastFromExileEffect();
                    effect.setTargetPointer(new FixedTarget(card.getId(), game));
                    game.addEffect(effect, source);

                    effect = new BlackWidowSpendAnyManaEffect();
                    effect.setTargetPointer(new FixedTarget(card.getId(), game));
                    game.addEffect(effect, source);
                }
            }
            return true;
        }
        return false;
    }
}

class BlackWidowCastFromExileEffect extends AsThoughEffectImpl {

    public BlackWidowCastFromExileEffect() {
        super(AsThoughEffectType.PLAY_FROM_NOT_OWN_HAND_ZONE, Duration.Custom, Outcome.Benefit);
        staticText = "You may cast that card for as long as it remains exiled, and you may spend mana as though it were mana of any color to cast that spell";
    }

    public BlackWidowCastFromExileEffect(final BlackWidowCastFromExileEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public BlackWidowCastFromExileEffect copy() {
        return new BlackWidowCastFromExileEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability source, UUID affectedControllerId, Game game) {
        UUID targetId = getTargetPointer().getFirst(game, source);
        if (targetId == null) {
            this.discard();
        } else if (objectId.equals(targetId)
                && affectedControllerId.equals(source.getControllerId())) {
            Card card = game.getCard(objectId);
            return card != null && !card.isLand();
        }
        return false;
    }
}

class BlackWidowSpendAnyManaEffect extends AsThoughEffectImpl implements AsThoughManaEffect {

    public BlackWidowSpendAnyManaEffect() {
        super(AsThoughEffectType.SPEND_OTHER_MANA, Duration.Custom, Outcome.Benefit);
        staticText = "you may spend mana as though it were mana of any color to cast it";
    }

    public BlackWidowSpendAnyManaEffect(final BlackWidowSpendAnyManaEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public BlackWidowSpendAnyManaEffect copy() {
        return new BlackWidowSpendAnyManaEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability source, UUID affectedControllerId, Game game) {
        Card theCard = game.getCard(objectId);
        if (theCard == null) {
            return false;
        }
        Card mainCard = theCard.getMainCard();
        if (mainCard == null) {
            return false;
        }
        objectId = mainCard.getId(); // for split cards
        if (objectId.equals(((FixedTarget) getTargetPointer()).getTarget())
                && game.getState().getZoneChangeCounter(objectId) <= ((FixedTarget) getTargetPointer()).getZoneChangeCounter() + 1) {
            // if the card moved from exile to spell the zone change counter is increased by 1 (effect must applies before and on stack, use isCheckPlayableMode?)
            return source.isControlledBy(affectedControllerId);
        } else if (((FixedTarget) getTargetPointer()).getTarget().equals(objectId)) {
            // object has moved zone so effect can be discarted
            this.discard();
        }
        return false;
    }

    @Override
    public ManaType getAsThoughManaType(ManaType manaType, ManaPoolItem mana, UUID affectedControllerId, Ability source, Game game) {
        return mana.getFirstAvailable();
    }
}

class BlackWidowLookEffect extends AsThoughEffectImpl {

    public BlackWidowLookEffect() {
        super(AsThoughEffectType.LOOK_AT_FACE_DOWN, Duration.EndOfGame, Outcome.Benefit);
        staticText = "You may look at the cards exiled with {this}";
    }

    public BlackWidowLookEffect(final BlackWidowLookEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public BlackWidowLookEffect copy() {
        return new BlackWidowLookEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability source, UUID affectedControllerId, Game game) {
        Card theCard = game.getCard(objectId);
        if (theCard == null) {
            return false;
        }
        Card mainCard = theCard.getMainCard();
        if (mainCard == null) {
            return false;
        }
        objectId = mainCard.getId(); // for split cards
        if (affectedControllerId.equals(source.getControllerId())
                && game.getState().getZone(objectId) == Zone.EXILED) {
            Player controller = game.getPlayer(source.getControllerId());
            MageObject sourceObject = source.getSourceObject(game);
            if (controller != null
                    && sourceObject != null) {
                Card card = game.getCard(objectId);
                if (card != null
                        && card.isFaceDown(game)) {
                    Set<UUID> exileZones = (Set<UUID>) game.getState().getValue(
                            BlackWidow.VALUE_PREFIX + source.getSourceId().toString());
                    if (exileZones != null) {
                        for (ExileZone exileZone : game.getExile().getExileZones()) {
                            if (exileZone.contains(objectId)) {
                                if (!exileZones.contains(exileZone.getId())) {
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
