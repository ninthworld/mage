package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.keyword.ExploreSourceEffect;
import mage.abilities.keyword.AgilityAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Puck extends CardImpl {

    public Puck(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{G}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.NOMAD);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Agility
        this.addAbility(new AgilityAbility());

        // When Puck enters the battlefield, he explores.
        this.addAbility(new EntersBattlefieldAbility(new ExploreSourceEffect()));
    }

    private Puck(final Puck card) {
        super(card);
    }

    @Override
    public Puck copy() {
        return new Puck(this);
    }
}
