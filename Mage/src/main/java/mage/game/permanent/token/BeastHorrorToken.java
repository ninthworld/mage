package mage.game.permanent.token;

import mage.MageInt;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.util.RandomUtil;

import java.util.Arrays;

/**
 * @author NinthWorld
 */
public final class BeastHorrorToken extends TokenImpl {

    public BeastHorrorToken() {
        super("Beast Horror", "6/7 black and green Beast Horror creature token");
        this.setOriginalExpansionSetCode("MRV");
        cardType.add(CardType.CREATURE);
        color.setGreen(true);
        color.setBlack(true);
        subtype.add(SubType.BEAST);
        subtype.add(SubType.HORROR);
        power = new MageInt(6);
        toughness = new MageInt(7);
    }

    public BeastHorrorToken(final BeastHorrorToken token) {
        super(token);
    }

    @Override
    public BeastHorrorToken copy() {
        return new BeastHorrorToken(this);
    }
}
