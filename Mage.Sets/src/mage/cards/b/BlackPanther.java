package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.DiesTriggeredAbility;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.keyword.AgilityAbility;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.VigilanceAbility;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterCard;
import mage.filter.predicate.mageobject.ConvertedManaCostPredicate;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author NinthWorld
 */
public final class BlackPanther extends CardImpl {

    private static final FilterCard filter = new FilterCard("creature card with converted mana cost 3 or less");

    static {
        filter.add(CardType.CREATURE.getPredicate());
        filter.add(new ConvertedManaCostPredicate(ComparisonType.FEWER_THAN, 4));
    }

    public BlackPanther(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CAT);
        this.subtype.add(SubType.NOBLE);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Agility
        this.addAbility(new AgilityAbility());

        // When Black Panther dies, you may search your library for a creature card with converted mana cost 3 or less, reveal it, put it into your hand, then shuffle your library.
        this.addAbility(new DiesTriggeredAbility(new SearchLibraryPutInHandEffect(new TargetCardInLibrary(filter), true, true)));
    }

    private BlackPanther(final BlackPanther card) {
        super(card);
    }

    @Override
    public BlackPanther copy() {
        return new BlackPanther(this);
    }
}
