package mage.cards.w;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.counter.AddCountersAllEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.ServoToken;

/**
 *
 * @author NinthWorld
 */
public final class WalterLangkowski extends CardImpl {

    public WalterLangkowski(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCIENTIST);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.s.Sasquatch.class;

        // Whenever you put a +1/+1 counter on a creature you control, you get {E}.
        this.addAbility(new WalterLangkowskiTriggeredAbility());

        // Pay {E} {E} {E}: Transform Walter Langkowski.
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(true), new PayEnergyCost(3)));
    }

    private WalterLangkowski(final WalterLangkowski card) {
        super(card);
    }

    @Override
    public WalterLangkowski copy() {
        return new WalterLangkowski(this);
    }
}

class WalterLangkowskiTriggeredAbility extends TriggeredAbilityImpl {

    public WalterLangkowskiTriggeredAbility() {
        super(Zone.BATTLEFIELD, new GetEnergyCountersControllerEffect(1), false);
    }

    public WalterLangkowskiTriggeredAbility(final WalterLangkowskiTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public WalterLangkowskiTriggeredAbility copy() {
        return new WalterLangkowskiTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.COUNTERS_ADDED;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getData().equals(CounterType.P1P1.getName())) {
            Permanent permanent = game.getPermanentOrLKIBattlefield(event.getTargetId());
            if (permanent == null) {
                permanent = game.getPermanentEntering(event.getTargetId());
            }
            return permanent != null && permanent.isControlledBy(this.getControllerId()) && permanent.getCardType().contains(CardType.CREATURE);
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever one or more +1/+1 counters are put on a creature you control, you get {E}.";
    }
}