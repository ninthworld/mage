package mage.cards.h;

import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.ObjectColor;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DealtDamageToSourceTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.ShuffleIntoLibrarySourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.ProtectionAbility;
import mage.cards.Card;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.game.Game;
import mage.game.events.DamagedCreatureEvent;
import mage.game.events.GameEvent;

/**
 *
 * @author NinthWorld
 */
public final class HydroMan extends CardImpl {

    public HydroMan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(3);
        this.toughness = new MageInt(6);

        // Protection from blue
        this.addAbility(ProtectionAbility.from(ObjectColor.BLUE));

        // {U}: Hydro-Man gets +1/-1 or -1/+1 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(1, -1, Duration.EndOfTurn), new ManaCostsImpl("{U}")));
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(-1, 1, Duration.EndOfTurn), new ManaCostsImpl("{U}")));

        // Whenever a red source deals damage to Hydro-Man, shuffle him into his owner's library.
        this.addAbility(new HydroManTriggeredAbility());
    }

    private HydroMan(final HydroMan card) {
        super(card);
    }

    @Override
    public HydroMan copy() {
        return new HydroMan(this);
    }
}

class HydroManTriggeredAbility extends TriggeredAbilityImpl {

    public HydroManTriggeredAbility() {
        super(Zone.BATTLEFIELD, new ShuffleIntoLibrarySourceEffect(), false);
    }

    public HydroManTriggeredAbility(final HydroManTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public HydroManTriggeredAbility copy() {
        return new HydroManTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_CREATURE || event.getType() == GameEvent.EventType.COMBAT_DAMAGE_STEP_POST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.DAMAGED_CREATURE && event.getTargetId().equals(getSourceId())) {
            MageObject mageObject = game.getBaseObject(event.getSourceId());
            return mageObject != null && mageObject.getColor(game).contains(ObjectColor.RED);
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a red source deals damage to {this}, shuffle him into his owner's library.";
    }
}
