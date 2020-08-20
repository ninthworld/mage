package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.ProwlAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.ExaltedAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Prowler extends CardImpl {

    public Prowler(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W/B}{W/B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.MERCENARY);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Prowl {B}
        this.addAbility(new ProwlAbility(this, "{B}"));

        // Exalted
        this.addAbility(new ExaltedAbility());
    }

    private Prowler(final Prowler card) {
        super(card);
    }

    @Override
    public Prowler copy() {
        return new Prowler(this);
    }
}
