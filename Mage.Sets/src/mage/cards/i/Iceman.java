package mage.cards.i;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealsDamageToACreatureTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DontUntapInControllersNextUntapStepTargetEffect;
import mage.abilities.effects.common.TapTargetEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.game.permanent.token.WallBlueToken;

/**
 *
 * @author NinthWorld
 */
public final class Iceman extends CardImpl {

    public Iceman(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        // Whenever a creature is dealt damage by Iceman, tap it. It doesn't untap during its controller's next untap step.
        Ability ability1 = new DealsDamageToACreatureTriggeredAbility(new TapTargetEffect(), false, false, true);
        ability1.addEffect(new DontUntapInControllersNextUntapStepTargetEffect());
        this.addAbility(ability1);

        // {1}{U}, {T}: Create a 0/4 blue Wall creature token with defender.
        Ability ability2 = new SimpleActivatedAbility(new CreateTokenEffect(new WallBlueToken()), new ManaCostsImpl("{1}{U}"));
        ability2.addCost(new TapSourceCost());
        this.addAbility(ability2);
    }

    private Iceman(final Iceman card) {
        super(card);
    }

    @Override
    public Iceman copy() {
        return new Iceman(this);
    }
}
