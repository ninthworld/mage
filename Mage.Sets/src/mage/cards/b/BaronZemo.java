package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.ExploitCreatureTriggeredAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.keyword.ArsenalAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.ExploitAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class BaronZemo extends CardImpl {

    public BaronZemo(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Arsenal 2
        this.addAbility(new ArsenalAbility(2));

        // Exploit
        this.addAbility(new ExploitAbility());

        // When Baron Zemo exploits a creature, you draw two cards and lose 2 life.
        Ability ability = new ExploitCreatureTriggeredAbility(new DrawCardSourceControllerEffect(2), false);
        ability.addEffect(new LoseLifeSourceControllerEffect(2));
        this.addAbility(ability);
    }

    private BaronZemo(final BaronZemo card) {
        super(card);
    }

    @Override
    public BaronZemo copy() {
        return new BaronZemo(this);
    }
}
