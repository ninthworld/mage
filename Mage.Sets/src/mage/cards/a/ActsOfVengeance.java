package mage.cards.a;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SagaAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.combat.GoadTargetEffect;
import mage.constants.Outcome;
import mage.constants.SagaChapter;
import mage.constants.SubType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.targetpointer.FixedTarget;

/**
 *
 * @author NinthWorld
 */
public final class ActsOfVengeance extends CardImpl {

    public ActsOfVengeance(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{B}{R}");
        
        this.subtype.add(SubType.SAGA);

        // <i>(As this Saga enters and after your draw step, add a lore counter. Sacrifice after III.)</i>
        SagaAbility sagaAbility = new SagaAbility(this, SagaChapter.CHAPTER_III);

        // I - Goad each creature your opponents control.
        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_I, SagaChapter.CHAPTER_I, new GoadAllOpponentsEffect());

        // II - Each opponent sacrifices a creature or planeswalker. Any player who can't discards a card.
        // sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_II, SagaChapter.CHAPTER_II, new SacrificeOpponentsEffect());

        // III - Create a 3/3 colorless Sentinel artifact creature token with "{4}: Adapt 2."
        // sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_III, );

        this.addAbility(sagaAbility);
    }

    private ActsOfVengeance(final ActsOfVengeance card) {
        super(card);
    }

    @Override
    public ActsOfVengeance copy() {
        return new ActsOfVengeance(this);
    }
}

class GoadAllOpponentsEffect extends OneShotEffect {

    public GoadAllOpponentsEffect() {
        super(Outcome.Benefit);
        staticText = "Goad each creature your opponents control. <i>(Until your next turn, those creatures attack each combat if able and attack a player other than you if able.)</i>";
    }

    public GoadAllOpponentsEffect(final GoadAllOpponentsEffect effect) {
        super(effect);
    }

    @Override
    public GoadAllOpponentsEffect copy() {
        return new GoadAllOpponentsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent creature : game.getBattlefield().getActivePermanents(StaticFilters.FILTER_PERMANENT_CREATURE, source.getControllerId(), game)) {
            if (!creature.isControlledBy(source.getControllerId()) && game.getPlayer(creature.getControllerId()).hasOpponent(source.getControllerId(), game)) {
                Effect effect = new GoadTargetEffect();
                effect.setTargetPointer(new FixedTarget(creature, game));
                effect.apply(game, source);
            }
        }
        return true;
    }
}
