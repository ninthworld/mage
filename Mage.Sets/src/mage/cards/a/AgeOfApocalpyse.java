package mage.cards.a;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.SagaAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.ExileAllEffect;
import mage.abilities.effects.common.ReturnFromExileEffect;
import mage.abilities.effects.common.ReturnToBattlefieldUnderOwnerControlTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.continuous.GainControlTargetEffect;
import mage.abilities.keyword.HasteAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.other.OwnerIdPredicate;
import mage.filter.predicate.permanent.ControllerIdPredicate;
import mage.game.ExileZone;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetadjustment.TargetAdjuster;
import mage.target.targetpointer.FixedTarget;

/**
 *
 * @author NinthWorld
 */
public final class AgeOfApocalpyse extends CardImpl {

    public AgeOfApocalpyse(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{W}{B}");
        
        this.subtype.add(SubType.SAGA);

        // <i>(As this Saga enters and after your draw step, add a lore counter. Sacrifice after III.)</i>
        SagaAbility sagaAbility = new SagaAbility(this, SagaChapter.CHAPTER_III);

        // I - Destroy target creature.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_I, SagaChapter.CHAPTER_I, new DestroyTargetEffect(), new TargetCreaturePermanent());

        // II - For each opponent, put target creature card from that player's graveyard onto the battlefield under your control. Those creatures gain haste until end of turn.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_II, SagaChapter.CHAPTER_II, new AgeOfApocalpyseEffect());
        sagaAbility.setTargetAdjuster(AgeOfApocalpyseAdjuster.instance);

        // III - Exile all creatures, then return them to the battlefield under their owners' control.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_III, new AgeOfApocalpyseExileEffect());
    }

    private AgeOfApocalpyse(final AgeOfApocalpyse card) {
        super(card);
    }

    @Override
    public AgeOfApocalpyse copy() {
        return new AgeOfApocalpyse(this);
    }
}

enum AgeOfApocalpyseAdjuster implements TargetAdjuster {
    instance;

    @Override
    public void adjustTargets(Ability ability, Game game) {
        ability.getTargets().clear();
        for (UUID opponentId : game.getOpponents(ability.getControllerId())) {
            Player opponent = game.getPlayer(opponentId);
            if (opponent != null) {
                FilterCreatureCard filter = new FilterCreatureCard("Creature card in graveyard of player " + opponent.getName());
                filter.add(new OwnerIdPredicate(opponentId));
                TargetCardInGraveyard targetCard = new TargetCardInGraveyard(filter);
                ability.addTarget(targetCard);
            }
        }
    }
}

class AgeOfApocalpyseEffect extends OneShotEffect {

    public AgeOfApocalpyseEffect() {
        super(Outcome.GainControl);
        this.staticText = "For each opponent, put target creature card from that player's graveyard onto the battlefield under your control. Those creatures gain haste until end of turn";
    }

    public AgeOfApocalpyseEffect(final AgeOfApocalpyseEffect effect) {
        super(effect);
    }

    @Override
    public AgeOfApocalpyseEffect copy() {
        return new AgeOfApocalpyseEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        for (Target target : source.getTargets()) {
            if (target.getFirstTarget() != null) {
                Card card = game.getCard(target.getFirstTarget());
                if (card != null) {
                    if (controller.moveCards(card, Zone.BATTLEFIELD, source, game)) {
                        ContinuousEffect effect = new GainAbilityTargetEffect(HasteAbility.getInstance(), Duration.EndOfTurn);
                        effect.setTargetPointer(new FixedTarget(target.getFirstTarget()));
                        game.addEffect(effect, source);
                    }
                }
            }
        }
        return true;
    }
}

class AgeOfApocalpyseExileEffect extends OneShotEffect {

    public AgeOfApocalpyseExileEffect() {
        super(Outcome.Detriment);
        staticText = "Exile all creatures, then return them to the battlefield under their owners' control";
    }

    public AgeOfApocalpyseExileEffect(final AgeOfApocalpyseExileEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        MageObject sourceObject = game.getObject(source.getSourceId());
        Player controller = game.getPlayer(source.getControllerId());
        if (sourceObject != null && controller != null) {
            Set<Card> toExile = new HashSet<>();
            toExile.addAll(game.getBattlefield().getActivePermanents(StaticFilters.FILTER_PERMANENT_CREATURE, source.getControllerId(), source.getSourceId(), game));
            controller.moveCardsToExile(toExile, source, game, true, source.getSourceId(), sourceObject.getIdName());
            ExileZone exile = game.getExile().getExileZone(source.getSourceId());
            if (exile != null && !exile.isEmpty()) {
                new AgeOfApocalpyseReturnFromExileEffect().apply(game, source);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public AgeOfApocalpyseExileEffect copy() {
        return new AgeOfApocalpyseExileEffect(this);
    }
}

class AgeOfApocalpyseReturnFromExileEffect extends OneShotEffect {

    public AgeOfApocalpyseReturnFromExileEffect() {
        super(Outcome.PutCardInPlay);
        staticText = "return them to the battlefield under their owners' control";
    }

    public AgeOfApocalpyseReturnFromExileEffect(final AgeOfApocalpyseReturnFromExileEffect effect) {
        super(effect);
    }

    @Override
    public AgeOfApocalpyseReturnFromExileEffect copy() {
        return new AgeOfApocalpyseReturnFromExileEffect(this);
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
