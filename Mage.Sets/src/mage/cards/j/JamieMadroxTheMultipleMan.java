package mage.cards.j;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.ChangelingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.game.permanent.token.DuplicateToken;

/**
 *
 * @author NinthWorld
 */
public final class JamieMadroxTheMultipleMan extends CardImpl {

    public JamieMadroxTheMultipleMan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.SHAPESHIFTER);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Changeling
        this.addAbility(ChangelingAbility.getInstance());

        // At the beginning of your upkeep, create a 3/3 blue Duplicate creature token with changeling.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new CreateTokenEffect(new DuplicateToken()), TargetController.YOU, false));
    }

    private JamieMadroxTheMultipleMan(final JamieMadroxTheMultipleMan card) {
        super(card);
    }

    @Override
    public JamieMadroxTheMultipleMan copy() {
        return new JamieMadroxTheMultipleMan(this);
    }
}
