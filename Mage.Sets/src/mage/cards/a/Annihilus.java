package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SacrificePermanentTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.cards.Card;
import mage.constants.*;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.AnnihilatorAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.InsectAnnihilatorToken;
import mage.players.Player;

/**
 *
 * @author NinthWorld
 */
public final class Annihilus extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent();

    static {
        filter.add(TargetController.OPPONENT.getControllerPredicate());
    }

    public Annihilus(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{B}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.INSECT);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(6);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Annihilator 2
        this.addAbility(new AnnihilatorAbility(2));

        // Whenever an opponent sacrifices a permanent, create a 1/1 black Insect creature token with annihilator 1.
        // TODO: Fix sacrifice trigger from you to opponent (filter doesnt work)
        this.addAbility(new SacrificePermanentTriggeredAbility(new CreateTokenEffect(new InsectAnnihilatorToken()), filter));

        // When Annihilus dies or is put into exile from the battlefield, you may put him into his owner's library third from the top.
        this.addAbility(new AnnihilusTriggeredAbility());
    }

    private Annihilus(final Annihilus card) {
        super(card);
    }

    @Override
    public Annihilus copy() {
        return new Annihilus(this);
    }
}

class AnnihilusTriggeredAbility extends TriggeredAbilityImpl {

    public AnnihilusTriggeredAbility() {
        super(Zone.ALL, new AnnihilusEffect(), true);
    }

    public AnnihilusTriggeredAbility(AnnihilusTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public AnnihilusTriggeredAbility copy() {
        return new AnnihilusTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        Permanent permanent = zEvent.getTarget();
        return (permanent != null
                && permanent.getId().equals(this.getSourceId())
                && zEvent.getFromZone() == Zone.BATTLEFIELD
                && ((zEvent.getToZone() == Zone.GRAVEYARD && permanent.isOwnedBy(permanent.getControllerId())) || zEvent.getToZone() == Zone.EXILED));
    }

    @Override
    public String getRule() {
        return "When {this} is put into your graveyard or exile from the battlefield, " + super.getRule();
    }
}

class AnnihilusEffect extends OneShotEffect {

    public AnnihilusEffect() {
        super(Outcome.ReturnToHand);
        staticText = "you may put it into your library third from the top";
    }

    public AnnihilusEffect(final AnnihilusEffect effect) {
        super(effect);
    }

    @Override
    public AnnihilusEffect copy() {
        return new AnnihilusEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        Card card = (Card) source.getSourceObjectIfItStillExists(game);
        if (card != null) {
            controller.putCardOnTopXOfLibrary(card, game, source, 3);
        }
        return true;
    }
}
