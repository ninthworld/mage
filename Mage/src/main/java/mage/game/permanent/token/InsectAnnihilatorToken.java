

package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.AnnihilatorAbility;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NinthWorld
 */
public final class InsectAnnihilatorToken extends TokenImpl {

    public InsectAnnihilatorToken() {
        this((String)null);
    }

    public InsectAnnihilatorToken(String setCode) {
        super("Insect", "1/1 black Insect creature token with Annihilator 1");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        color.setBlack(true);
        subtype.add(SubType.INSECT);
        power = new MageInt(1);
        toughness = new MageInt(1);

        // Annihilator 1
        this.addAbility(new AnnihilatorAbility(1));
    }

    public InsectAnnihilatorToken(final InsectAnnihilatorToken token) {
        super(token);
    }

    public InsectAnnihilatorToken copy() {
        return new InsectAnnihilatorToken(this);
    }
}