package mage.cards.g;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.AgilityAbility;
import mage.abilities.keyword.ArsenalAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.DeathtouchAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Gamora extends CardImpl {

    public Gamora(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B/G}{B/G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Deathtouch
        this.addAbility(DeathtouchAbility.getInstance());

        // Arsenal 1
        this.addAbility(new ArsenalAbility(1));

        // Agility
        this.addAbility(new AgilityAbility());
    }

    private Gamora(final Gamora card) {
        super(card);
    }

    @Override
    public Gamora copy() {
        return new Gamora(this);
    }
}
