package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ExileSourceEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.constants.*;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Angel extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(AnotherPredicate.instance);
    }

    public Angel(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ANGEL);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.a.AngelArchangel.class;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever Angel attacks, another target creature gains flying until end of turn.
        Ability ability = new AttacksTriggeredAbility(new GainAbilityTargetEffect(FlyingAbility.getInstance(), Duration.EndOfTurn), false);
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);

        // {B}: Exile Angel with suspend 1.
//        this.addAbility(new SimpleActivatedAbility(new ExileSourceEffect(), new ManaCostsImpl("{B}")));

        // If Angel entered the battlefield from exile, transform him.
        this.addAbility(new AngelTriggeredAbility());
    }

    private Angel(final Angel card) {
        super(card);
    }

    @Override
    public Angel copy() {
        return new Angel(this);
    }
}

class AngelTriggeredAbility extends TriggeredAbilityImpl {

    public AngelTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public AngelTriggeredAbility(AngelTriggeredAbility ability) {
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
        return "If Angel entered the battlefield from exile, transform him.";
    }

    @Override
    public AngelTriggeredAbility copy() {
        return new AngelTriggeredAbility(this);
    }
}