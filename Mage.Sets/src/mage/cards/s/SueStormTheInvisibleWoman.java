package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.keyword.CantBeBlockedSourceAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.HexproofAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.game.permanent.token.ForcefieldToken;

/**
 *
 * @author NinthWorld
 */
public final class SueStormTheInvisibleWoman extends CardImpl {

    public SueStormTheInvisibleWoman(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ILLUSION);
        this.subtype.add(SubType.ADVISOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(6);

        // Hexproof
        this.addAbility(HexproofAbility.getInstance());

        // Sue Storm, the Invisible Woman can't be blocked.
        this.addAbility(new CantBeBlockedSourceAbility());

        // {2}{W}: Create a 0/1 white Forcefield creature token with defender.
        this.addAbility(new SimpleActivatedAbility(new CreateTokenEffect(new ForcefieldToken()), new ManaCostsImpl("{2}{W}")));
    }

    private SueStormTheInvisibleWoman(final SueStormTheInvisibleWoman card) {
        super(card);
    }

    @Override
    public SueStormTheInvisibleWoman copy() {
        return new SueStormTheInvisibleWoman(this);
    }
}
