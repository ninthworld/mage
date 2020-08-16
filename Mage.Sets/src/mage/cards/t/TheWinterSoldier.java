package mage.cards.t;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.AgilityAbility;
import mage.abilities.keyword.ArsenalAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class TheWinterSoldier extends CardImpl {

    public TheWinterSoldier(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        this.color.setWhite(true);
        this.color.setBlack(true);

        this.nightCard = true;
        this.transformable = true;

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Arsenal 2
        this.addAbility(new ArsenalAbility(2));

        // Agility
        this.addAbility(new AgilityAbility());
    }

    private TheWinterSoldier(final TheWinterSoldier card) {
        super(card);
    }

    @Override
    public TheWinterSoldier copy() {
        return new TheWinterSoldier(this);
    }
}
