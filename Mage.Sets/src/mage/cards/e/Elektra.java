package mage.cards.e;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.AgilityAbility;
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
public final class Elektra extends CardImpl {

    public Elektra(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R/W}{R/W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Agility
        this.addAbility(new AgilityAbility());
    }

    private Elektra(final Elektra card) {
        super(card);
    }

    @Override
    public Elektra copy() {
        return new Elektra(this);
    }
}
