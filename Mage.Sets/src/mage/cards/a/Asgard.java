package mage.cards.a;

import java.util.UUID;

import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.OneOpponentCondition;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.TapSourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.mana.RedManaAbility;
import mage.abilities.mana.SimpleManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterLandCard;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author NinthWorld
 */
public final class Asgard extends CardImpl {

    private static final FilterLandCard filter = new FilterLandCard();

    public Asgard(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setRed(true);
        this.color.setWhite(true);

        // Asgard enters the battlefield tapped unless you have two or more opponents.
        this.addAbility(new EntersBattlefieldAbility(
                new ConditionalOneShotEffect(
                        new TapSourceEffect(),
                        OneOpponentCondition.instance,
                        "tapped unless you have two or more opponents"
                ), "tapped unless you have two or more opponents"
        ));

        // {T}: Add {R} or {W}.
        this.addAbility(new RedManaAbility());
        this.addAbility(new WhiteManaAbility());

        // {T}, Pay 1 life, Sacrifice Asgard: Search your library for a land card, put it onto the battlefield, then shuffle your library.
        this.addAbility(new SimpleActivatedAbility(
                new SearchLibraryPutInPlayEffect(
                        new TargetCardInLibrary(filter), false, true),
                new CompositeCost(new TapSourceCost(),
                        new CompositeCost(new PayLifeCost(1), new SacrificeSourceCost(), ""),
                        "{T}, Pay 1 life, Sacrifice Asgard")));
    }

    private Asgard(final Asgard card) {
        super(card);
    }

    @Override
    public Asgard copy() {
        return new Asgard(this);
    }
}
