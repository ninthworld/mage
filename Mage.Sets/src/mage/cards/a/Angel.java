package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileSourceEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.continuous.GainSuspendEffect;
import mage.abilities.keyword.SuspendAbility;
import mage.abilities.keyword.TransformAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Angel extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(AnotherPredicate.instance);
    }

    public Angel(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ANGEL);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.a.AngelArchangel.class;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever Angel attacks, another target creature gains flying until end of turn.
        Ability ability = new AttacksTriggeredAbility(new GainAbilityTargetEffect(FlyingAbility.getInstance(), Duration.EndOfTurn), false);
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);

        // {B}: Exile Angel with suspend 1.
        this.addAbility(new SimpleActivatedAbility(new AngelSuspendEffect(1), new ManaCostsImpl("{B}")));

        // If Angel entered the battlefield from exile, transform him.
        this.addAbility(new TransformAbility());
        this.addAbility(new AngelTriggeredAbility());
    }

    private Angel(final Angel card) {
        super(card);
    }

    @Override
    public Angel copy() {
        return new Angel(this);
    }
}

class AngelSuspendEffect extends OneShotEffect {

    private int suspendCount;

    public AngelSuspendEffect(int suspendCount) {
        super(Outcome.Benefit);
        this.suspendCount = suspendCount;
        this.staticText = "exile it with suspend " + suspendCount;
    }

    public AngelSuspendEffect(final AngelSuspendEffect effect) {
        super(effect);
        this.suspendCount = effect.suspendCount;
    }

    @Override
    public AngelSuspendEffect copy() {
        return new AngelSuspendEffect(this);
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

class AngelTriggeredAbility extends TriggeredAbilityImpl {

    public AngelTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public AngelTriggeredAbility(AngelTriggeredAbility ability) {
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
        return "If Angel entered the battlefield from exile, transform him.";
    }

    @Override
    public AngelTriggeredAbility copy() {
        return new AngelTriggeredAbility(this);
    }
}