package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.TransformAbility;
import mage.cards.j.JuggernautMRV;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.Target;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class CainMarko extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("you control a legendary artifact");

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(SuperType.LEGENDARY.getPredicate());
        filter.add(CardType.ARTIFACT.getPredicate());
    }

    public CainMarko(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = JuggernautMRV.class;

        // {R}, {T}: Cain Marko deals damage equal to his power to target creature with lesser power.
        this.addAbility(new SimpleActivatedAbility(new CainMarkoChampionEffect(), new CompositeCost(new ManaCostsImpl("{R}"), new TapSourceCost(), "{R}, {T}")));

        // At the beginning of your upkeep, if you control a legendary artifact, transform Cain Marko.
        this.addAbility(new TransformAbility());
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new ConditionalOneShotEffect(new TransformSourceEffect(true), new PermanentsOnTheBattlefieldCondition(filter)), TargetController.YOU, false));
    }

    private CainMarko(final CainMarko card) {
        super(card);
    }

    @Override
    public CainMarko copy() {
        return new CainMarko(this);
    }
}

class CainMarkoChampionEffect extends OneShotEffect {

    public CainMarkoChampionEffect() {
        super(Outcome.Benefit);
        this.staticText = "{this} deals damage equal to his power to target creature with lesser power";
    }

    public CainMarkoChampionEffect(final CainMarkoChampionEffect effect) {
        super(effect);
    }

    @Override
    public CainMarkoChampionEffect copy() {
        return new CainMarkoChampionEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanentOrLKIBattlefield(source.getSourceId());
        if (permanent != null) {
            FilterCreaturePermanent filter = new FilterCreaturePermanent();
            filter.add(new PowerPredicate(ComparisonType.FEWER_THAN, permanent.getPower().getValue()));
            Target target = new TargetCreaturePermanent(filter);
            source.addTarget(target);
            new DamageTargetEffect(permanent.getPower().getValue()).apply(game, source);
            return true;
        }
        return false;
    }
}
