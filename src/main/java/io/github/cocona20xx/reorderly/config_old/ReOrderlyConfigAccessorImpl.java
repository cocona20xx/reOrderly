package io.github.cocona20xx.reorderly.config_old;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.annotations.SerializedName;
import io.github.cocona20xx.reorderly.ReOrderlyMod;
import io.github.cocona20xx.reorderly.config.ReOrderlyConfigAccessor;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("FieldCanBeLocal")
public class ReOrderlyConfigAccessorImpl implements ReOrderlyConfigAccessor {

    private static final transient String[] blacklistDefaults = new String[]{"minecraft:armor_stand", "minecraft:bee", "minecraft:cod", "minecraft:pufferfish", "minecraft:salmon", "minecraft:shulker", "minecraft:tropical_fish", "illuminations:firefly"};
    private static final transient String[] bossDefaults = new String[]{"minecraft:ender_dragon", "minecraft:wither"};
    /**
     * whether the mod is enabled
     */
    private boolean draw = true;
    private int maxDistance = 24;
    /**
     * whether to render health bars when the HUD is disabled by pressing F1
     */
    private boolean renderInF1 = false;
    /**
     * scale modifier for the health bar
     */
    private float healthBarScale = 1.0F;
    private double heightAbove = 0.6;
    /**
     * whether to draw the background
     */
    private boolean drawBackground = true;
    private int backgroundPadding = 2;
    private int backgroundHeight = 6;
    private int barHeight = 4;
    private int plateSize = 25;
    private int plateSizeBoss = 50;
    private boolean showAttributes = true;
    private boolean showArmor = true;
    private boolean groupArmor = true;
    private boolean colorByType = false;
    /**
     * (negative) offset for the health bar text relative to the entity name
     */
    private int hpTextHeight = 14;
    @SerializedName("show_max_hp") //need this to not have weird names in the config
    private boolean showMaxHP = true;
    @SerializedName("show_current_hp") //need this to not have weird names in the config
    private boolean showCurrentHP = true;
    private boolean showPercentage = true;
    private boolean showOnPlayers = true;
    private boolean showOnBosses = true;
    private boolean showOnlyFocused = false;
    private boolean enableDebugInfo = false;
    private Set<String> blacklist = Sets.newHashSet(blacklistDefaults);
    private Set<String> bosses = Sets.newHashSet(bossDefaults);

