package mage.cards.o;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.OneOrMoreCountersAddedTriggeredAbility;
import mage.abilities.dynamicvalue.common.EffectKeyValue;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.ReachAbility;
import mage.abilities.keyword.WitherAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.NestOfScarabsBlackInsectToken;

/**
 *
 * @author NinthWorld
 */
public final class OmegaRed extends CardImpl {

    public OmegaRed(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.MERCENARY);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Reach
        this.addAbility(ReachAbility.getInstance());

        // Wither
        this.addAbility(WitherAbility.getInstance());

        // Whenever you put one or more -1/-1 counters on a creature, each opponent loses 1 life and you gain 1 life.
        this.addAbility(new OmegaRedTriggeredAbility());
    }

    private OmegaRed(final OmegaRed card) {
        super(card);
    }

    @Override
    public OmegaRed copy() {
        return new OmegaRed(this);
    }
}

class OmegaRedTriggeredAbility extends TriggeredAbilityImpl {

    OmegaRedTriggeredAbility() {
        super(Zone.BATTLEFIELD, new LoseLifeOpponentsEffect(1));
        this.addEffect(new GainLifeEffect(1));
    }

    OmegaRedTriggeredAbility(final OmegaRedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.COUNTERS_ADDED;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        boolean weAreDoingIt = isControlledBy(game.getControllerId(event.getSourceId()));
        boolean isM1M1Counters = event.getData().equals(CounterType.M1M1.getName());
        if (weAreDoingIt && isM1M1Counters && event.getAmount() > 0) {
            Permanent permanent = game.getPermanentOrLKIBattlefield(event.getTargetId());
            if (permanent == null) {
                permanent = game.getPermanentEntering(event.getTargetId());
            }
            if (permanent != null && permanent.isCreature()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OmegaRedTriggeredAbility copy() {
        return new OmegaRedTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever you put one or more -1/-1 counters on a creature, each opponent loses 1 life and you gain 1 life.";
    }
}
