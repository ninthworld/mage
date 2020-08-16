package mage.cards.a;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SagaAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DestroyAllEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllOfChosenSubtypeEffect;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterPermanentCard;
import mage.filter.predicate.other.OwnerIdPredicate;
import mage.game.Game;
import mage.game.permanent.token.InsectAnnihilatorToken;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInYourGraveyard;

/**
 *
 * @author NinthWorld
 */
public final class Annihilation extends CardImpl {

    private static final FilterPermanent filterYourInsects = new FilterPermanent();
    private static final FilterPermanent filterAllInsects = new FilterPermanent();

    static {
        filterYourInsects.add(TargetController.YOU.getControllerPredicate());
        filterYourInsects.add(SubType.INSECT.getPredicate());
        filterAllInsects.add(SubType.INSECT.getPredicate());
    }

    public Annihilation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{X}{B}{B}");
        
        this.subtype.add(SubType.SAGA);

        // <i>(As this Saga enters and after your draw step, add a lore counter. Sacrifice after IV.)</i>
        SagaAbility sagaAbility = new SagaAbility(this, SagaChapter.CHAPTER_IV);

        // I - Create X 1/1 black Insect creature tokens with annihilator 1.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_I, SagaChapter.CHAPTER_I, new CreateTokenEffect(new InsectAnnihilatorToken(), this.getManaCost().getX()));

        // II - Insects you control gain menace until end of turn.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_II, SagaChapter.CHAPTER_II, new GainAbilityAllEffect(new MenaceAbility(), Duration.EndOfTurn, filterYourInsects));

        // III - Each opponent may return up to one target permanent from their graveyard to the battlefield.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_III, SagaChapter.CHAPTER_III, new AnnihilationEffect());

        // IV - Destroy all Insects.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_IV, new DestroyAllEffect(filterAllInsects));
    }

    private Annihilation(final Annihilation card) {
        super(card);
    }

    @Override
    public Annihilation copy() {
        return new Annihilation(this);
    }
}

class AnnihilationEffect extends OneShotEffect {

    public AnnihilationEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "Each opponent may return up to one target permanent from their graveyard to the battlefield";

    }

    public AnnihilationEffect(final AnnihilationEffect effect) {
        super(effect);
    }

    @Override
    public AnnihilationEffect copy() {
        return new AnnihilationEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            for (UUID playerId : game.getOpponents(controller.getId())) {
                Player opponent = game.getPlayer(playerId);
                if (opponent != null) {
                    FilterCard filter = new FilterPermanentCard("permanent card from your graveyard");
                    filter.add(new OwnerIdPredicate(opponent.getId()));
                    Target targetCardOpponent = new TargetCardInGraveyard(filter);

                    if (targetCardOpponent.canChoose(source.getSourceId(), opponent.getId(), game)) {
                        if (opponent.chooseUse(outcome, "Return a permanent card from your graveyard to the battlefield?", source, game)) {
                            if (opponent.chooseTarget(outcome, targetCardOpponent, source, game)) {
                                Card card = game.getCard(targetCardOpponent.getFirstTarget());
                                if (card != null) {
                                    opponent.moveCards(card, Zone.BATTLEFIELD, source, game);
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        return false;
    }

    private boolean returnCreatureFromGraveToBattlefield(Player player, Ability source, Game game) {
        Target target = new TargetCardInYourGraveyard(StaticFilters.FILTER_CARD_CREATURE);
        target.setNotTarget(false);
        if (target.canChoose(source.getSourceId(), source.getControllerId(), game)) {
            if (player.chooseTarget(outcome, target, source, game)) {
                Card card = game.getCard(target.getFirstTarget());
                if (card != null) {
                    return player.moveCards(card, Zone.BATTLEFIELD, source, game);
                }
            }
        }
        return false;
    }
}