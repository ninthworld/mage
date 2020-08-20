package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.LookAtTargetPlayerHandEffect;
import mage.abilities.effects.common.TapTargetEffect;
import mage.abilities.effects.common.discard.DiscardControllerEffect;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.abilities.keyword.AgilityAbility;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPlayer;
import mage.target.common.TargetCreaturePermanent;
import mage.util.CardUtil;

/**
 *
 * @author NinthWorld
 */
public final class Psylocke extends CardImpl {

    public Psylocke(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.NINJA);
        this.subtype.add(SubType.TELEPATH);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        this.nightCard = true;
        this.transformable = true;

        // Agility
        this.addAbility(new AgilityAbility());

        // Whenever you cast a noncreature spell, you may look at target player's hand.
        Ability ability1 = new SpellCastControllerTriggeredAbility(new LookAtTargetPlayerHandEffect(), StaticFilters.FILTER_SPELL_NON_CREATURE, true);
        ability1.addTarget(new TargetPlayer());
        this.addAbility(ability1);

        // {U}{B}, {T}: Tap target creature. That creature's controller discards a card.
        Ability ability2 = new SimpleActivatedAbility(new TapTargetEffect(), new CompositeCost(new ManaCostsImpl("{U}{B}"), new TapSourceCost(), "{U}{B}, {T}"));
        ability2.addTarget(new TargetCreaturePermanent());
        ability2.addEffect(new PsylockeDiscardTargetControllerEffect(1));
        this.addAbility(ability2);
    }

    private Psylocke(final Psylocke card) {
        super(card);
    }

    @Override
    public Psylocke copy() {
        return new Psylocke(this);
    }
}

class PsylockeDiscardTargetControllerEffect extends OneShotEffect {

    protected DynamicValue amount;
    protected boolean randomDiscard;

    public PsylockeDiscardTargetControllerEffect(DynamicValue amount) {
        this(amount, false);
    }

    public PsylockeDiscardTargetControllerEffect(DynamicValue amount, boolean randomDiscard) {
        super(Outcome.Discard);
        this.randomDiscard = randomDiscard;
        this.amount = amount;
    }

    public PsylockeDiscardTargetControllerEffect(int amount) {
        this(StaticValue.get(amount));
    }

    public PsylockeDiscardTargetControllerEffect(int amount, boolean randomDiscard) {
        super(Outcome.Discard);
        this.randomDiscard = randomDiscard;
        this.amount = StaticValue.get(amount);
    }

    public PsylockeDiscardTargetControllerEffect(final PsylockeDiscardTargetControllerEffect effect) {
        super(effect);
        this.amount = effect.amount.copy();
        this.randomDiscard = effect.randomDiscard;
    }

    @Override
    public PsylockeDiscardTargetControllerEffect copy() {
        return new PsylockeDiscardTargetControllerEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (UUID targetPermanentId : targetPointer.getTargets(game, source)) {
            Permanent permanent = game.getPermanent(targetPermanentId);
            if (permanent != null) {
                Player player = game.getPlayer(permanent.getControllerId());
                if (player != null) {
                    player.discard(amount.calculate(game, source, this), randomDiscard, source, game);
                }
            }
        }
        return true;
    }

    @Override
    public String getText(Mode mode) {
        if (staticText != null && !staticText.isEmpty()) {
            return staticText;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("That creature's controller discards ");
        if (amount.toString().equals("1")) {
            sb.append("a card");
        } else {
            sb.append(CardUtil.numberToText(amount.toString())).append(" cards");
        }
        if (randomDiscard) {
            sb.append(" at random");
        }
        String message = amount.getMessage();
        if (!message.isEmpty()) {
            sb.append(" for each ");
        }
        sb.append(message);
        return sb.toString();
    }
}