package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.ArsenalAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Starlord extends CardImpl {

    public Starlord(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.PIRATE);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        this.color.setRed(true);
        this.color.setWhite(true);

        this.transformable = true;
        this.nightCard = true;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Arsenal 2
        this.addAbility(new ArsenalAbility(2));
    }

    private Starlord(final Starlord card) {
        super(card);
    }

    @Override
    public Starlord copy() {
        return new Starlord(this);
    }
}
