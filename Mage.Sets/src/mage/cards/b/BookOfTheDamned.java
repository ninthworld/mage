package mage.cards.b;

import java.util.UUID;
import java.util.function.Predicate;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.ShuffleIntoLibrarySourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.cards.Card;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.game.permanent.token.ChthonToken;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class BookOfTheDamned extends CardImpl {

    private static final FilterCard filter = new FilterCard("black instant or sorcery spell");

    static {
        filter.add(new ColorPredicate(ObjectColor.BLACK));
        filter.add(Predicates.or(CardType.INSTANT.getPredicate(), CardType.SORCERY.getPredicate()));
    }

    public BookOfTheDamned(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // {2}{B}, {T}: Search your library for a black instant or sorcery spell, reveal it, put it into your hand, then shuffle Book of the Damned into your library.
        Ability ability1 = new SimpleActivatedAbility(new SearchLibraryPutInHandEffect(new TargetCardInLibrary(filter), true, false), new CompositeCost(new ManaCostsImpl("{2}{B}"), new TapSourceCost(), "{2}{B}, {T}"));
        ability1.addEffect(new ShuffleIntoLibrarySourceEffect());
        this.addAbility(ability1);

        // {7}, {T}, Exile Book of the Damned: Sacrifice a creature. If you do, create a 7/7 legendary black Demon God creature token with indestructible named Chthon.
        Ability ability2 = new SimpleActivatedAbility(Zone.BATTLEFIELD, new CreateTokenEffect(new ChthonToken()), new CompositeCost(new GenericManaCost(7), new CompositeCost(new TapSourceCost(), new SacrificeSourceCost(), ""), "{7}, {T}"));
        ability2.addCost(new SacrificeTargetCost(new TargetControlledCreaturePermanent()));
        this.addAbility(ability2);
    }

    private BookOfTheDamned(final BookOfTheDamned card) {
        super(card);
    }

    @Override
    public BookOfTheDamned copy() {
        return new BookOfTheDamned(this);
    }
}
