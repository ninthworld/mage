package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.ControlsCreatureGreatestPowerCondition;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileAllEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.discard.DiscardHandControllerEffect;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.choices.Choice;
import mage.choices.ChoiceImpl;
import mage.choices.ChoiceLeftOrRight;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.util.CardUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author NinthWorld
 */
public final class ApocalypseMCU extends CardImpl {

    private static final FilterCard filter = new FilterCard("creature card from a graveyard");

    static {
        filter.add(CardType.CREATURE.getPredicate());
    }

    public ApocalypseMCU(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{B}{G}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.SHAPESHIFTER);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(7);
        this.toughness = new MageInt(6);

        // Menace
        this.addAbility(new MenaceAbility());

        // At the beginning of your upkeep, if you control a creature with the greatest power among creatures on the battlefield, put target creature card from a graveyard onto the battlefield under your control. You may transform it.
        Ability ability = new BeginningOfUpkeepTriggeredAbility(
                Zone.BATTLEFIELD,
                new ConditionalOneShotEffect(
                        new ReturnFromGraveyardToBattlefieldAndTransformTargetEffect(),
                        ControlsCreatureGreatestPowerCondition.instance),
                TargetController.YOU, false);
        ability.addTarget(new TargetCardInGraveyard(filter));
        this.addAbility(ability);

        // Sacrifice a creature: Apocalypse gets +2/+2 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(2, 2, Duration.EndOfTurn), new SacrificeTargetCost(new TargetControlledCreaturePermanent())));

        // When Apocalypse dies or is put into exile from the battlefield, you may put him into his owner’s library third from the top.
        this.addAbility(new ApocalypseTriggeredAbility());
    }

    public ApocalypseMCU(final ApocalypseMCU card) {
        super(card);
    }

    @Override
    public ApocalypseMCU copy() {
        return new ApocalypseMCU(this);
    }
}

class ReturnFromGraveyardToBattlefieldAndTransformTargetEffect extends OneShotEffect {

    public ReturnFromGraveyardToBattlefieldAndTransformTargetEffect() {
        super(Outcome.PutCreatureInPlay);
        staticText = "put target creature card from a graveyard onto the battlefield under your control. You may transform it.";
    }

    public ReturnFromGraveyardToBattlefieldAndTransformTargetEffect(final ReturnFromGraveyardToBattlefieldAndTransformTargetEffect effect) {
        super(effect);
    }

    @Override
    public ReturnFromGraveyardToBattlefieldAndTransformTargetEffect copy() {
        return new ReturnFromGraveyardToBattlefieldAndTransformTargetEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Set<Card> cardsToMove = new HashSet<>();
            for (UUID targetId : getTargetPointer().getTargets(game, source)) {
                Card card = game.getCard(targetId);
                if (card != null && game.getState().getZone(card.getId()) == Zone.GRAVEYARD) {
                    cardsToMove.add(card);
                }
            }
            if (controller.moveCards(cardsToMove, Zone.BATTLEFIELD, source, game, false, false, false, null)) {
                for (Card card : cardsToMove) {
                    Choice choice = new ChoiceTransform(card.getName());
                    if (card != null && controller.choose(Outcome.Transform, choice, game) && choice.isChosen() && choice.getChoice().equals("Yes")) {
                        card.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new TransformSourceEffect(true)));
                    }
                }
            }
            return true;
        }
        return false;
    }
}

class ChoiceTransform extends ChoiceImpl {

    public ChoiceTransform(String name) {
        super(true);
        this.choices.add("Yes");
        this.choices.add("No");
        this.message = "Transform " + name + "?";
    }

    public ChoiceTransform(final ChoiceTransform choice) {
        super(choice);
    }

    @Override
    public ChoiceTransform copy() {
        return new ChoiceTransform(this);
    }

}

class ApocalypseTriggeredAbility extends TriggeredAbilityImpl {

    public ApocalypseTriggeredAbility() {
        super(Zone.ALL, new ApocalypseEffect(), true);
    }

    public ApocalypseTriggeredAbility(ApocalypseTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public ApocalypseTriggeredAbility copy() {
        return new ApocalypseTriggeredAbility(this);
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

class ApocalypseEffect extends OneShotEffect {

    public ApocalypseEffect() {
        super(Outcome.ReturnToHand);
        staticText = "you may put it into your library third from the top";
    }

    public ApocalypseEffect(final ApocalypseEffect effect) {
        super(effect);
    }

    @Override
    public ApocalypseEffect copy() {
        return new ApocalypseEffect(this);
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
