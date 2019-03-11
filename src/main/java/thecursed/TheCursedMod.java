package thecursed;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import thecursed.cards.attack.*;
import thecursed.cards.curse.Dregs;
import thecursed.cards.power.*;
import thecursed.cards.skill.*;
import thecursed.characters.TheCursedCharacter;
import thecursed.enums.AbstractCardEnum;
import thecursed.enums.TheCursedCharEnum;
import thecursed.potions.DregsPotion;
import thecursed.relics.*;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@SpireInitializer
public class TheCursedMod implements EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber,
        EditStringsSubscriber, EditKeywordsSubscriber, PostInitializeSubscriber {
    private static final Color CUSTOM_COLOR = CardHelper.getColor(94.0F, 55.0F, 220.0F);

    private static final String ATTACK_CARD = "512/attack_thecursed.png";
    private static final String SKILL_CARD = "512/skill_thecursed.png";
    private static final String POWER_CARD = "512/power_thecursed.png";
    private static final String ENERGY_ORB = "512/card_thecursed_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/attack_thecursed.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/skill_thecursed.png";
    private static final String POWER_CARD_PORTRAIT = "1024/power_thecursed.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_small_orb.png";

    public static TheCursedCharacter theCursedCharacter;

    private Map<String, Keyword> keywords;

    public TheCursedMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.THE_CURSED_PURPLE,
                CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD),
                getResourcePath(POWER_CARD), getResourcePath(ENERGY_ORB),
                getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT),
                getResourcePath(POWER_CARD_PORTRAIT), getResourcePath(ENERGY_ORB_PORTRAIT), getResourcePath(CARD_ENERGY_ORB));
    }

    public static final String getResourcePath(String resource) {
        return "TheCursedMod/img/" + resource;
    }

    public static void initialize() {
        new TheCursedMod();
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(getResourcePath("badge.png"));
        BaseMod.registerModBadge(
                badgeTexture, "The Cursed", "jhp109",
                "Adds a new character to the game - The Cursed", null);

        BaseMod.addPotion(
                DregsPotion.class, Color.BLACK, Color.DARK_GRAY, Color.GRAY, DregsPotion.POTION_ID,
                TheCursedCharEnum.THE_CURSED);
    }

    @Override
    public void receiveEditCharacters() {
        theCursedCharacter = new TheCursedCharacter("The Cursed");
        BaseMod.addCharacter(
                theCursedCharacter, getResourcePath("charSelect/button.png"), getResourcePath("charSelect/portrait.png"),
                TheCursedCharEnum.THE_CURSED);
    }

    @Override
    public void receiveEditCards() {
        // Basic (4)
        BaseMod.addCard(new Strike_TheCursed());
        BaseMod.addCard(new Defend_TheCursed());
        BaseMod.addCard(new CleanUpWorkshop());
        BaseMod.addCard(new CursedWand());

        // Attack (22)
        BaseMod.addCard(new AbyssShockwave());
        BaseMod.addCard(new CursedBlade());
        BaseMod.addCard(new CursedBoomerang());
        BaseMod.addCard(new CursedMace());
        BaseMod.addCard(new CursedRelics());
        BaseMod.addCard(new CursedShiv());
        BaseMod.addCard(new CursedStaff());
        BaseMod.addCard(new DemonSword());
        BaseMod.addCard(new FuryStrike());
        BaseMod.addCard(new ManaBullet());
        BaseMod.addCard(new Mangle());
        BaseMod.addCard(new RavingStaff());
        BaseMod.addCard(new ShockAndAwe());
        BaseMod.addCard(new SoulCrush());
        BaseMod.addCard(new SoulReap());
        BaseMod.addCard(new SpitefulStrike());
        BaseMod.addCard(new Stigma());
        BaseMod.addCard(new StunningStrike());
        BaseMod.addCard(new SurpriseAttack());
        BaseMod.addCard(new TacticalStrike());
        BaseMod.addCard(new VitalStrike());
        BaseMod.addCard(new Wield());

        // Power (11)
        BaseMod.addCard(new CollectTribute());
        BaseMod.addCard(new DemonicPact());
        BaseMod.addCard(new EmpowerCircles());
        BaseMod.addCard(new HellfireBreathing());
        BaseMod.addCard(new HellsGateOpen());
        BaseMod.addCard(new Hemoplague());
        BaseMod.addCard(new LuckyCharm());
        BaseMod.addCard(new ManaCirculation());
        BaseMod.addCard(new OutForBlood());
        BaseMod.addCard(new SpitefulPlan());
        BaseMod.addCard(new VoidForm());

        // Skill (38)
        BaseMod.addCard(new AntiAging());
        BaseMod.addCard(new AshesToAshes());
        BaseMod.addCard(new BlessingInDisguise());
        BaseMod.addCard(new BloodBarrier());
        BaseMod.addCard(new CircleOfAbyss());
        BaseMod.addCard(new CircleOfAmplification());
        BaseMod.addCard(new CircleOfBlood());
        BaseMod.addCard(new CircleOfCorruption());
        BaseMod.addCard(new CircleOfFlame());
        BaseMod.addCard(new CircleOfFocus());
        BaseMod.addCard(new CircleOfWindfury());
        BaseMod.addCard(new CursedAmulet());
        BaseMod.addCard(new CursedArmor());
        BaseMod.addCard(new CursedSpellbook());
        BaseMod.addCard(new DarkBarrier());
        BaseMod.addCard(new DoubleShield());
        BaseMod.addCard(new Evocation());
        BaseMod.addCard(new FleshFromBone());
        BaseMod.addCard(new ForbiddenAlchemy());
        BaseMod.addCard(new Grudge());
        BaseMod.addCard(new NeowsMight());
        BaseMod.addCard(new Neurasthenia());
        BaseMod.addCard(new Obscuration());
        BaseMod.addCard(new ParallelWorld());
        BaseMod.addCard(new PrepareRitual());
        BaseMod.addCard(new RapidMovement());
        BaseMod.addCard(new ReleasedKnowledge());
        BaseMod.addCard(new ReleasedStrength());
        BaseMod.addCard(new SaltUponWound());
        BaseMod.addCard(new SmokeScreen());
        BaseMod.addCard(new SpitefulPray());
        BaseMod.addCard(new SpreadPlague());
        BaseMod.addCard(new Stretching());
        BaseMod.addCard(new StingEye());
        BaseMod.addCard(new Torture());
        BaseMod.addCard(new TroublousMinions());
        BaseMod.addCard(new UltimatePain());
        BaseMod.addCard(new WearCloak());

        // Curse
        BaseMod.addCard(new Dregs());
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new BlackMagic101(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new BlackMagicAdvanced(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new BloodyHarpoon(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new CrystalBall(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new DemonicMark(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new FourLeafCloverCharm(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new MagicCandle(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new PinkPellets(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new OminousMark(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new SoulVessel(), AbstractCardEnum.THE_CURSED_PURPLE);
        BaseMod.addRelicToCustomPool(new Tack(), AbstractCardEnum.THE_CURSED_PURPLE);
    }

    private static String getLanguageString() {
        // Note to translators - add your language here (by alphabetical order).
        switch (Settings.language) {
            case KOR:
                return "kor";
            default:
                return "eng";
        }
    }

    @Override
    public void receiveEditStrings() {
        String language = getLanguageString();
        String l10nPath = "TheCursedMod/localization/";
        BaseMod.loadCustomStringsFile(RelicStrings.class, l10nPath + language + "/RelicStrings.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, l10nPath + language + "/CardStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, l10nPath + language + "/PowerStrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, l10nPath + language + "/UIStrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, l10nPath + language + "/CharacterStrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, l10nPath + language + "/PotionStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        final Gson gson = new Gson();
        String language = getLanguageString();

        String keywordStrings =
                Gdx.files.internal("TheCursedMod/localization/" + language + "/KeywordStrings.json")
                        .readString(String.valueOf(StandardCharsets.UTF_8));
        Type typeToken = new TypeToken<Map<String, Keyword>>() {
        }.getType();

        keywords = gson.fromJson(keywordStrings, typeToken);
        keywords.forEach((k, v) -> {
            BaseMod.addKeyword(v.PROPER_NAME, v.NAMES, v.DESCRIPTION);
        });
    }
}