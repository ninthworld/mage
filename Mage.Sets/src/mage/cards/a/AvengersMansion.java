package mage.cards.a;

import java.util.UUID;

import mage.ConditionalMana;
import mage.MageObject;
import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.Cost;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.abilities.mana.ConditionalAnyColorManaAbility;
import mage.abilities.mana.builder.ConditionalManaBuilder;
import mage.abilities.mana.conditional.CreatureCastConditionalMana;
import mage.abilities.mana.conditional.CreatureCastManaCondition;
import mage.abilities.mana.conditional.ManaCondition;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.MulticoloredPredicate;
import mage.game.Game;

/**
 *
 * @author NinthWorld
 */
public final class AvengersMansion extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();
    private static final Condition condition
            = new PermanentsOnTheBattlefieldCondition(filter, ComparisonType.MORE_THAN, 1);

    static {
        filter.add(MulticoloredPredicate.instance);
    }

    public AvengersMansion(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setGold(true);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.a.AvengersTower.class;

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // {T}: Add one mana of any color. Spend this mana only to cast a multicolored creature spell.
        this.addAbility(new ConditionalAnyColorManaAbility(1, new AvengersMansionManaBuilder()));

        // At the beginning of your upkeep, if you control two or more multicolored creatures, transform Avengers Mansion.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                Zone.BATTLEFIELD,
                new ConditionalOneShotEffect(new TransformSourceEffect(true), condition,
                        "if you control two or more multicolored creatures, transform Avengers Mansion"),
                TargetController.YOU, false));
    }

    private AvengersMansion(final AvengersMansion card) {
        super(card);
    }

    @Override
    public AvengersMansion copy() {
        return new AvengersMansion(this);
    }
}

class AvengersMansionManaBuilder extends ConditionalManaBuilder {
    @Override
    public ConditionalMana build(Object... options) {
        return new MulticoloredCreatureCastConditionalMana(this.mana);
    }

    @Override
    public String getRule() {
        return "Spend this mana only to cast a multicolored creature spell";
    }
}

class MulticoloredCreatureCastConditionalMana extends ConditionalMana {

    public MulticoloredCreatureCastConditionalMana(Mana mana) {
        super(mana);
        staticText = "Spend this mana only to cast a multicolored creature spell";
        addCondition(new MulticoloredCreatureCastManaCondition());
    }
}

class MulticoloredCreatureCastManaCondition extends ManaCondition implements Condition {

    @Override
    public boolean apply(Game game, Ability source) {
        if (source instanceof SpellAbility) {
            MageObject object = game.getObject(source.getSourceId());
            if (object != null && object.getColor(game).isMulticolored() && object.isCreature()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean apply(Game game, Ability source, UUID originalId, Cost costToPay) {
        return apply(game, source);
    }
}