    static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableText(String.format("config.%s.title", ReOrderlyMod.MODID)));
        ReOrderlyConfigAccessorImpl config = OrderlyConfigManager.getConfig();
        builder.getOrCreateCategory(new LiteralText("general"))
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.draw", ReOrderlyMod.MODID)), config.canDraw()).setDefaultValue(true).setSaveConsumer(b -> config.draw = b).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.maxDistance", ReOrderlyMod.MODID)), config.getMaxDistance()).setDefaultValue(24).setSaveConsumer(i -> config.maxDistance = i).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.renderInF1", ReOrderlyMod.MODID)), config.canRenderInF1()).setDefaultValue(false).setSaveConsumer(b -> config.renderInF1 = b).build())
                .addEntry(ConfigEntryBuilder.create().startFloatField(new TranslatableText(String.format("config.%s.healthBarScale", ReOrderlyMod.MODID)), config.getHealthBarScale()).setDefaultValue(1.0F).setSaveConsumer(d -> config.healthBarScale = d).build())
                .addEntry(ConfigEntryBuilder.create().startDoubleField(new TranslatableText(String.format("config.%s.heightAbove", ReOrderlyMod.MODID)), config.getHeightAbove()).setDefaultValue(0.6D).setSaveConsumer(d -> config.heightAbove = d).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.drawBackground", ReOrderlyMod.MODID)), config.drawsBackground()).setDefaultValue(true).setSaveConsumer(b -> config.drawBackground = b).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.backgroundPadding", ReOrderlyMod.MODID)), config.getBackgroundPadding()).setDefaultValue(2).setSaveConsumer(i -> config.backgroundPadding = i).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.backgroundHeight", ReOrderlyMod.MODID)), config.getBackgroundHeight()).setDefaultValue(6).setSaveConsumer(i -> config.backgroundHeight = i).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.barHeight", ReOrderlyMod.MODID)), config.getBarHeight()).setDefaultValue(4).setSaveConsumer(i -> config.barHeight = i).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.plateSize", ReOrderlyMod.MODID)), config.getPlateSize()).setDefaultValue(25).setSaveConsumer(i -> config.plateSize = i).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.plateSizeBoss", ReOrderlyMod.MODID)), config.getPlateSizeBoss()).setDefaultValue(50).setSaveConsumer(i -> config.plateSizeBoss = i).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showAttributes", ReOrderlyMod.MODID)), config.showAttributes()).setDefaultValue(true).setSaveConsumer(b -> config.showAttributes = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showArmor", ReOrderlyMod.MODID)), config.showArmor()).setDefaultValue(true).setSaveConsumer(b -> config.showArmor = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.groupArmor", ReOrderlyMod.MODID)), config.showGroupArmor()).setDefaultValue(true).setSaveConsumer(b -> config.groupArmor = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.colorByType", ReOrderlyMod.MODID)), config.colorByType()).setDefaultValue(false).setSaveConsumer(b -> config.colorByType = b).build())
                .addEntry(ConfigEntryBuilder.create().startIntField(new TranslatableText(String.format("config.%s.hpTextHeight", ReOrderlyMod.MODID)), config.getHpTextHeight()).setDefaultValue(14).setSaveConsumer(i -> config.hpTextHeight = i).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showMaxHP", ReOrderlyMod.MODID)), config.showMaxHP()).setDefaultValue(true).setSaveConsumer(b -> config.showMaxHP = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showCurrentHP", ReOrderlyMod.MODID)), config.showCurrentHP()).setDefaultValue(true).setSaveConsumer(b -> config.showCurrentHP = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showPercentage", ReOrderlyMod.MODID)), config.showPercentage()).setDefaultValue(true).setSaveConsumer(b -> config.showPercentage = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showOnPlayers", ReOrderlyMod.MODID)), config.showOnPlayers()).setDefaultValue(true).setSaveConsumer(b -> config.showOnPlayers = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showOnBosses", ReOrderlyMod.MODID)), config.showOnBosses()).setDefaultValue(true).setSaveConsumer(b -> config.showOnBosses = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.showOnlyFocused", ReOrderlyMod.MODID)), config.showingOnlyFocused()).setDefaultValue(false).setSaveConsumer(b -> config.showOnlyFocused = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle(new TranslatableText(String.format("config.%s.enableDebugInfo", ReOrderlyMod.MODID)), config.isDebugInfoEnabled()).setDefaultValue(false).setSaveConsumer(b -> config.enableDebugInfo = b).build())
                .addEntry(ConfigEntryBuilder.create().startStrList(new TranslatableText(String.format("config.%s.blacklist", ReOrderlyMod.MODID)), Lists.newArrayList(config.getBlacklist())).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? new TranslatableText("config.orderly.error.invalid_identifier") : null)).setDefaultValue(Lists.newArrayList(blacklistDefaults)).setExpended(true).setSaveConsumer(strings -> config.blacklist = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build())
                .addEntry(ConfigEntryBuilder.create().startStrList(new TranslatableText(String.format("config.%s.bosses", ReOrderlyMod.MODID)), Lists.newArrayList(config.getBosses())).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? new TranslatableText("config.orderly.error.invalid_identifier") : null)).setDefaultValue(Lists.newArrayList(bossDefaults)).setExpended(true).setSaveConsumer(strings -> config.bosses = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build());
        builder.setSavingRunnable(OrderlyConfigManager::save);
        return builder.build();
    }

    @Override
    public boolean canDraw() {
        return draw;
    }

    @Override
    public int getMaxDistance() {
        return maxDistance;
    }

    @Override
    public boolean canRenderInF1() {
        return renderInF1;
    }

    @Override
    public float getHealthBarScale() {
        return healthBarScale;
    }

    @Override
    public double getHeightAbove() {
        return heightAbove;
    }

    @Override
    public boolean drawsBackground() {
        return drawBackground;
    }

    @Override
    public int getBackgroundPadding() {
        return backgroundPadding;
    }

    @Override
    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    @Override
    public int getBarHeight() {
        return barHeight;
    }

    @Override
    public int getPlateSize() {
        return plateSize;
    }

    @Override
    public int getPlateSizeBoss() {
        return plateSizeBoss;
    }

    @Override
    public boolean showAttributes() {
        return showAttributes;
    }

    @Override
    public boolean showArmor() {
        return showArmor;
    }

    @Override
    public boolean showGroupArmor() {
        return groupArmor;
    }

    @Override
    public boolean colorByType() {
        return colorByType;
    }

    @Override
    public int getHpTextHeight() {
        return hpTextHeight;
    }

    @Override
    public boolean showMaxHP() {
        return showMaxHP;
    }

    @Override
    public boolean showCurrentHP() {
        return showCurrentHP;
    }

    @Override
    public boolean showPercentage() {
        return showPercentage;
    }

    @Override
    public boolean showOnPlayers() {
        return showOnPlayers;
    }

    @Override
    public boolean showOnBosses() {
        return showOnBosses;
    }

    @Override
    public boolean showingOnlyFocused() {
        return showOnlyFocused;
    }

    @Override
    public boolean isDebugInfoEnabled() {
        return enableDebugInfo;
    }

    @Override
    public List<String> getBlacklist() {
        return blacklist;
    }

    @Override
    public List<String> getBosses() {
        return bosses;
    }

    public void toggleDraw() {
        draw = !draw;
    }
