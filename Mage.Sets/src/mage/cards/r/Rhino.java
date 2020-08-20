package mage.cards.r;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.BecomesExertSourceTriggeredAbility;
import mage.abilities.effects.common.combat.CantBeBlockedSourceEffect;
import mage.abilities.keyword.ExertAbility;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Rhino extends CardImpl {

    public Rhino(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{G}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.RHINO);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // You may exert Rhino as he attacks. If you do, he can't be blocked this turn.
        this.addAbility(new ExertAbility(new BecomesExertSourceTriggeredAbility(new CantBeBlockedSourceEffect(Duration.EndOfTurn))));
    }

    private Rhino(final Rhino card) {
        super(card);
    }

    @Override
    public Rhino copy() {
        return new Rhino(this);
    }
}
