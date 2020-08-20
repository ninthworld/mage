package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.costs.mana.VariableManaCost;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.LoseLifeDefendingPlayerEffect;
import mage.abilities.effects.common.RegenerateSourceEffect;
import mage.cards.Card;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.AfflictAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterCard;
import mage.filter.predicate.permanent.DefendingPlayerControlsPredicate;
import mage.game.Game;
import mage.players.Player;
import mage.target.Target;
import mage.target.Targets;
import mage.target.common.TargetCardInGraveyard;
import mage.target.targetadjustment.XTargetsAdjuster;

/**
 *
 * @author NinthWorld
 */
public final class Blackheart extends CardImpl {

    private static final FilterCard filter = new FilterCard("cards from defending player's graveyard");

    static {
        filter.add(DefendingPlayerControlsPredicate.instance);
    }

    public Blackheart(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{B}{B}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.DEMON);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        // Afflict 5
        this.addAbility(new AfflictAbility(5));

        // Whenever Blackheart deals combat damage to a player, you may pay {X}. If you do, exile up to X target cards from defending player's graveyard. That player loses 1 life for each card exiled this way.
        Ability ability = new DealsCombatDamageToAPlayerTriggeredAbility(new DoIfCostPaid(new BlackheartEffect(), new VariableManaCost()), false);
        ability.addTarget(new TargetCardInGraveyard(filter));
        ability.setTargetAdjuster(XTargetsAdjuster.instance);
        this.addAbility(ability);

        // {1}{B}: Regenerate Blackheart.
        this.addAbility(new SimpleActivatedAbility(new RegenerateSourceEffect(), new ManaCostsImpl("{1}{B}")));
    }

    private Blackheart(final Blackheart card) {
        super(card);
    }

    @Override
    public Blackheart copy() {
        return new Blackheart(this);
    }
}

class BlackheartEffect extends OneShotEffect {

    public BlackheartEffect() {
        super(Outcome.Benefit);
        this.staticText = "exile up to X target cards from defending player's graveyard. That player loses 1 life for each card exiled this way.";
    }

    public BlackheartEffect(BlackheartEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Target target = source.getTargets().get(0);
            if (target != null) {
                int exileCount = 0;
                for (UUID targetId : target.getTargets()) {
                    Card card = game.getCard(targetId);
                    if (card != null && controller.moveCardsToExile(card, source, game, false, null, "")) {
                        exileCount++;
                    }
                }
                if (exileCount > 0) {
                    new LoseLifeDefendingPlayerEffect(exileCount, false).apply(game, source);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public BlackheartEffect copy() {
        return new BlackheartEffect(this);
    }
}
