package mage.cards.c;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.VariableManaCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.effects.common.search.SearchLibraryPutOnLibraryEffect;
import mage.abilities.keyword.EquipAbility;
import mage.cards.*;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.mageobject.ConvertedManaCostPredicate;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Cerebro extends CardImpl {

    private static final FilterControlledCreaturePermanent filterTelepath = new FilterControlledCreaturePermanent("Telepath");

    static {
        filterTelepath.add(SubType.TELEPATH.getPredicate());
    }

    public Cerebro(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature has "{X}, {T}: Search your library for a Mutant creature card with converted mana cost X or less, reveal it, then shuffle your library and put that card on top of it."
        Ability ability = new SimpleActivatedAbility(
                new CerebroSearchEffect(),
                new VariableManaCost());
        ability.addCost(new TapSourceCost());
        this.addAbility(new SimpleStaticAbility(new GainAbilityAttachedEffect(ability, AttachmentType.EQUIPMENT)));

        // Equip Telepath {1}
        this.addAbility(new EquipAbility(Outcome.AddAbility, new GenericManaCost(0), new TargetControlledCreaturePermanent(filterTelepath)));

        // Equip {3}
        this.addAbility(new EquipAbility(3));
    }

    private Cerebro(final Cerebro card) {
        super(card);
    }

    @Override
    public Cerebro copy() {
        return new Cerebro(this);
    }
}

class CerebroSearchEffect extends OneShotEffect {

    public CerebroSearchEffect() {
        super(Outcome.DrawCard);
        staticText = "Search your library for a Mutant creature card with converted mana cost X or less, reveal it, then shuffle your library and put that card on top of it";
    }

    public CerebroSearchEffect(final CerebroSearchEffect effect) {
        super(effect);
    }

    @Override
    public CerebroSearchEffect copy() {
        return new CerebroSearchEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player != null) {

            FilterCard filter = new FilterCard("Mutant creature card with converted mana cost X or less");
            filter.add(CardType.CREATURE.getPredicate());
            filter.add(SubType.MUTANT.getPredicate());
            filter.add(new ConvertedManaCostPredicate(ComparisonType.FEWER_THAN, source.getManaCostsToPay().getX() + 1));

            TargetCardInLibrary target = new TargetCardInLibrary(filter);
            if (player.searchLibrary(target, source, game) && !target.getTargets().isEmpty()) {
                Card card = player.getLibrary().getCard(target.getFirstTarget(), game);
                if (card != null) {
                    Cards cards = new CardsImpl();
                    cards.add(card);
                    String name = "Reveal";
                    Card sourceCard = game.getCard(source.getSourceId());
                    if (sourceCard != null) {
                        name = sourceCard.getName();
                    }
                    player.revealCards(name, cards, game);
                    player.shuffleLibrary(source, game);
                    player.getLibrary().putOnTop(card, game);
                    return true;
                }
                player.shuffleLibrary(source, game);
                return true;
            }
            player.shuffleLibrary(source, game);
            return true;
        }
        return false;
    }
}
