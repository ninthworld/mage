package mage.cards.t;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.keyword.CantAttackAloneAbility;
import mage.abilities.keyword.UnstableAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.VigilanceAbility;
import mage.abilities.keyword.ReachAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Toad extends CardImpl {

    public Toad(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.FROG);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // Reach
        this.addAbility(ReachAbility.getInstance());

        // Toad can't attack alone.
        this.addAbility(new CantAttackAloneAbility());

        // Unstable
        this.addAbility(new UnstableAbility());
    }

    private Toad(final Toad card) {
        super(card);
    }

    @Override
    public Toad copy() {
        return new Toad(this);
    }
}
