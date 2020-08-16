package mage.cards.g;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.SacrificeEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.keyword.TransformAbility;
import mage.abilities.keyword.UnstableAbility;
import mage.cards.decks.CardNameUtil;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.filter.predicate.other.CardTextPredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.Target;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetOpponent;

/**
 *
 * @author NinthWorld
 */
public final class GreenGoblin extends CardImpl {

    public GreenGoblin(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.GOBLIN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        this.color.setGreen(true);
        this.color.setBlack(true);

        this.nightCard = true;
        this.transformable = true;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Menace
        this.addAbility(new MenaceAbility());

        // Unstable
        this.addAbility(new UnstableAbility());

        // When this creature transforms into Green Goblin, you may search your library for a card named Goblin Glider, reveal it, put it into your hand, then shuffle your library.
        this.addAbility(new GreenGoblinAbility());

        // Pay 1 life, {T}: Transform Green Goblin.
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(false), new CompositeCost(new PayLifeCost(1), new TapSourceCost(), "Pay 1 life, {T}")));
    }

    private GreenGoblin(final GreenGoblin card) {
        super(card);
    }

    @Override
    public GreenGoblin copy() {
        return new GreenGoblin(this);
    }
}

class GreenGoblinAbility extends TriggeredAbilityImpl {

    static final String RULE_TEXT = "When this creature transforms into Green Goblin, you may search your library for a card named Goblin Glider, reveal it, put it into your hand, then shuffle your library";
    static final FilterCard filter = new FilterCard();

    static {
        filter.add(new NamePredicate("Goblin Glider"));
    }

    public GreenGoblinAbility() {
        super(Zone.BATTLEFIELD, new SearchLibraryPutInHandEffect(new TargetCardInLibrary(filter), true, true), true);
    }

    public GreenGoblinAbility(final GreenGoblinAbility ability) {
        super(ability);
    }

    @Override
    public GreenGoblinAbility copy() {
        return new GreenGoblinAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.TRANSFORMED;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getTargetId().equals(sourceId)) {
            Permanent permanent = game.getPermanent(sourceId);
            if (permanent != null && permanent.isTransformed()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return RULE_TEXT + '.';
    }
}