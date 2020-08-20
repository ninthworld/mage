package mage.cards.b;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.*;
import mage.cards.Card;
import mage.constants.*;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;
import mage.game.ExileZone;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Blink extends CardImpl {

    public Blink(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.NOMAD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // {1}{W}: Exile target nonland permanent you control, then return it to the battlefield under its owner's control.
        Ability ability = new SimpleActivatedAbility(new ExileTargetForSourceEffect(), new ManaCostsImpl("{1}{W}"));
        ability.addEffect(new ReturnToBattlefieldUnderYourControlTargetEffect(true));
        ability.addTarget(new TargetPermanent(StaticFilters.FILTER_CONTROLLED_PERMANENT_NON_LAND));
        this.addAbility(ability);

        // {3}{W}, {T}: Exile all nonland permanents you control, then return them to the battlefield under their owners' control.
        this.addAbility(new SimpleActivatedAbility(new BlinkExileEffect(), new CompositeCost(new ManaCostsImpl("{3}{W}"), new TapSourceCost(), "{3}{W}, {T}")));
    }

    private Blink(final Blink card) {
        super(card);
    }

    @Override
    public Blink copy() {
        return new Blink(this);
    }
}

class BlinkExileEffect extends OneShotEffect {

    public BlinkExileEffect() {
        super(Outcome.Detriment);
        staticText = "Exile all nonland permanents you control, then return those cards to the battlefield under their owners' control";
    }

    public BlinkExileEffect(final BlinkExileEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        MageObject sourceObject = game.getObject(source.getSourceId());
        Player controller = game.getPlayer(source.getControllerId());
        if (sourceObject != null && controller != null) {
            Set<Card> toExile = new HashSet<>();
            toExile.addAll(game.getBattlefield().getActivePermanents(StaticFilters.FILTER_CONTROLLED_PERMANENT_NON_LAND, source.getControllerId(), source.getSourceId(), game));
            controller.moveCardsToExile(toExile, source, game, true, source.getSourceId(), sourceObject.getIdName());
            ExileZone exile = game.getExile().getExileZone(source.getSourceId());
            if (exile != null && !exile.isEmpty()) {
                new BlinkReturnFromExileEffect().apply(game, source);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public BlinkExileEffect copy() {
        return new BlinkExileEffect(this);
    }
}

class BlinkReturnFromExileEffect extends OneShotEffect {

    public BlinkReturnFromExileEffect() {
        super(Outcome.PutCardInPlay);
        staticText = "return those cards to the battlefield under their owners' control";
    }

    public BlinkReturnFromExileEffect(final BlinkReturnFromExileEffect effect) {
        super(effect);
    }

    @Override
    public BlinkReturnFromExileEffect copy() {
        return new BlinkReturnFromExileEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            ExileZone exile = game.getExile().getExileZone(source.getSourceId());
            if (exile != null) {
                controller.moveCards(exile.getCards(game), Zone.BATTLEFIELD, source, game, false, false, true, null);
            }
            return true;
        }
        return false;
    }

}
