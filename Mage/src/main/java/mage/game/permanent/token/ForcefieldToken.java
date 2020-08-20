package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.DefenderAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 * @author NinthWorld
 */
public final class ForcefieldToken extends TokenImpl {

    public ForcefieldToken() {
        super("Forcefield", "0/1 white Forcefield creature token with defender");
        this.setOriginalExpansionSetCode("MRV");
        cardType.add(CardType.CREATURE);
        color.setWhite(true);
        subtype.add(SubType.FORCEFIELD);
        power = new MageInt(0);
        toughness = new MageInt(1);

        // Defender
        this.addAbility(DefenderAbility.getInstance());
    }

    public ForcefieldToken(final ForcefieldToken token) {
        super(token);
    }

    @Override
    public ForcefieldToken copy() {
        return new ForcefieldToken(this);
    }
}
