package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.RegenerateSourceEffect;
import mage.abilities.effects.keyword.ScryEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.filter.common.FilterControlledPermanent;
import mage.game.permanent.token.PlantToken;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author NinthWorld
 */
public final class MantisCelestialMadonna extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("Plant");

    static {
        filter.add(SubType.PLANT.getPredicate());
    }

    public MantisCelestialMadonna(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.INSECT);
        this.subtype.add(SubType.NOMAD);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // At the beginning of your upkeep, create a 0/1 green Plant creature token, then scry 1.
        Ability ability = new BeginningOfUpkeepTriggeredAbility(new CreateTokenEffect(new PlantToken()), TargetController.YOU, false);
        ability.addEffect(new ScryEffect(1));
        this.addAbility(ability);

        // Sacrifice a Plant: Regenerate Mantis, Celestial Madonna.
        this.addAbility(new SimpleActivatedAbility(new RegenerateSourceEffect(), new SacrificeTargetCost(new TargetControlledPermanent(filter))));
    }

    private MantisCelestialMadonna(final MantisCelestialMadonna card) {
        super(card);
    }

    @Override
    public MantisCelestialMadonna copy() {
        return new MantisCelestialMadonna(this);
    }
}
