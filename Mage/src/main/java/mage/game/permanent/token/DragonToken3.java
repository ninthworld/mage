package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author NinthWorld
 */
public final class DragonToken3 extends TokenImpl {

    public DragonToken3() {
        super("Dragon", "3/3 red Dragon creature token with flying");
        this.setOriginalExpansionSetCode("MRV");
        cardType.add(CardType.CREATURE);
        color.setRed(true);
        subtype.add(SubType.DRAGON);
        power = new MageInt(3);
        toughness = new MageInt(3);
        addAbility(FlyingAbility.getInstance());
    }

    public DragonToken3(final DragonToken3 token) {
        super(token);
    }

    public DragonToken3 copy() {
        return new DragonToken3(this);
    }
}
