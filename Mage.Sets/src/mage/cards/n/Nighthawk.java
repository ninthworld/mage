package mage.cards.n;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.MyTurnCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.cards.Card;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author NinthWorld
 */
public final class Nighthawk extends CardImpl {

    public Nighthawk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W/U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.BIRD);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // As long as it's not your turn, Nighthawk gets +2/+2.
        this.addAbility(new SimpleStaticAbility(new ConditionalContinuousEffect(new BoostSourceEffect(2, 2, Duration.WhileOnBattlefield), NotMyTurnCondition.instance, "As long as it's not your turn, {this} gets +2/+2")));
    }

    private Nighthawk(final Nighthawk card) {
        super(card);
    }

    @Override
    public Nighthawk copy() {
        return new Nighthawk(this);
    }
}

enum NotMyTurnCondition implements Condition {
    instance;

    @Override
    public boolean apply(Game game, Ability source) {
        return !game.isActivePlayer(source.getControllerId());
    }

    @Override
    public String toString() {
        return "";
    }
}
