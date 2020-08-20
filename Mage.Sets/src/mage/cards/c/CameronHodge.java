package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DiesCreatureTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.UndyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterPermanent;
import mage.target.TargetPermanent;

/**
 *
 * @author NinthWorld
 */
public final class CameronHodge extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("Mutant");

    static {
        filter.add(SubType.MUTANT.getPredicate());
    }

    public CameronHodge(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{2}{B}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CONSTRUCT);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(7);

        // Whenever a Mutant dies, draw a card.
        this.addAbility(new DiesCreatureTriggeredAbility(new DrawCardSourceControllerEffect(1), false, filter));

        // {3}{B}, {T}: Destroy target Mutant.
        Ability ability = new SimpleActivatedAbility(new DestroyTargetEffect(), new CompositeCost(new ManaCostsImpl("{3}{B}"), new TapSourceCost(), "{3}{B}, {T}"));
        ability.addTarget(new TargetPermanent(filter));
        this.addAbility(ability);

        // Undying
        this.addAbility(new UndyingAbility());

    }

    private CameronHodge(final CameronHodge card) {
        super(card);
    }

    @Override
    public CameronHodge copy() {
        return new CameronHodge(this);
    }
}
