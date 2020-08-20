package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.AttacksOrBlocksTriggeredAbility;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.CrewAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class SpiderMobile extends CardImpl {

    public SpiderMobile(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.VEHICLE);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Whenever Spider-Mobile attacks or blocks, you may draw a card. If you do, discard a card.
        this.addAbility(new AttacksOrBlocksTriggeredAbility(new DrawDiscardControllerEffect(1, 1, true), false));

        // Crew 1
        this.addAbility(new CrewAbility(1));

    }

    private SpiderMobile(final SpiderMobile card) {
        super(card);
    }

    @Override
    public SpiderMobile copy() {
        return new SpiderMobile(this);
    }
}
