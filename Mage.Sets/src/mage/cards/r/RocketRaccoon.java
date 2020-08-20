package mage.cards.r;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.ArsenalAbility;
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
public final class RocketRaccoon extends CardImpl {

    public RocketRaccoon(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.BEAST);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Arsenal 3
        this.addAbility(new ArsenalAbility(3));
    }

    private RocketRaccoon(final RocketRaccoon card) {
        super(card);
    }

    @Override
    public RocketRaccoon copy() {
        return new RocketRaccoon(this);
    }
}
