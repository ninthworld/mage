package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DiesTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.GainSuspendEffect;
import mage.abilities.keyword.HeroicAbility;
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
public final class BuckyBarnes extends CardImpl {

    public BuckyBarnes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.t.TheWinterSoldier.class;

        // <i>Heroic</i> - Whenever you cast a spell that targets Bucky Barnes, you gain 2 life.
        this.addAbility(new HeroicAbility(new GainLifeEffect(2)));

        // When Bucky Barnes dies, exile him with Suspend 2.
        this.addAbility(new DiesTriggeredAbility(new BuckyBarnesSuspendEffect(2)));

        // If Bucky Barnes entered the battlefield from exile, transform him.
        this.addAbility(new TransformAbility());
        this.addAbility(new BuckyBarnesTriggeredAbility());
    }

    private BuckyBarnes(final BuckyBarnes card) {
        super(card);
    }

    @Override
    public BuckyBarnes copy() {
        return new BuckyBarnes(this);
    }
}

class BuckyBarnesSuspendEffect extends OneShotEffect {

    private int suspendCount;

    public BuckyBarnesSuspendEffect(int suspendCount) {
        super(Outcome.Benefit);
        this.suspendCount = suspendCount;
        this.staticText = "exile it with suspend " + suspendCount;
    }

    public BuckyBarnesSuspendEffect(final BuckyBarnesSuspendEffect effect) {
        super(effect);
        this.suspendCount = effect.suspendCount;
    }

    @Override
    public BuckyBarnesSuspendEffect copy() {
        return new BuckyBarnesSuspendEffect(this);
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

class BuckyBarnesTriggeredAbility extends TriggeredAbilityImpl {

    public BuckyBarnesTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public BuckyBarnesTriggeredAbility(BuckyBarnesTriggeredAbility ability) {
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
        return "If Bucky Barnes entered the battlefield from exile, transform him.";
    }

    @Override
    public BuckyBarnesTriggeredAbility copy() {
        return new BuckyBarnesTriggeredAbility(this);
    }
}