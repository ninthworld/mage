package mage.cards.w;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.*;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class WarMachine extends CardImpl {

    public WarMachine(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CONSTRUCT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        this.color.setRed(true);
        this.color.setWhite(true);

        this.transformable = true;
        this.nightCard = true;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Arsenal 2
        this.addAbility(new ArsenalAbility(2));

        // {T}: Transform War Machine.
        this.addAbility(new TransformAbility());
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(false), new TapSourceCost()));
    }

    private WarMachine(final WarMachine card) {
        super(card);
    }

    @Override
    public WarMachine copy() {
        return new WarMachine(this);
    }
}
