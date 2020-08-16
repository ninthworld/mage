package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.LandfallAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.RegenerateSourceEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.WitherAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.game.permanent.token.HumanAgentToken;

/**
 *
 * @author NinthWorld
 */
public final class BaronStrucker extends CardImpl {

    public BaronStrucker(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        // Wither
        this.addAbility(WitherAbility.getInstance());

        // <i>Landfall</i> - Whenever a land enters the battlefield under your control, create a 1/1 black Human Agent creature token.
        this.addAbility(new LandfallAbility(new CreateTokenEffect(new HumanAgentToken()), false));

        // {1}{B}: Regenerate Baron Strucker.
        this.addAbility(new SimpleActivatedAbility(new RegenerateSourceEffect(), new ManaCostsImpl("{1}{B}")));
    }

    private BaronStrucker(final BaronStrucker card) {
        super(card);
    }

    @Override
    public BaronStrucker copy() {
        return new BaronStrucker(this);
    }
}
