package mage.cards.b;

import java.util.UUID;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.ShuffleIntoLibrarySourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.game.permanent.token.ForcefieldToken;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author NinthWorld
 */
public final class BookOfTheVishanti extends CardImpl {

    private static final FilterCard filter = new FilterCard("white instant or sorcery spell");

    static {
        filter.add(new ColorPredicate(ObjectColor.WHITE));
        filter.add(Predicates.or(CardType.INSTANT.getPredicate(), CardType.SORCERY.getPredicate()));
    }

    public BookOfTheVishanti(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // When Book of the Vishanti enters the battlefield, create a 0/1 white Forcefield creature token with defender.
        this.addAbility(new EntersBattlefieldAbility(new CreateTokenEffect(new ForcefieldToken())));

        // {2}{W}, {T}: Search your library for a white instant or sorcery spell, reveal it, put it into your hand, then shuffle Book of the Vishanti into your library.
        Ability ability = new SimpleActivatedAbility(new SearchLibraryPutInHandEffect(new TargetCardInLibrary(filter), true, false), new CompositeCost(new ManaCostsImpl("{2}{W}"), new TapSourceCost(), "{2}{W}, {T}"));
        ability.addEffect(new ShuffleIntoLibrarySourceEffect());
        this.addAbility(ability);
    }

    private BookOfTheVishanti(final BookOfTheVishanti card) {
        super(card);
    }

    @Override
    public BookOfTheVishanti copy() {
        return new BookOfTheVishanti(this);
    }
}
