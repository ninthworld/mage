package mage.cards.h;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.MenaceAbility;
import mage.abilities.keyword.ExtortAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Hobgoblin extends CardImpl {

    public Hobgoblin(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.GOBLIN);
        this.subtype.add(SubType.MERCENARY);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Menace
        this.addAbility(new MenaceAbility());

        // When Hobgoblin enters the battlefield, you draw a card and lose 1 life.
        Ability ability = new EntersBattlefieldAbility(new DrawCardSourceControllerEffect(1));
        ability.addEffect(new LoseLifeSourceControllerEffect(1));
        this.addAbility(ability);

        // Extort
        this.addAbility(new ExtortAbility());

    }

    private Hobgoblin(final Hobgoblin card) {
        super(card);
    }

    @Override
    public Hobgoblin copy() {
        return new Hobgoblin(this);
    }
}
