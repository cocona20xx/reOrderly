package io.github.cocona20xx.reorderly.config;

import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.config.api.values.ValueKey;
import org.quiltmc.config.api.values.ValueList;
import org.quiltmc.loader.api.config.QuiltConfig;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ReOrderlyConfigManager {

    private static final Accessor ACCESSOR = new Accessor();
    public static final ReOrderlyConfig CONFIG = QuiltConfig.create("reorderly", "config", ReOrderlyConfig.class);

    public static final TrackedValue<Boolean> DRAW_ENABLED = (TrackedValue<Boolean>) CONFIG.getValue(List.of("drawEnabled"));
    public static final TrackedValue<Integer> MAX_DISTANCE = (TrackedValue<Integer>) CONFIG.getValue(List.of("maxDistance"));
    public static final TrackedValue<Boolean> RENDER_IN_F1 = (TrackedValue<Boolean>) CONFIG.getValue(List.of("renderEnabledInF1"));
    public static final TrackedValue<Float> HP_BAR_SCALE = (TrackedValue<Float>) CONFIG.getValue(List.of("healthBarScale"));
    public static final TrackedValue<Double> HEIGHT_ABOVE = (TrackedValue<Double>) CONFIG.getValue(List.of("heightAbove"));
    public static final TrackedValue<Boolean> DRAW_BG = (TrackedValue<Boolean>) CONFIG.getValue(List.of("drawBackground"));
    public static final TrackedValue<Integer> BG_PADDING = (TrackedValue<Integer>) CONFIG.getValue(List.of("backgroundPadding"));
    public static final TrackedValue<Integer> BG_HEIGHT = (TrackedValue<Integer>) CONFIG.getValue(List.of("backgroundHeight"));
    public static final TrackedValue<Integer> BAR_HEIGHT = (TrackedValue<Integer>) CONFIG.getValue(List.of("barHeight"));
    public static final TrackedValue<Integer> PLATE_SIZE = (TrackedValue<Integer>) CONFIG.getValue(List.of("plateSize"));
    public static final TrackedValue<Integer> PLATE_SIZE_BOSS = (TrackedValue<Integer>) CONFIG.getValue(List.of("plateSizeBoss"));
    public static final TrackedValue<Boolean> SHOW_ATTRIBUTES = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showAttributes"));
    public static final TrackedValue<Boolean> SHOW_ARMOR = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showArmor"));
    public static final TrackedValue<Boolean> SHOW_GROUP_ARMOR = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showGroupArmor"));
    public static final TrackedValue<Boolean> COLOR_BY_TYPE = (TrackedValue<Boolean>) CONFIG.getValue(List.of("doColorByType"));
    public static final TrackedValue<Integer> HP_TEXT_HEIGHT = (TrackedValue<Integer>) CONFIG.getValue(List.of("hpTextHeight"));
    public static final TrackedValue<Boolean> SHOW_MAX_HP = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showMaxHp"));
    public static final TrackedValue<Boolean> SHOW_CURRENT_HP = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showCurrentHp"));
    public static final TrackedValue<Boolean> SHOW_PERCENT_HP = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showPercentage"));
    public static final TrackedValue<Boolean> SHOW_ON_PLAYERS = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showOnPlayers"));
    public static final TrackedValue<Boolean> SHOW_ON_BOSSES = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showOnBosses"));
    public static final TrackedValue<Boolean> SHOW_FOCUSED_ONLY = (TrackedValue<Boolean>) CONFIG.getValue(List.of("showFocusedOnly"));
    public static final TrackedValue<Boolean> DEBUG_MODE = (TrackedValue<Boolean>) CONFIG.getValue(List.of("debugMode"));
    public static final TrackedValue<ValueList<String>> BLACKLIST = (TrackedValue<ValueList<String>>) CONFIG.getValue(List.of("blacklist"));
    public static final TrackedValue<ValueList<String>> BOSS_LIST = (TrackedValue<ValueList<String>>) CONFIG.getValue(List.of("bossList"));

    public static Accessor getAccessor(){
        return ACCESSOR;
    }
    public static final class Accessor implements ReOrderlyConfigAccessor {

        @Override
        public boolean canDraw() {
            return DRAW_ENABLED.getRealValue();
        }

        @Override
        public int getMaxDistance() {
            return MAX_DISTANCE.getRealValue();
        }

        @Override
        public boolean canRenderInF1() {
            return RENDER_IN_F1.getRealValue();
        }

        @Override
        public float getHealthBarScale() {
            return HP_BAR_SCALE.getRealValue();
        }

        @Override
        public double getHeightAbove() {
            return HEIGHT_ABOVE.getRealValue();
        }

        @Override
        public boolean drawsBackground() {
            return DRAW_BG.getRealValue();
        }

        @Override
        public int getBackgroundPadding() {
            return BG_PADDING.getRealValue();
        }

        @Override
        public int getBackgroundHeight() {
            return BG_HEIGHT.getRealValue();
        }

        @Override
        public int getBarHeight() {
            return BAR_HEIGHT.getRealValue();
        }

        @Override
        public int getPlateSize() {
            return PLATE_SIZE.getRealValue();
        }

        @Override
        public int getPlateSizeBoss() {
            return PLATE_SIZE_BOSS.getRealValue();
        }

        @Override
        public boolean showAttributes() {
            return SHOW_ATTRIBUTES.getRealValue();
        }

        @Override
        public boolean showArmor() {
            return SHOW_ARMOR.getRealValue();
        }

        @Override
        public boolean showGroupArmor() {
            return SHOW_GROUP_ARMOR.getRealValue();
        }

        @Override
        public boolean colorByType() {
            return COLOR_BY_TYPE.getRealValue();
        }

        @Override
        public int getHpTextHeight() {
            return HP_TEXT_HEIGHT.getRealValue();
        }

        @Override
        public boolean showMaxHP() {
            return SHOW_MAX_HP.getRealValue();
        }

        @Override
        public boolean showCurrentHP() {
            return SHOW_CURRENT_HP.getRealValue();
        }

        @Override
        public boolean showPercentage() {
            return SHOW_PERCENT_HP.getRealValue();
        }

        @Override
        public boolean showOnPlayers() {
            return SHOW_ON_PLAYERS.getRealValue();
        }

        @Override
        public boolean showOnBosses() {
            return SHOW_ON_BOSSES.getRealValue();
        }

        @Override
        public boolean showFocusedOnly() {
            return SHOW_FOCUSED_ONLY.getRealValue();
        }

        @Override
        public boolean isDebugInfoEnabled() {
            return DEBUG_MODE.getRealValue();
        }

        @Override
        public List<String> getBlacklist() {
            return BLACKLIST.getRealValue();
        }

        @Override
        public List<String> getBosses() {
            return BOSS_LIST.getRealValue();
        }
    }
}
