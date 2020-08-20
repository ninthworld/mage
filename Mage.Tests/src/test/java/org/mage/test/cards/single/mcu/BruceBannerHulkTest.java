package org.mage.test.cards.single.mcu;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import mage.counters.CounterType;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

public class BruceBannerHulkTest extends CardTestPlayerBase {

    @Test
    public void test_hulk_transform_controllerturn() {
        playerA.addCounters(CounterType.ENERGY.createInstance(5), currentGame);
        addCard(Zone.BATTLEFIELD, playerA, "Bruce Banner", 1);
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Pay {E}{E}{E}{E}{E}: ");

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerA, "Bruce Banner", 1);
    }

    @Test
    public void test_hulk_nottransform_dealdamageplayer_controllerturn() {
        playerA.addCounters(CounterType.ENERGY.createInstance(5), currentGame);
        addCard(Zone.BATTLEFIELD, playerA, "Bruce Banner", 1);
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Pay {E}{E}{E}{E}{E}: ");

        attack(1, playerA, "Hulk");

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerA, "Hulk", 1);
    }

    @Test
    public void test_hulk_nottransform_dealdamagecreature_controllerturn() {
        addCard(Zone.BATTLEFIELD, playerB, "Black Widow", 1);

        playerA.addCounters(CounterType.ENERGY.createInstance(5), currentGame);
        addCard(Zone.BATTLEFIELD, playerA, "Bruce Banner", 1);

        activateAbility(3, PhaseStep.PRECOMBAT_MAIN, playerA, "Pay {E}{E}{E}{E}{E}: ");

        attack(3, playerA, "Hulk", playerB);
        block(3, playerB, "Black Widow", "Hulk");

        setStrictChooseMode(false);
        setStopAt(4, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerA, "Hulk", 1);
    }

    @Test
    public void test_hulk_nottransform_receivedamage_controllerturn() {
        addCard(Zone.BATTLEFIELD, playerB, "Black Widow", 1);

        playerA.addCounters(CounterType.ENERGY.createInstance(5), currentGame);
        addCard(Zone.BATTLEFIELD, playerA, "Bruce Banner", 1);

        activateAbility(2, PhaseStep.PRECOMBAT_MAIN, playerA, "Pay {E}{E}{E}{E}{E}: ");

        attack(2, playerB, "Black Widow", playerA);
        block(2, playerA, "Hulk", "Black Widow");

        setStrictChooseMode(true);
        setStopAt(3, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerA, "Hulk", 1);
    }
}
