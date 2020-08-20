package mage.cards.b;

import java.util.*;

import mage.MageInt;
import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCosts;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CastSourceTriggeredAbility;
import mage.abilities.effects.common.ExileAllEffect;
import mage.abilities.effects.common.ReturnFromExileEffect;
import mage.abilities.effects.common.continuous.BecomesFaceDownCreatureEffect;
import mage.cards.Card;
import mage.constants.*;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPlayer;

/**
 *
 * @author NinthWorld
 */
public final class Beyonder extends CardImpl {

    public Beyonder(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{W}{W}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.GOD);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // When you cast this spell, exile all creatures target player controls. At the beginning of the next end step, return those creatures to the battlefield under their owners' control.
        Ability ability = new CastSourceTriggeredAbility(new BeyonderEffect());
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);
    }

    private Beyonder(final Beyonder card) {
        super(card);
    }

    @Override
    public Beyonder copy() {
        return new Beyonder(this);
    }
}

class BeyonderEffect extends OneShotEffect {

    public BeyonderEffect() {
        super(Outcome.Benefit);
        this.staticText = "exile all creatures target player controls. At the beginning of the next end step, return those creatures to the battlefield under their owners' control.";
    }

    public BeyonderEffect(final BeyonderEffect effect) {
        super(effect);
    }

    @Override
    public BeyonderEffect copy() {
        return new BeyonderEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Player targetPlayer = game.getPlayer(getTargetPointer().getFirst(game, source));
        if (controller != null && targetPlayer != null) {
            List<Card> cardsToReturn = new ArrayList<>();
            for (Permanent permanent : game.getBattlefield().getAllActivePermanents(StaticFilters.FILTER_PERMANENT_CREATURE, targetPlayer.getId(), game)) {
                if (permanent != null) {
                    Card card = permanent.getMainCard();
                    if (card != null && controller.moveCardToExileWithInfo(permanent.getMainCard(), null, "", source.getSourceId(), game, Zone.BATTLEFIELD, true)) {
                        cardsToReturn.add(card);
                    }
                }
            }
            if (cardsToReturn.isEmpty()) {
                return true;
            }

            game.addDelayedTriggeredAbility(new AtTheBeginOfNextEndStepDelayedTriggeredAbility(new BeyonderReturnEffect(cardsToReturn)), source);

            return true;
        }
        return false;
    }
}

class BeyonderReturnEffect extends OneShotEffect {

    private List<Card> cards;

    public BeyonderReturnEffect(List<Card> cards) {
        super(Outcome.Benefit);
        this.staticText = "";
        this.cards = cards;
    }

    public BeyonderReturnEffect(final BeyonderReturnEffect effect) {
        super(effect);
        this.cards = effect.cards;
    }

    @Override
    public BeyonderReturnEffect copy() {
        return new BeyonderReturnEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null && cards != null) {
            if (!cards.isEmpty()) {
                Set<Card> toBattlefield = new LinkedHashSet();
                toBattlefield.addAll(cards);
                controller.moveCards(toBattlefield, Zone.BATTLEFIELD, source, game, false, false, true, null);
            }
            return true;
        }
        return false;
    }
}