package mage.cards.j;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.AttacksAllTriggeredAbility;
import mage.abilities.common.AttacksOrBlocksTriggeredAbility;
import mage.abilities.common.BlocksTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.discard.DiscardControllerEffect;
import mage.abilities.effects.keyword.InvestigateEffect;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.targetpointer.FixedTarget;

/**
 *
 * @author NinthWorld
 */
public final class JJonahJameson extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Spiders");

    static {
        filter.add(SubType.SPIDER.getPredicate());
    }

    public JJonahJameson(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCOUT);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // {T}: Discard a card, then draw a card.
        Ability ability = new SimpleActivatedAbility(new DiscardControllerEffect(1), new TapSourceCost());
        ability.addEffect(new DrawCardSourceControllerEffect(1));
        this.addAbility(ability);

        // Whenever a Spider attacks or blocks, investigate.
        this.addAbility(new JJonahJamesonTriggeredAbility());
    }

    private JJonahJameson(final JJonahJameson card) {
        super(card);
    }

    @Override
    public JJonahJameson copy() {
        return new JJonahJameson(this);
    }
}

class JJonahJamesonTriggeredAbility extends TriggeredAbilityImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Spiders");

    static {
        filter.add(SubType.SPIDER.getPredicate());
    }

    public JJonahJamesonTriggeredAbility() {
        super(Zone.BATTLEFIELD, new InvestigateEffect(), false);
    }

    public JJonahJamesonTriggeredAbility(JJonahJamesonTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ATTACKER_DECLARED || event.getType() == GameEvent.EventType.BLOCKER_DECLARED;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent permanent = game.getPermanent(event.getSourceId());
        if (filter.match(permanent, getSourceId(), getControllerId(), game)) {
            for (Effect effect : this.getEffects()) {
                effect.setTargetPointer(new FixedTarget(permanent, game));
            }
            return true;
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a Spider attacks or blocks, investigate.";
    }

    @Override
    public JJonahJamesonTriggeredAbility copy() {
        return new JJonahJamesonTriggeredAbility(this);
    }
}