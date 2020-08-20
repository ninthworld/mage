
package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.GraftAbility;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.Collections;

/**
 *
 * @author NinthWorld
 */
public final class SymbioteToken extends TokenImpl {

    public SymbioteToken() {
        super("Symbiote", "0/0 black Symbiote creature token with graft 2");
        this.setOriginalExpansionSetCode("MRV");

        cardType.add(CardType.CREATURE);
        subtype.add(SubType.SYMBIOTE);

        color.setBlack(true);
        power = new MageInt(0);
        toughness = new MageInt(0);

        // Graft 2
        this.addAbility(new GraftAbility(this, 2));
    }

    public SymbioteToken(final SymbioteToken token) {
        super(token);
    }

    public SymbioteToken copy() {
        return new SymbioteToken(this);
    }
}