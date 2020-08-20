package mage.cards.b;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.AttacksAttachedTriggeredAbility;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.DealsCombatDamageToACreatureTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.effects.common.discard.DiscardControllerEffect;
import mage.abilities.effects.common.ruleModifying.CantRegenerateTargetEffect;
import mage.abilities.keyword.DoubleStrikeAbility;
import mage.abilities.keyword.EquipAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class BlackBladeOfMuramasa extends CardImpl {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("Silver Samurai");

    static {
        filter.add(new NamePredicate("Silver Samurai"));
    }

    public BlackBladeOfMuramasa(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{3}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature gets +3/+1, has double strike and "Creatures dealt combat damage by this creature can't regenerate this turn."
        Ability ability = new SimpleStaticAbility(new BoostEquippedEffect(3, 1));
        ability.addEffect(new GainAbilityAttachedEffect(DoubleStrikeAbility.getInstance(), AttachmentType.EQUIPMENT));
        ability.addEffect(new GainAbilityAttachedEffect(new DealsCombatDamageToACreatureTriggeredAbility(
                new CantRegenerateTargetEffect(Duration.EndOfTurn, "can't regenerate this turn"),
                false, true),
                AttachmentType.EQUIPMENT));
        this.addAbility(ability);

        // Whenever equipped creature attacks, discard a card.
        this.addAbility(new AttacksAttachedTriggeredAbility(new DiscardControllerEffect(1), false));

        // Equip Silver Samurai {2}
        this.addAbility(new EquipAbility(Outcome.AddAbility, new GenericManaCost(2), new TargetControlledCreaturePermanent(filter)));

        // Equip {4}
        this.addAbility(new EquipAbility(4));
    }

    private BlackBladeOfMuramasa(final BlackBladeOfMuramasa card) {
        super(card);
    }

    @Override
    public BlackBladeOfMuramasa copy() {
        return new BlackBladeOfMuramasa(this);
    }
}
