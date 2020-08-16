package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.HeroicAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

/**
 *
 * @author NinthWorld
 */
public final class BuckyBarnes extends CardImpl {

    public BuckyBarnes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.t.TheWinterSoldier.class;

        // <i>Heroic</i> - Whenever you cast a spell that targets Bucky Barnes, you gain 2 life.
        this.addAbility(new HeroicAbility(new GainLifeEffect(2)));

        // When Bucky Barnes dies, exile him with Suspend 2.


        // If Bucky Barnes entered the battlefield from exile, transform him.
        this.addAbility(new BuckyBarnesTriggeredAbility());
    }

    private BuckyBarnes(final BuckyBarnes card) {
        super(card);
    }

    @Override
    public BuckyBarnes copy() {
        return new BuckyBarnes(this);
    }
}

class BuckyBarnesTriggeredAbility extends TriggeredAbilityImpl {

    public BuckyBarnesTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public BuckyBarnesTriggeredAbility(BuckyBarnesTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent permanent = game.getPermanent(event.getSourceId());
        return (permanent != null && ((EntersTheBattlefieldEvent) event).getFromZone() == Zone.EXILED);
    }

    @Override
    public String getRule() {
        return "If Bucky Barnes entered the battlefield from exile, transform him.";
    }

    @Override
    public BuckyBarnesTriggeredAbility copy() {
        return new BuckyBarnesTriggeredAbility(this);
    }
}