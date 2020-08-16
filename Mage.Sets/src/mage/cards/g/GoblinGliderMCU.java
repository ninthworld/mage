
package mage.cards.g;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.common.LeavesBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.AddCardTypeSourceEffect;
import mage.abilities.effects.common.continuous.BecomesCreatureSourceEffect;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.abilities.keyword.CrewAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 *
 * @author NinthWorld
 */
public final class GoblinGliderMCU extends CardImpl {

    public GoblinGliderMCU(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{1}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.VEHICLE);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // {1}{B}: Goblin Glider becomes an artifact creature until end of turn.
        this.addAbility(new SimpleActivatedAbility(new AddCardTypeSourceEffect(Duration.EndOfTurn, CardType.ARTIFACT, CardType.CREATURE), new ManaCostsImpl("{1]{B}")));

        // When Goblin Glider leaves the battlefield, target player discards a card.
        Ability ability = new LeavesBattlefieldTriggeredAbility(new DiscardTargetEffect(1), false);
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);

        // Crew 1
        this.addAbility(new CrewAbility(1));
    }

    public GoblinGliderMCU(final GoblinGliderMCU card) {
        super(card);
    }

    @Override
    public GoblinGliderMCU copy() {
        return new GoblinGliderMCU(this);
    }
}
