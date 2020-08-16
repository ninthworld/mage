
package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 *
 * @author NinthWorld
 */
public final class Marvel extends ExpansionSet {

    private static final Marvel instance = new Marvel();

    public static Marvel getInstance() {
        return instance;
    }

    private Marvel() {
        super("Marvel the Gathering", "MCU", ExpansionSet.buildDate(2020, 8, 9), SetType.CUSTOM_SET);
        this.blockName = "Marvel the Gathering";
        this.hasBoosters = true;
        this.hasBasicLands = false;

        // 78 Commons       (17.333%)
        // 133 Uncommons    (29.555%)
        // 201 Rares        (44.666%)
        // 38 Mythics       ( 8.444%) (18.9% ratio to rares)
        // = 450 total

//        this.numBoosterLands = 0;
//        this.numBoosterCommon = 10;
//        this.numBoosterUncommon = 3;
//        this.numBoosterRare = 1;
//        this.ratioBoosterMythic = 8;
        this.numBoosterLands = 0;
        this.numBoosterCommon = 3;
        this.numBoosterUncommon = 4;
        this.numBoosterRare = 7;
        this.ratioBoosterMythic = 5;

        cards.add(new SetCardInfo("Abduction", 78, Rarity.UNCOMMON, mage.cards.a.Abduction.class));
        cards.add(new SetCardInfo("Abnormal Endurance", 139, Rarity.COMMON, mage.cards.a.AbnormalEndurance.class));
        cards.add(new SetCardInfo("Acrobatic Maneuver", 1, Rarity.COMMON, mage.cards.a.AcrobaticManeuver.class));
        cards.add(new SetCardInfo("Act of Heroism", 3, Rarity.COMMON, mage.cards.a.ActOfHeroism.class));
        cards.add(new SetCardInfo("Acts of Vengeance", 278, Rarity.RARE, mage.cards.a.ActsOfVengeance.class)); // WIP
        cards.add(new SetCardInfo("Adam Warlock, Savior of Worlds", 5, Rarity.RARE, mage.cards.a.AdamWarlockSaviorOfWorlds.class));
        cards.add(new SetCardInfo("Adamantium", 392, Rarity.RARE, mage.cards.a.Adamantium.class));
        cards.add(new SetCardInfo("Amora the Enchantress", 280, Rarity.RARE, mage.cards.a.AmoraTheEnchantress.class));
        cards.add(new SetCardInfo("Age of Apocalpyse", 279, Rarity.RARE, mage.cards.a.AgeOfApocalpyse.class));
        cards.add(new SetCardInfo("Ancestral Vision", 79, Rarity.RARE, mage.cards.a.AncestralVision.class));
        cards.add(new SetCardInfo("Angel", 7, Rarity.UNCOMMON, mage.cards.a.Angel.class)); // WIP
        cards.add(new SetCardInfo("Annihilation", 140, Rarity.RARE, mage.cards.a.Annihilation.class));
        cards.add(new SetCardInfo("Annihilus", 141, Rarity.MYTHIC, mage.cards.a.Annihilus.class)); // WIP
        cards.add(new SetCardInfo("Apocalypse", 281, Rarity.MYTHIC, mage.cards.a.ApocalypseMCU.class));
        cards.add(new SetCardInfo("Archangel", 7, Rarity.UNCOMMON, mage.cards.a.AngelArchangel.class));
        cards.add(new SetCardInfo("Asgard", 459, Rarity.RARE, mage.cards.a.Asgard.class));
        cards.add(new SetCardInfo("Atlantis", 460, Rarity.UNCOMMON, mage.cards.a.Atlantis.class));
        cards.add(new SetCardInfo("Aurora", 282, Rarity.UNCOMMON, mage.cards.a.Aurora.class));
        cards.add(new SetCardInfo("Avalanche", 189, Rarity.RARE, mage.cards.a.AvalancheMCU.class));
        cards.add(new SetCardInfo("Avengers Mansion", 461, Rarity.RARE, mage.cards.a.AvengersMansion.class));
        cards.add(new SetCardInfo("Avengers Tower", 461, Rarity.RARE, mage.cards.a.AvengersTower.class));
        cards.add(new SetCardInfo("Banshee", 9, Rarity.UNCOMMON, mage.cards.b.BansheeMCU.class));
        cards.add(new SetCardInfo("Barge In", 190, Rarity.COMMON, mage.cards.b.BargeIn.class));
        cards.add(new SetCardInfo("Baron Mordo", 283, Rarity.RARE, mage.cards.b.BaronMordo.class));
        cards.add(new SetCardInfo("Baron Strucker", 142, Rarity.UNCOMMON, mage.cards.b.BaronStrucker.class));
        cards.add(new SetCardInfo("Beast Within", 237, Rarity.UNCOMMON, mage.cards.b.BeastWithin.class));
        cards.add(new SetCardInfo("Become Immense", 238, Rarity.UNCOMMON, mage.cards.b.BecomeImmense.class));
        cards.add(new SetCardInfo("Biogenic Upgrade", 239, Rarity.UNCOMMON, mage.cards.b.BiogenicUpgrade.class));
        cards.add(new SetCardInfo("Black Cat", 191, Rarity.RARE, mage.cards.b.BlackCatMCU.class));
        cards.add(new SetCardInfo("Black Market", 144, Rarity.RARE, mage.cards.b.BlackMarket.class));
        cards.add(new SetCardInfo("Brainstorm", 81, Rarity.UNCOMMON, mage.cards.b.Brainstorm.class));
        cards.add(new SetCardInfo("Breaking Point", 192, Rarity.RARE, mage.cards.b.BreakingPoint.class));
        cards.add(new SetCardInfo("Broken Bond", 241, Rarity.COMMON, mage.cards.b.BrokenBond.class));
        cards.add(new SetCardInfo("Bucky Barnes", 17, Rarity.UNCOMMON, mage.cards.b.BuckyBarnes.class)); // WIP
        cards.add(new SetCardInfo("Built to Last", 19, Rarity.COMMON, mage.cards.b.BuiltToLast.class));
        cards.add(new SetCardInfo("Campaign of Vengeance", 288, Rarity.UNCOMMON, mage.cards.c.CampaignOfVengeance.class));
        cards.add(new SetCardInfo("Carnage", 147, Rarity.RARE, mage.cards.c.Carnage.class));
        cards.add(new SetCardInfo("Chance for Glory", 289, Rarity.MYTHIC, mage.cards.c.ChanceForGlory.class));
        cards.add(new SetCardInfo("Clear Shot", 243, Rarity.UNCOMMON, mage.cards.c.ClearShot.class));
        cards.add(new SetCardInfo("Cletus Kasady", 147, Rarity.RARE, mage.cards.c.CletusKasady.class));
        cards.add(new SetCardInfo("Clone Legion", 84, Rarity.MYTHIC, mage.cards.c.CloneLegion.class));
        cards.add(new SetCardInfo("Colossal Might", 292, Rarity.COMMON, mage.cards.c.ColossalMight.class));
        cards.add(new SetCardInfo("Colossus", 57, Rarity.UNCOMMON, mage.cards.c.Colossus.class));
        cards.add(new SetCardInfo("Costly Plunder", 149, Rarity.COMMON, mage.cards.c.CostlyPlunder.class));
        cards.add(new SetCardInfo("Courage in Crisis", 244, Rarity.COMMON, mage.cards.c.CourageInCrisis.class));
        cards.add(new SetCardInfo("Cyclonic Rift", 85, Rarity.RARE, mage.cards.c.CyclonicRift.class));
        cards.add(new SetCardInfo("Daring Demolition", 150, Rarity.COMMON, mage.cards.d.DaringDemolition.class));
        cards.add(new SetCardInfo("Dark Deal", 151, Rarity.UNCOMMON, mage.cards.d.DarkDeal.class));
        cards.add(new SetCardInfo("Dark Ritual", 152, Rarity.COMMON, mage.cards.d.DarkRitual.class));
        cards.add(new SetCardInfo("Defense Grid", 406, Rarity.RARE, mage.cards.d.DefenseGrid.class));
        cards.add(new SetCardInfo("Desperate Stand", 295, Rarity.UNCOMMON, mage.cards.d.DesperateStand.class));
        cards.add(new SetCardInfo("Detainment Spell", 27, Rarity.COMMON, mage.cards.d.DetainmentSpell.class));
        cards.add(new SetCardInfo("Elixir of Immortality", 407, Rarity.UNCOMMON, mage.cards.e.ElixirOfImmortality.class));
        cards.add(new SetCardInfo("Empowered Autogenerator", 408, Rarity.RARE, mage.cards.e.EmpoweredAutogenerator.class));
        cards.add(new SetCardInfo("End Hostilities", 31, Rarity.RARE, mage.cards.e.EndHostilities.class));
        cards.add(new SetCardInfo("Engineered Explosives", 409, Rarity.MYTHIC, mage.cards.e.EngineeredExplosives.class));
        cards.add(new SetCardInfo("Engineered Plague", 154, Rarity.UNCOMMON, mage.cards.e.EngineeredPlague.class));
        cards.add(new SetCardInfo("Enter the Unknown", 246, Rarity.UNCOMMON, mage.cards.e.EnterTheUnknown.class));
        cards.add(new SetCardInfo("Evacuation", 89, Rarity.RARE, mage.cards.e.Evacuation.class));
        cards.add(new SetCardInfo("Evolution Vat", 410, Rarity.RARE, mage.cards.e.EvolutionVat.class));
        cards.add(new SetCardInfo("Evolutionary Leap", 247, Rarity.RARE, mage.cards.e.EvolutionaryLeap.class));
        cards.add(new SetCardInfo("Explosive Apparatus", 411, Rarity.COMMON, mage.cards.e.ExplosiveApparatus.class));
        cards.add(new SetCardInfo("Eyes of the Watcher", 90, Rarity.UNCOMMON, mage.cards.e.EyesOfTheWatcher.class));
        cards.add(new SetCardInfo("Fabricate", 91, Rarity.UNCOMMON, mage.cards.f.Fabricate.class));
        cards.add(new SetCardInfo("Fabrication Module", 413, Rarity.UNCOMMON, mage.cards.f.FabricationModule.class));
        cards.add(new SetCardInfo("Faithless Looting", 200, Rarity.COMMON, mage.cards.f.FaithlessLooting.class));
        cards.add(new SetCardInfo("Fate Transfer", 361, Rarity.COMMON, mage.cards.f.FateTransfer.class));
        cards.add(new SetCardInfo("Fiery Confluence", 201, Rarity.RARE, mage.cards.f.FieryConfluence.class));
        cards.add(new SetCardInfo("Finest Hour", 301, Rarity.RARE, mage.cards.f.FinestHour.class));
        cards.add(new SetCardInfo("First Response", 33, Rarity.UNCOMMON, mage.cards.f.FirstResponse.class));
        cards.add(new SetCardInfo("Fractured Identity", 302, Rarity.RARE, mage.cards.f.FracturedIdentity.class));
        cards.add(new SetCardInfo("Fractured Loyalty", 203, Rarity.UNCOMMON, mage.cards.f.FracturedLoyalty.class));
        cards.add(new SetCardInfo("Fuel for the Cause", 92, Rarity.COMMON, mage.cards.f.FuelForTheCause.class));
        cards.add(new SetCardInfo("Gamble", 205, Rarity.RARE, mage.cards.g.Gamble.class));
        cards.add(new SetCardInfo("Gang Up", 156, Rarity.UNCOMMON, mage.cards.g.GangUp.class));
        cards.add(new SetCardInfo("Genosha", 467, Rarity.RARE, mage.cards.g.Genosha.class));
        cards.add(new SetCardInfo("Giant Man", 367, Rarity.RARE, mage.cards.g.GiantMan.class));
        cards.add(new SetCardInfo("Glaring Spotlight", 415, Rarity.RARE, mage.cards.g.GlaringSpotlight.class));
        cards.add(new SetCardInfo("Glimmer of Genius", 93, Rarity.UNCOMMON, mage.cards.g.GlimmerOfGenius.class));
        cards.add(new SetCardInfo("Goblin Glider", 416, Rarity.UNCOMMON, mage.cards.g.GoblinGliderMCU.class));
        cards.add(new SetCardInfo("Grapple with the Past", 248, Rarity.COMMON, mage.cards.g.GrappleWithThePast.class));
        cards.add(new SetCardInfo("Greater Good", 249, Rarity.RARE, mage.cards.g.GreaterGood.class));
        cards.add(new SetCardInfo("Green Goblin", 167, Rarity.RARE, mage.cards.g.GreenGoblin.class));
        cards.add(new SetCardInfo("Grotesque Mutation", 157, Rarity.COMMON, mage.cards.g.GrotesqueMutation.class));
        cards.add(new SetCardInfo("Growing Ranks", 366, Rarity.RARE, mage.cards.g.GrowingRanks.class));
        cards.add(new SetCardInfo("Hank Pym", 367, Rarity.RARE, mage.cards.h.HankPym.class));
        cards.add(new SetCardInfo("Helm of the Host", 418, Rarity.RARE, mage.cards.h.HelmOfTheHost.class));
        cards.add(new SetCardInfo("Hex", 158, Rarity.RARE, mage.cards.h.Hex.class));
        cards.add(new SetCardInfo("High Ground", 37, Rarity.UNCOMMON, mage.cards.h.HighGround.class));
        cards.add(new SetCardInfo("Hijack", 207, Rarity.COMMON, mage.cards.h.Hijack.class));
        cards.add(new SetCardInfo("Hostile Realm", 208, Rarity.COMMON, mage.cards.h.HostileRealm.class));
        cards.add(new SetCardInfo("Hull Breach", 306, Rarity.COMMON, mage.cards.h.HullBreach.class));
        cards.add(new SetCardInfo("Icy Blast", 96, Rarity.RARE, mage.cards.i.IcyBlast.class));
        cards.add(new SetCardInfo("Impact Tremors", 209, Rarity.COMMON, mage.cards.i.ImpactTremors.class));
        cards.add(new SetCardInfo("Incite War", 210, Rarity.COMMON, mage.cards.i.InciteWar.class));
        cards.add(new SetCardInfo("Increasing Vengeance", 211, Rarity.RARE, mage.cards.i.IncreasingVengeance.class));
        cards.add(new SetCardInfo("Inferno Fist", 212, Rarity.COMMON, mage.cards.i.InfernoFist.class));
        cards.add(new SetCardInfo("Invisibility", 98, Rarity.COMMON, mage.cards.i.Invisibility.class));
        cards.add(new SetCardInfo("Ionize", 308, Rarity.RARE, mage.cards.i.Ionize.class));
        cards.add(new SetCardInfo("Karma", 102, Rarity.UNCOMMON, mage.cards.k.KarmaMCU.class));
        cards.add(new SetCardInfo("Kill Shot", 41, Rarity.COMMON, mage.cards.k.KillShot.class));
        cards.add(new SetCardInfo("Liquimetal Coating", 423, Rarity.UNCOMMON, mage.cards.l.LiquimetalCoating.class));
        cards.add(new SetCardInfo("Living Death", 160, Rarity.RARE, mage.cards.l.LivingDeath.class));
        cards.add(new SetCardInfo("Long-Term Plans", 104, Rarity.UNCOMMON, mage.cards.l.LongTermPlans.class));
        cards.add(new SetCardInfo("Malfunction", 108, Rarity.COMMON, mage.cards.m.Malfunction.class));
        cards.add(new SetCardInfo("Mark for Death", 217, Rarity.UNCOMMON, mage.cards.m.MarkForDeath.class));
        cards.add(new SetCardInfo("Merciless Eviction", 313, Rarity.RARE, mage.cards.m.MercilessEviction.class));
        cards.add(new SetCardInfo("Metallic Rebuke", 109, Rarity.COMMON, mage.cards.m.MetallicRebuke.class));
        cards.add(new SetCardInfo("Metallurgic Summonings", 110, Rarity.MYTHIC, mage.cards.m.MetallurgicSummonings.class));
        cards.add(new SetCardInfo("Might Beyond Reason", 257, Rarity.COMMON, mage.cards.m.MightBeyondReason.class));
        cards.add(new SetCardInfo("Mimic Vat", 426, Rarity.RARE, mage.cards.m.MimicVat.class));
        cards.add(new SetCardInfo("Miraculous Recovery", 47, Rarity.UNCOMMON, mage.cards.m.MiraculousRecovery.class));
        cards.add(new SetCardInfo("Mission Briefing", 111, Rarity.RARE, mage.cards.m.MissionBriefing.class));
        cards.add(new SetCardInfo("Molten Psyche", 218, Rarity.RARE, mage.cards.m.MoltenPsyche.class));
        cards.add(new SetCardInfo("Momentous Fall", 258, Rarity.RARE, mage.cards.m.MomentousFall.class));
        cards.add(new SetCardInfo("Morbid Curiosity", 165, Rarity.UNCOMMON, mage.cards.m.MorbidCuriosity.class));
        cards.add(new SetCardInfo("Murder Investigation", 49, Rarity.UNCOMMON, mage.cards.m.MurderInvestigation.class));
        cards.add(new SetCardInfo("Mystic Confluence", 113, Rarity.RARE, mage.cards.m.MysticConfluence.class));
        cards.add(new SetCardInfo("Mystic Retrieval", 114, Rarity.UNCOMMON, mage.cards.m.MysticRetrieval.class));
        cards.add(new SetCardInfo("Necrosha", 467, Rarity.RARE, mage.cards.n.Necrosha.class));
        cards.add(new SetCardInfo("Neoform", 319, Rarity.UNCOMMON, mage.cards.n.Neoform.class));
        cards.add(new SetCardInfo("No Mercy", 166, Rarity.RARE, mage.cards.n.NoMercy.class));
        cards.add(new SetCardInfo("Norman Osborn", 167, Rarity.RARE, mage.cards.n.NormanOsborn.class));
        cards.add(new SetCardInfo("Open the Armory", 53, Rarity.UNCOMMON, mage.cards.o.OpenTheArmory.class));
        cards.add(new SetCardInfo("Overrun", 260, Rarity.UNCOMMON, mage.cards.o.Overrun.class));
        cards.add(new SetCardInfo("Painful Lesson", 168, Rarity.COMMON, mage.cards.p.PainfulLesson.class));
        cards.add(new SetCardInfo("Parallel Lives", 261, Rarity.RARE, mage.cards.p.ParallelLives.class));
        cards.add(new SetCardInfo("Path of Bravery", 55, Rarity.RARE, mage.cards.p.PathOfBravery.class));
        cards.add(new SetCardInfo("Peter Rasputin", 57, Rarity.UNCOMMON, mage.cards.p.PeterRasputin.class));
        cards.add(new SetCardInfo("Pilfered Plans", 324, Rarity.COMMON, mage.cards.p.PilferedPlans.class));
        cards.add(new SetCardInfo("Planar Portal", 431, Rarity.RARE, mage.cards.p.PlanarPortal.class));
        cards.add(new SetCardInfo("Portal of Sanctuary", 118, Rarity.UNCOMMON, mage.cards.p.PortalOfSanctuary.class));
        cards.add(new SetCardInfo("Power Conduit", 432, Rarity.UNCOMMON, mage.cards.p.PowerConduit.class));
        cards.add(new SetCardInfo("Predatory Hunger", 263, Rarity.COMMON, mage.cards.p.PredatoryHunger.class));
        cards.add(new SetCardInfo("Price of Betrayal", 170, Rarity.UNCOMMON, mage.cards.p.PriceOfBetrayal.class));
        cards.add(new SetCardInfo("Putrefy", 327, Rarity.UNCOMMON, mage.cards.p.Putrefy.class));
        cards.add(new SetCardInfo("Raise the Alarm", 59, Rarity.COMMON, mage.cards.r.RaiseTheAlarm.class));
        cards.add(new SetCardInfo("Rampant Growth", 266, Rarity.COMMON, mage.cards.r.RampantGrowth.class));
        cards.add(new SetCardInfo("Ransack the Lab", 171, Rarity.COMMON, mage.cards.r.RansackTheLab.class));
        cards.add(new SetCardInfo("Reality Scramble", 225, Rarity.RARE, mage.cards.r.RealityScramble.class));
        cards.add(new SetCardInfo("Rebuild", 119, Rarity.UNCOMMON, mage.cards.r.Rebuild.class));
        cards.add(new SetCardInfo("Regrowth", 267, Rarity.UNCOMMON, mage.cards.r.Regrowth.class));
        cards.add(new SetCardInfo("Rejuvenation Chamber", 434, Rarity.UNCOMMON, mage.cards.r.RejuvenationChamber.class));
        cards.add(new SetCardInfo("Retaliate", 63, Rarity.RARE, mage.cards.r.Retaliate.class));
        cards.add(new SetCardInfo("Reverse Engineer", 120, Rarity.UNCOMMON, mage.cards.r.ReverseEngineer.class));
        cards.add(new SetCardInfo("Revoke Existence", 65, Rarity.COMMON, mage.cards.r.RevokeExistence.class));
        cards.add(new SetCardInfo("Rhythm of the Wild", 329, Rarity.UNCOMMON, mage.cards.r.RhythmOfTheWild.class));
        cards.add(new SetCardInfo("Righteous Authority", 330, Rarity.RARE, mage.cards.r.RighteousAuthority.class));
        cards.add(new SetCardInfo("Riot Gear", 435, Rarity.COMMON, mage.cards.r.RiotGear.class));
        cards.add(new SetCardInfo("Salvaging Station", 439, Rarity.RARE, mage.cards.s.SalvagingStation.class));
        cards.add(new SetCardInfo("Sasquatch", 277, Rarity.UNCOMMON, mage.cards.s.Sasquatch.class));
        cards.add(new SetCardInfo("Scheming Symmetry", 172, Rarity.RARE, mage.cards.s.SchemingSymmetry.class));
        cards.add(new SetCardInfo("Scour All Possibilities", 121, Rarity.COMMON, mage.cards.s.ScourAllPossibilities.class));
        cards.add(new SetCardInfo("Searing Meditation", 334, Rarity.RARE, mage.cards.s.SearingMeditation.class));
        cards.add(new SetCardInfo("Seize the Day", 228, Rarity.RARE, mage.cards.s.SeizeTheDay.class));
        cards.add(new SetCardInfo("Sentinel Tower", 442, Rarity.RARE, mage.cards.s.SentinelTower.class));
        cards.add(new SetCardInfo("Sentry", 67, Rarity.RARE, mage.cards.s.Sentry.class));
        cards.add(new SetCardInfo("Shared Animosity", 229, Rarity.RARE, mage.cards.s.SharedAnimosity.class));
        cards.add(new SetCardInfo("Shifting Loyalties", 123, Rarity.UNCOMMON, mage.cards.s.ShiftingLoyalties.class));
        cards.add(new SetCardInfo("Shocker", 230, Rarity.UNCOMMON, mage.cards.s.ShockerMCU.class));
        cards.add(new SetCardInfo("Shoulder to Shoulder", 69, Rarity.COMMON, mage.cards.s.ShoulderToShoulder.class));
        cards.add(new SetCardInfo("Shrapnel Blast", 231, Rarity.UNCOMMON, mage.cards.s.ShrapnelBlast.class));
        cards.add(new SetCardInfo("Silent Gravestone", 443, Rarity.RARE, mage.cards.s.SilentGravestone.class));
        cards.add(new SetCardInfo("Sinister Sabotage", 124, Rarity.UNCOMMON, mage.cards.s.SinisterSabotage.class));
        cards.add(new SetCardInfo("Sins of the Past", 175, Rarity.RARE, mage.cards.s.SinsOfThePast.class));
        cards.add(new SetCardInfo("Sixth Sense", 270, Rarity.UNCOMMON, mage.cards.s.SixthSense.class));
        cards.add(new SetCardInfo("Smash to Smithereens", 232, Rarity.COMMON, mage.cards.s.SmashToSmithereens.class));
        cards.add(new SetCardInfo("Solidarity of Heroes", 271, Rarity.UNCOMMON, mage.cards.s.SolidarityOfHeroes.class));
        cards.add(new SetCardInfo("Spontaneous Mutation", 125, Rarity.COMMON, mage.cards.s.SpontaneousMutation.class));
        cards.add(new SetCardInfo("Spy Network", 126, Rarity.COMMON, mage.cards.s.SpyNetwork.class));
        cards.add(new SetCardInfo("Steady Aim", 272, Rarity.COMMON, mage.cards.s.SteadyAim.class));
        cards.add(new SetCardInfo("Steady Progress", 127, Rarity.COMMON, mage.cards.s.SteadyProgress.class));
        cards.add(new SetCardInfo("Stealth Mission", 128, Rarity.COMMON, mage.cards.s.StealthMission.class));
        cards.add(new SetCardInfo("Stolen Goods", 129, Rarity.RARE, mage.cards.s.StolenGoods.class));
        cards.add(new SetCardInfo("Strength of Arms", 73, Rarity.COMMON, mage.cards.s.StrengthOfArms.class));
        cards.add(new SetCardInfo("Stunning Reversal", 176, Rarity.MYTHIC, mage.cards.s.StunningReversal.class));
        cards.add(new SetCardInfo("Sword of Vengeance", 447, Rarity.RARE, mage.cards.s.SwordOfVengeance.class));
        cards.add(new SetCardInfo("Talent of the Telepath", 131, Rarity.RARE, mage.cards.t.TalentOfTheTelepath.class));
        cards.add(new SetCardInfo("Telekinesis", 132, Rarity.COMMON, mage.cards.t.Telekinesis.class));
        cards.add(new SetCardInfo("Teleportal", 341, Rarity.UNCOMMON, mage.cards.t.Teleportal.class));
        cards.add(new SetCardInfo("Temporal Manipulation", 133, Rarity.MYTHIC, mage.cards.t.TemporalManipulation.class));
        cards.add(new SetCardInfo("Terrifying Presence", 273, Rarity.COMMON, mage.cards.t.TerrifyingPresence.class));
        cards.add(new SetCardInfo("The Winter Soldier", 17, Rarity.UNCOMMON, mage.cards.t.TheWinterSoldier.class));
        cards.add(new SetCardInfo("Thrill of Possibility", 234, Rarity.COMMON, mage.cards.t.ThrillOfPossibility.class));
        cards.add(new SetCardInfo("Time Wipe", 343, Rarity.RARE, mage.cards.t.TimeWipe.class));
        cards.add(new SetCardInfo("Timely Reinforcements", 74, Rarity.UNCOMMON, mage.cards.t.TimelyReinforcements.class));
        cards.add(new SetCardInfo("Titanic Brawl", 274, Rarity.COMMON, mage.cards.t.TitanicBrawl.class));
        cards.add(new SetCardInfo("Torment of Venom", 178, Rarity.COMMON, mage.cards.t.TormentOfVenom.class));
        cards.add(new SetCardInfo("Training Grounds", 135, Rarity.RARE, mage.cards.t.TrainingGrounds.class));
        cards.add(new SetCardInfo("Turn the Tables", 75, Rarity.RARE, mage.cards.t.TurnTheTables.class));
        cards.add(new SetCardInfo("Underworld Connections", 180, Rarity.RARE, mage.cards.u.UnderworldConnections.class));
        cards.add(new SetCardInfo("Unexpected Results", 345, Rarity.RARE, mage.cards.u.UnexpectedResults.class));
        cards.add(new SetCardInfo("Unflinching Courage", 346, Rarity.UNCOMMON, mage.cards.u.UnflinchingCourage.class));
        cards.add(new SetCardInfo("Unity of Purpose", 137, Rarity.UNCOMMON, mage.cards.u.UnityOfPurpose.class));
        cards.add(new SetCardInfo("Unlikely Aid", 181, Rarity.COMMON, mage.cards.u.UnlikelyAid.class));
        cards.add(new SetCardInfo("Unmask", 182, Rarity.MYTHIC, mage.cards.u.Unmask.class));
        cards.add(new SetCardInfo("Urban Evolution", 347, Rarity.UNCOMMON, mage.cards.u.UrbanEvolution.class));
        cards.add(new SetCardInfo("Valorous Stance", 76, Rarity.UNCOMMON, mage.cards.v.ValorousStance.class));
        cards.add(new SetCardInfo("Verdant Confluence", 276, Rarity.RARE, mage.cards.v.VerdantConfluence.class));
        cards.add(new SetCardInfo("Vicious Offering", 183, Rarity.COMMON, mage.cards.v.ViciousOffering.class));
        cards.add(new SetCardInfo("Vicious Rumors", 184, Rarity.COMMON, mage.cards.v.ViciousRumors.class));
        cards.add(new SetCardInfo("Vigilante Justice", 235, Rarity.UNCOMMON, mage.cards.v.VigilanteJustice.class));
        cards.add(new SetCardInfo("Vindicate", 348, Rarity.RARE, mage.cards.v.Vindicate.class));
        cards.add(new SetCardInfo("Void", 34, Rarity.RARE, mage.cards.s.SentryVoid.class));
        cards.add(new SetCardInfo("Walter Langkowski", 277, Rarity.UNCOMMON, mage.cards.w.WalterLangkowski.class));
        cards.add(new SetCardInfo("Warpath", 236, Rarity.UNCOMMON, mage.cards.w.WarpathMCU.class));
        cards.add(new SetCardInfo("Weapon Rack", 458, Rarity.COMMON, mage.cards.w.WeaponRack.class));
        cards.add(new SetCardInfo("Worst Fears", 187, Rarity.MYTHIC, mage.cards.w.WorstFears.class));
        cards.add(new SetCardInfo("Wretched Confluence", 188, Rarity.RARE, mage.cards.w.WretchedConfluence.class));
    }
}
