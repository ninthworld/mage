package mage.cards.k;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldAllTriggeredAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.FightTargetSourceEffect;
import mage.abilities.keyword.AgilityAbility;
import mage.abilities.keyword.ArsenalAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;

/**
 *
 * @author NinthWorld
 */
public final class KravenTheHunter extends CardImpl {

    public KravenTheHunter(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Arsenal 1
        this.addAbility(new ArsenalAbility(1));

        // Agility
        this.addAbility(new AgilityAbility());

        // Whenever a creature enters the battlefield under an opponent's control, you may pay {2}. If you do, Kraven the Hunter fights that creature.
        this.addAbility(new EntersBattlefieldAllTriggeredAbility(
                Zone.BATTLEFIELD,
                new DoIfCostPaid(new FightTargetSourceEffect(), new GenericManaCost(2)),
                StaticFilters.FILTER_OPPONENTS_PERMANENT_CREATURE,
                false,
                SetTargetPointer.PERMANENT,
                "Whenever a creature enters the battlefield under and opponent's control, you may pay {2}. If you do, {this} fights that creature."));
    }

    private KravenTheHunter(final KravenTheHunter card) {
        super(card);
    }

    @Override
    public KravenTheHunter copy() {
        return new KravenTheHunter(this);
    }
}