//            v_maxDistance = builder.define("Max Distance", maxDistance);
//            v_renderInF1 = builder.define("Render with Interface Disabled (F1)", renderInF1);
//            v_heightAbove = builder.define("Height Above Mob", heightAbove);
//            v_drawBackground = builder.define("Draw Background", drawBackground);
//            v_backgroundPadding = builder.define("Background Padding", backgroundPadding);
//            v_backgroundHeight = builder.define("Background Height", backgroundHeight);
//            v_barHeight = builder.define("Health Bar Height", barHeight);
//            v_plateSize = builder.define("Plate Size", plateSize);
//            v_plateSizeBoss = builder.define("Plate Size (Boss)", plateSizeBoss);
//            v_showAttributes = builder.define("Show Attributes", showAttributes);
//            v_showArmor = builder.define("Show Armor", showArmor);
//            v_groupArmor = builder.define("Group Armor (condense 5 iron icons into 1 diamond icon)", groupArmor);
//            v_colorByType = builder.define("Color Health Bar by Type (instead of health percentage)", colorByType);
//            v_hpTextHeight = builder.define("HP Text Height", hpTextHeight);
//            v_showMaxHP = builder.define("Show Max HP", showMaxHP);
//            v_showCurrentHP = builder.define("Show Current HP", showCurrentHP);
//            v_showPercentage = builder.define("Show HP Percentage", showPercentage);
//            v_showOnPlayers = builder.define("Display on Players", showOnPlayers);
//            v_showOnBosses = builder.define("Display on Bosses", showOnBosses);
//            v_showOnlyFocused = builder.define("Only show the health bar for the entity looked at", showOnlyFocused);
//            v_enableDebugInfo = builder.define("Show Debug Info with F3", enableDebugInfo);
//            v_blacklist = builder.comment("Blacklist uses entity IDs, not their display names. Use F3 to see them in the ReOrderlyMod bar.")
}
