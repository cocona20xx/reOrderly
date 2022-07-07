package io.github.cocona20xx.reorderly.config.codec;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.cocona20xx.reorderly.config.ReOrderlyConfigAccessor;

import java.util.List;
import java.util.Set;

public class ConfigMainCodec implements ReOrderlyConfigAccessor {
    public static final Codec<ConfigMainCodec> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.BOOL.fieldOf("can_draw").orElse(ConfigDefaults.drawEnabled).forGetter(ConfigMainCodec::canDraw),
                    Codec.intRange(1, ConfigDefaults.maxDistance).fieldOf("max_distance").orElse(ConfigDefaults.maxDistance).forGetter(ConfigMainCodec::getMaxDistance),
                    Codec.BOOL.fieldOf("render_in_f1").orElse(ConfigDefaults.renderEnabledInF1).forGetter(ConfigMainCodec::canRenderInF1),
                    Codec.floatRange(ConfigDefaults.heathBarScaleMin, ConfigDefaults.heathBarScaleLimit).fieldOf("health_bar_scale").orElse(ConfigDefaults.healthBarScale).forGetter(ConfigMainCodec::getHealthBarScale),
                    Codec.DOUBLE.fieldOf("height_above").orElse(ConfigDefaults.heightAbove).forGetter(ConfigMainCodec::getHeightAbove),
                    Codec.BOOL.fieldOf("draw_background").orElse(ConfigDefaults.drawBackground).forGetter(ConfigMainCodec::drawsBackground),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("background_padding").orElse(ConfigDefaults.backgroundPadding).forGetter(ConfigMainCodec::getBackgroundPadding),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("background_height").orElse(ConfigDefaults.backgroundHeight).forGetter(ConfigMainCodec::getBackgroundHeight),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("bar_height").orElse(ConfigDefaults.barHeight).forGetter(ConfigMainCodec::getBarHeight),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("plate_size").orElse(ConfigDefaults.plateSize).forGetter(ConfigMainCodec::getPlateSize),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("plate_size_boss").orElse(ConfigDefaults.plateSizeBoss).forGetter(ConfigMainCodec::getPlateSizeBoss),
                    Codec.BOOL.fieldOf("show_attributes").orElse(ConfigDefaults.showAttributes).forGetter(ConfigMainCodec::showAttributes),
                    Codec.BOOL.fieldOf("show_armor").orElse(ConfigDefaults.showArmor).forGetter(ConfigMainCodec::showArmor),
                    Codec.BOOL.fieldOf("show_group_armor").orElse(ConfigDefaults.showGroupArmor).forGetter(ConfigMainCodec::showGroupArmor),
                    Codec.BOOL.fieldOf("color_by_type").orElse(ConfigDefaults.doColorByType).forGetter(ConfigMainCodec::colorByType),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("hp_text_height").orElse(ConfigDefaults.hpTextHeight).forGetter(ConfigMainCodec::getHpTextHeight),
                    Codec.BOOL.fieldOf("show_max_hp").orElse(ConfigDefaults.showMaxHp).forGetter(ConfigMainCodec::showMaxHP),
                    Codec.BOOL.fieldOf("show_current_hp").orElse(ConfigDefaults.showCurrentHp).forGetter(ConfigMainCodec::showCurrentHP),
                    Codec.BOOL.fieldOf("show_percentage").orElse(ConfigDefaults.showPercentage).forGetter(ConfigMainCodec::showPercentage),
                    Codec.BOOL.fieldOf("show_on_players").orElse(ConfigDefaults.showOnPlayers).forGetter(ConfigMainCodec::showOnPlayers),
                    Codec.BOOL.fieldOf("show_on_bosses").orElse(ConfigDefaults.showOnBosses).forGetter(ConfigMainCodec::showOnBosses),
                    Codec.BOOL.fieldOf("show_focused_only").orElse(ConfigDefaults.showFocusedOnly).forGetter(ConfigMainCodec::showingOnlyFocused),
                    Codec.BOOL.fieldOf("debug_mode").orElse(ConfigDefaults.debugMode).forGetter(ConfigMainCodec::isDebugInfoEnabled),
                    Codec.STRING.listOf().fieldOf("blacklist").orElse(Lists.newArrayList(ConfigDefaults.blacklist)).forGetter(ConfigMainCodec::getBlacklist),
                    Codec.STRING.listOf().fieldOf("bosses").orElse(Lists.newArrayList(ConfigDefaults.boss)).forGetter(ConfigMainCodec::getBosses)
            ).apply(instance, ConfigMainCodec::new)
    );

    private boolean drawEnabled = ConfigDefaults.drawEnabled;
    private int maxDistance = ConfigDefaults.maxDistance;
    private boolean renderEnabledInF1 = ConfigDefaults.renderEnabledInF1;
    private float healthBarScale = ConfigDefaults.healthBarScale;
    private double heightAbove = ConfigDefaults.heightAbove;
    private boolean drawBackground = ConfigDefaults.drawBackground;
    private int backgroundPadding = ConfigDefaults.backgroundPadding;
    private int backgroundHeight = ConfigDefaults.backgroundHeight;
    private int barHeight = ConfigDefaults.barHeight;
    private int plateSize = ConfigDefaults.plateSize;
    private int plateSizeBoss = ConfigDefaults.plateSizeBoss;
    private boolean showAttributes = ConfigDefaults.showAttributes;
    private boolean showArmor = ConfigDefaults.showArmor;
    private boolean showGroupArmor = ConfigDefaults.showGroupArmor;
    private boolean doColorByType = ConfigDefaults.doColorByType;
    private int hpTextHeight = ConfigDefaults.hpTextHeight;
    private boolean showMaxHp = ConfigDefaults.showMaxHp;
    private boolean showCurrentHp = ConfigDefaults.showCurrentHp;
    private boolean showPercentage = ConfigDefaults.showPercentage;
    private boolean showOnPlayers = ConfigDefaults.showOnPlayers;
    private boolean showOnBosses = ConfigDefaults.showOnBosses;
    private boolean showFocusedOnly = ConfigDefaults.showFocusedOnly;
    private boolean debugMode = ConfigDefaults.debugMode;
    private Set<String> blacklist = Sets.newHashSet(ConfigDefaults.blacklist);
    private Set<String> bosses = Sets.newHashSet(ConfigDefaults.boss);

    public ConfigMainCodec(boolean drawEnabled,
                           int maxDistance,
                           boolean renderEnabledInF1,
                           float healthBarScale,
                           double heightAbove,
                           boolean drawBackground,
                           int backgroundPadding,
                           int backgroundHeight,
                           int barHeight,
                           int plateSize,
                           int plateSizeBoss,
                           boolean showAttributes,
                           boolean showArmor,
                           boolean showGroupArmor,
                           boolean doColorByType,
                           int hpTextHeight,
                           boolean showMaxHp,
                           boolean showCurrentHp,
                           boolean showPercentage,
                           boolean showOnPlayers,
                           boolean showOnBosses,
                           boolean showFocusedOnly,
                           boolean debugMode,
                           Set<String> blacklist,
                           Set<String> bosses){
                                        this.drawEnabled = drawEnabled;
                                        this.maxDistance = maxDistance;
                                        this.renderEnabledInF1 = renderEnabledInF1;
                                        this.healthBarScale = healthBarScale;
                                        this.heightAbove = heightAbove;
                                        this.drawBackground = drawBackground;
                                        this.backgroundPadding = backgroundPadding;
                                        this.backgroundHeight = backgroundHeight;
                                        this.barHeight = barHeight;
                                        this.plateSize = plateSize;
                                        this.plateSizeBoss = plateSizeBoss;
                                        this.showAttributes = showAttributes;
                                        this.showArmor = showArmor;
                                        this.showGroupArmor = showGroupArmor;
                                        this.doColorByType = doColorByType;
                                        this.hpTextHeight = hpTextHeight;
                                        this.showMaxHp = showMaxHp;
                                        this.showCurrentHp = showCurrentHp;
                                        this.showPercentage = showPercentage;
                                        this.showOnPlayers = showOnPlayers;
                                        this.showOnBosses = showOnBosses;
                                        this.showFocusedOnly = showFocusedOnly;
                                        this.debugMode = debugMode;
                                        this.blacklist = blacklist;
                                        this.bosses = bosses;
    }

    @Override
    public boolean canDraw() {
        return false;
    }

    @Override
    public int getMaxDistance() {
        return 0;
    }

    @Override
    public boolean canRenderInF1() {
        return false;
    }

    @Override
    public float getHealthBarScale() {
        return 0;
    }

    @Override
    public double getHeightAbove() {
        return 0;
    }

    @Override
    public boolean drawsBackground() {
        return false;
    }

    @Override
    public int getBackgroundPadding() {
        return 0;
    }

    @Override
    public int getBackgroundHeight() {
        return 0;
    }

    @Override
    public int getBarHeight() {
        return 0;
    }

    @Override
    public int getPlateSize() {
        return 0;
    }

    @Override
    public int getPlateSizeBoss() {
        return 0;
    }

    @Override
    public boolean showAttributes() {
        return false;
    }

    @Override
    public boolean showArmor() {
        return false;
    }

    @Override
    public boolean showGroupArmor() {
        return false;
    }

    @Override
    public boolean colorByType() {
        return false;
    }

    @Override
    public int getHpTextHeight() {
        return 0;
    }

    @Override
    public boolean showMaxHP() {
        return false;
    }

    @Override
    public boolean showCurrentHP() {
        return false;
    }

    @Override
    public boolean showPercentage() {
        return false;
    }

    @Override
    public boolean showOnPlayers() {
        return false;
    }

    @Override
    public boolean showOnBosses() {
        return false;
    }

    @Override
    public boolean showingOnlyFocused() {
        return false;
    }

    @Override
    public boolean isDebugInfoEnabled() {
        return false;
    }

    @Override
    public List<String> getBlacklist() {
        return null;
    }

    @Override
    public List<String> getBosses() {
        return null;
    }
}
