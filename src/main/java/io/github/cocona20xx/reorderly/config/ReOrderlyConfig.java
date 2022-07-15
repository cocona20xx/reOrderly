package io.github.cocona20xx.reorderly.config;

import org.quiltmc.config.api.WrappedConfig;
import org.quiltmc.config.api.annotations.FloatRange;
import org.quiltmc.config.api.annotations.IntegerRange;
import org.quiltmc.config.api.values.ValueList;

public final class ReOrderlyConfig extends WrappedConfig {
    public final boolean drawEnabled = ConfigDefaults.drawEnabled;
    public final int maxDistance = ConfigDefaults.maxDistance;
    public final boolean renderEnabledInF1 = ConfigDefaults.renderEnabledInF1;
    @FloatRange(min = ConfigDefaults.healthBarScaleMin, max = ConfigDefaults.healthBarScaleLimit)
    public final float healthBarScale = ConfigDefaults.healthBarScale;
    public final double heightAbove = ConfigDefaults.heightAbove;
    public final boolean drawBackground = ConfigDefaults.drawBackground;
    @IntegerRange(min = 0, max = Integer.MAX_VALUE)
    public final int backgroundPadding = ConfigDefaults.backgroundPadding;
    @IntegerRange(min = 0, max = Integer.MAX_VALUE)
    public final int backgroundHeight = ConfigDefaults.backgroundHeight;
    @IntegerRange(min = 0, max = Integer.MAX_VALUE)
    public final int barHeight = ConfigDefaults.barHeight;
    @IntegerRange(min = 0, max = Integer.MAX_VALUE)
    public final int plateSize = ConfigDefaults.plateSize;
    @IntegerRange(min = 0, max = Integer.MAX_VALUE)
    public final int plateSizeBoss = ConfigDefaults.plateSizeBoss;
    public final boolean showAttributes = ConfigDefaults.showAttributes;
    public final boolean showArmor = ConfigDefaults.showArmor;
    public final boolean showGroupArmor = ConfigDefaults.showGroupArmor;
    public final boolean doColorByType = ConfigDefaults.doColorByType;
    @IntegerRange(min = 0, max = Integer.MAX_VALUE)
    public final int hpTextHeight = ConfigDefaults.hpTextHeight;
    public final boolean showMaxHp = ConfigDefaults.showMaxHp;
    public final boolean showCurrentHp = ConfigDefaults.showCurrentHp;
    public final boolean showPercentage = ConfigDefaults.showPercentage;
    public final boolean showOnPlayers = ConfigDefaults.showOnPlayers;
    public final boolean showOnBosses = ConfigDefaults.showOnBosses;
    public final boolean showFocusedOnly = ConfigDefaults.showFocusedOnly;
    public final boolean debugMode = ConfigDefaults.debugMode;
    public final ValueList<String> blacklist = ValueList.create("default", ConfigDefaults.blacklistArray);
    public final ValueList<String> bossList = ValueList.create("default", ConfigDefaults.bossListArray);
}
