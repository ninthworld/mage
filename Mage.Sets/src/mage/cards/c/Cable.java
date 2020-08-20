package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.*;
import mage.constants.*;
import mage.abilities.keyword.HexproofAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetNonlandPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Cable extends CardImpl {

    public Cable(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.MERCENARY);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Hexproof
        this.addAbility(HexproofAbility.getInstance());

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // At the beginning of your upkeep, pay {U} or sacrifice Cable.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new SacrificeSourceUnlessPaysEffect(new ManaCostsImpl("{U}")), TargetController.YOU, false));

        // Whenever Cable attacks, you may exile target nonland permanent. At the beginning of the next end step, return that permanent to the battlefield under its owner's control.
        Ability ability = new AttacksTriggeredAbility(new CableEffect(), true);
        ability.addTarget(new TargetNonlandPermanent());
        this.addAbility(ability);
    }

    private Cable(final Cable card) {
        super(card);
    }

    @Override
    public Cable copy() {
        return new Cable(this);
    }
}

class CableEffect extends OneShotEffect {

    public CableEffect() {
        super(Outcome.Detriment);
        staticText = "exile target nonland permanent. At the beginning of the next end step, return that permanent to the battlefield under its owner's control.";
    }

    public CableEffect(final CableEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getFirstTarget());
        if (permanent != null) {
            if (permanent.moveToExile(source.getSourceId(), "Cable", source.getSourceId(), game)) {
                AtTheBeginOfNextEndStepDelayedTriggeredAbility delayedAbility = new AtTheBeginOfNextEndStepDelayedTriggeredAbility(new ReturnFromExileEffect(source.getSourceId(), Zone.BATTLEFIELD));
                game.addDelayedTriggeredAbility(delayedAbility, source);
                return true;
            }
        }
        return false;
    }

    @Override
    public CableEffect copy() {
        return new CableEffect(this);
    }
}
