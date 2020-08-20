package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.IndestructibleAbility;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 * @author NinthWorld
 */
public final class CosmicWarriorToken extends TokenImpl {

    public CosmicWarriorToken() {
        super("Cosmic Warrior", "2/2 black Cosmic Warrior creature token");
        this.setOriginalExpansionSetCode("MRV");
        cardType.add(CardType.CREATURE);
        color.setBlack(true);
        subtype.add(SubType.COSMIC);
        subtype.add(SubType.WARRIOR);
        power = new MageInt(2);
        toughness = new MageInt(2);
    }

    public CosmicWarriorToken(final CosmicWarriorToken token) {
        super(token);
    }

    @Override
    public CosmicWarriorToken copy() {
        return new CosmicWarriorToken(this);
    }
}
