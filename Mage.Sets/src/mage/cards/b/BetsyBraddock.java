package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DiesTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.GainSuspendEffect;
import mage.abilities.effects.keyword.ScryEffect;
import mage.abilities.keyword.SuspendAbility;
import mage.abilities.keyword.TransformAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;

/**
 *
 * @author NinthWorld
 */
public final class BetsyBraddock extends CardImpl {

    public BetsyBraddock(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.TELEPATH);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.p.Psylocke.class;

        // {1}{U}: Scry 1.
        this.addAbility(new SimpleActivatedAbility(new ScryEffect(1), new ManaCostsImpl("{1}{U}")));

        // When Betsy Braddock dies, exile her with suspend 1.
        this.addAbility(new DiesTriggeredAbility(new BetsyBraddockSuspendEffect(1)));

        // If Betsy Braddock entered the battlefield from exile, transform her.
        this.addAbility(new TransformAbility());
        this.addAbility(new BetsyBraddockTriggeredAbility());
    }

    private BetsyBraddock(final BetsyBraddock card) {
        super(card);
    }

    @Override
    public BetsyBraddock copy() {
        return new BetsyBraddock(this);
    }
}

class BetsyBraddockSuspendEffect extends OneShotEffect {

    private int suspendCount;

    public BetsyBraddockSuspendEffect(int suspendCount) {
        super(Outcome.Benefit);
        this.suspendCount = suspendCount;
        this.staticText = "exile it with suspend " + suspendCount;
    }

    public BetsyBraddockSuspendEffect(final BetsyBraddockSuspendEffect effect) {
        super(effect);
        this.suspendCount = effect.suspendCount;
    }

    @Override
    public BetsyBraddockSuspendEffect copy() {
        return new BetsyBraddockSuspendEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Card card = game.getCard(source.getSourceId());
        if (controller != null && card != null) {
            if (game.getState().getZone(card.getId()) == Zone.GRAVEYARD) {
                UUID exileId = SuspendAbility.getSuspendExileId(controller.getId(), game);
                controller.moveCardToExileWithInfo(card, exileId, "Suspended cards of " + controller.getName(), source.getSourceId(), game, Zone.GRAVEYARD, true);
                card.addCounters(CounterType.TIME.createInstance(this.suspendCount), source, game);
                game.addEffect(new GainSuspendEffect(new MageObjectReference(card, game)), source);
            }
            return true;
        }
        return false;
    }
}

class BetsyBraddockTriggeredAbility extends TriggeredAbilityImpl {

    public BetsyBraddockTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public BetsyBraddockTriggeredAbility(BetsyBraddockTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent permanent = game.getPermanent(event.getSourceId());
        return (permanent != null && ((EntersTheBattlefieldEvent) event).getFromZone() == Zone.EXILED);
    }

    @Override
    public String getRule() {
        return "If {this} entered the battlefield from exile, transform it.";
    }

    @Override
    public BetsyBraddockTriggeredAbility copy() {
        return new BetsyBraddockTriggeredAbility(this);
    }
}