package mage.cards.e;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.common.search.SearchLibraryPutOnLibraryEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author NinthWorld
 */
public final class EdwinJarvis extends CardImpl {

    public EdwinJarvis(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // When Edwin Jarvis enters the battlefield, you may search your library for an artifact card, reveal it, then shuffle your library and put that card on top of it.
        this.addAbility(new EntersBattlefieldAbility(new SearchLibraryPutOnLibraryEffect(new TargetCardInLibrary(StaticFilters.FILTER_CARD_ARTIFACT), true, true)));
    }

    private EdwinJarvis(final EdwinJarvis card) {
        super(card);
    }

    @Override
    public EdwinJarvis copy() {
        return new EdwinJarvis(this);
    }
}
