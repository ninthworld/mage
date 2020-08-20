package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DealtDamageToSourceTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.predicate.permanent.ControllerIdPredicate;
import mage.game.Game;
import mage.game.events.DamagedCreatureEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.Target;
import mage.target.TargetPermanent;
import mage.target.TargetPlayer;
import mage.target.common.TargetPermanentOrPlayer;

/**
 *
 * @author NinthWorld
 */
public final class Blob extends CardImpl {

    public Blob(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{G}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.OOZE);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(6);

        // Blob has indestructible as long as he's untapped.
        this.addAbility(new SimpleStaticAbility(
                Zone.BATTLEFIELD,
                new ConditionalContinuousEffect(
                        new GainAbilitySourceEffect(IndestructibleAbility.getInstance(), Duration.WhileOnBattlefield),
                        new BlobUntappedCondition(),
                        "{this} has indestructible as long as he's untapped")));

        // Whenever damage is dealt to Blob, he deals half that damage, rounded up, to the source of that damage.
        this.addAbility(new BlobTriggeredAbility());
    }

    private Blob(final Blob card) {
        super(card);
    }

    @Override
    public Blob copy() {
        return new Blob(this);
    }
}

class BlobUntappedCondition implements Condition {

    public BlobUntappedCondition() {
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());

        if (permanent != null) {
            return !permanent.isTapped();
        }

        return false;
    }

    @Override
    public String toString() {
        return "as long as he's untapped";
    }
}

class BlobTriggeredAbility extends TriggeredAbilityImpl {

    public BlobTriggeredAbility() {
        super(Zone.BATTLEFIELD, null, false);
    }

    public BlobTriggeredAbility(final BlobTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public BlobTriggeredAbility copy() {
        return new BlobTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_CREATURE || event.getType() == GameEvent.EventType.COMBAT_DAMAGE_STEP_POST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.DAMAGED_CREATURE && event.getTargetId().equals(getSourceId())) {
            int damageAmount = event.getAmount();
            if (damageAmount > 0) {
                UUID eventSourceId = event.getSourceId();
                if (!eventSourceId.equals(sourceId)) {
                    if (game.isActivePlayer(eventSourceId)) {
                        Target target = new TargetPlayer();
                        target.addTarget(eventSourceId, this, game);
                        this.addTarget(target);
                    }
                    else {
                        Card card = game.getCard(eventSourceId);
                        if (card != null && card.isPermanent()) {
                            Target target = new TargetPermanent();
                            target.addTarget(eventSourceId, this, game);
                            this.addTarget(target);
                        }
                    }

                    if (this.getTargets().size() > 0 && this.getTargets().get(0) != null) {
                        new DamageTargetEffect((int)Math.ceil(damageAmount / 2.0)).apply(game, this);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever damage is dealt to {this}, he deals half that damage, rounded up, to the source of that damage.";
    }
}
