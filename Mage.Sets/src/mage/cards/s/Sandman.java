package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.ObjectColor;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.ShuffleIntoLibrarySourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.ProtectionAbility;
import mage.cards.basiclands.Mountain;
import mage.constants.*;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;

/**
 *
 * @author NinthWorld
 */
public final class Sandman extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("Mountains you control");

    static {
        filter.add(SubType.MOUNTAIN.getPredicate());
    }

    public Sandman(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{R}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // protection from red
        this.addAbility(ProtectionAbility.from(ObjectColor.RED));

        // {2}{R}: Sandman gets +X/+0 until end of turn, where X is the number of Mountains you control.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(new PermanentsOnBattlefieldCount(filter), StaticValue.get(1), Duration.EndOfTurn), new ManaCostsImpl("{2}{R}")));

        // Whenever a blue source deals damage to Sandman, shuffle him into his owner's library.
        this.addAbility(new SandmanTriggeredAbility());
    }

    private Sandman(final Sandman card) {
        super(card);
    }

    @Override
    public Sandman copy() {
        return new Sandman(this);
    }
}

class SandmanTriggeredAbility extends TriggeredAbilityImpl {

    public SandmanTriggeredAbility() {
        super(Zone.BATTLEFIELD, new ShuffleIntoLibrarySourceEffect(), false);
    }

    public SandmanTriggeredAbility(final SandmanTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public SandmanTriggeredAbility copy() {
        return new SandmanTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_CREATURE || event.getType() == GameEvent.EventType.COMBAT_DAMAGE_STEP_POST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.DAMAGED_CREATURE && event.getTargetId().equals(getSourceId())) {
            MageObject mageObject = game.getBaseObject(event.getSourceId());
            return mageObject != null && mageObject.getColor(game).contains(ObjectColor.BLUE);
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a blue source deals damage to {this}, shuffle him into his owner's library.";
    }
}
