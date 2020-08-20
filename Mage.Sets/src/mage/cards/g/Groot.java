package mage.cards.g;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.LandfallAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.RemoveCountersSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.RegenerateTargetEffect;
import mage.abilities.effects.common.UntapSourceEffect;
import mage.abilities.effects.common.continuous.BecomesCreatureSourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.ReachAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.counters.CounterType;
import mage.game.permanent.token.WallGrootToken;
import mage.game.permanent.token.WallToken;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Groot extends CardImpl {

    public Groot(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{G}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.TREEFOLK);
        this.subtype.add(SubType.NOBLE);
        this.power = new MageInt(7);
        this.toughness = new MageInt(4);

        // Reach
        this.addAbility(ReachAbility.getInstance());

        // <i>Landfall</i> - Whenever a land enters the battlefield under your control, put a +1/+1 counter on Groot.
        this.addAbility(new LandfallAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance()), false));

        // {G}, Remove a +1/+1 counter from Groot: Regenerate target creature.
        Ability ability1 = new SimpleActivatedAbility(new RegenerateTargetEffect(), new ManaCostsImpl("{G}"));
        ability1.addCost(new RemoveCountersSourceCost(CounterType.P1P1.createInstance()));
        ability1.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability1);

        // {2}{G}: Untap Groot. He becomes a 4/7 Wall creature with defender until end of turn in addition to his other types.
        Ability ability2 = new SimpleActivatedAbility(new UntapSourceEffect(), new ManaCostsImpl("{2}{G}"));
        ability2.addEffect(new BecomesCreatureSourceEffect(new WallGrootToken(), "in addition to his other types", Duration.EndOfTurn));
        this.addAbility(ability2);
    }

    private Groot(final Groot card) {
        super(card);
    }

    @Override
    public Groot copy() {
        return new Groot(this);
    }
}
