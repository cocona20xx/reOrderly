package io.github.cocona20xx.reorderly.config;

import java.util.List;

public interface ReOrderlyConfigAccessor {
    boolean canDraw();

    int getMaxDistance();

    boolean canRenderInF1();

    float getHealthBarScale();

    double getHeightAbove();

    boolean drawsBackground();

    int getBackgroundPadding();

    int getBackgroundHeight();

    int getBarHeight();

    int getPlateSize();

    int getPlateSizeBoss();

    boolean showAttributes();

    boolean showArmor();

    boolean showGroupArmor();

    boolean colorByType();

    int getHpTextHeight();

    boolean showMaxHP();

    boolean showCurrentHP();

    boolean showPercentage();

    boolean showOnPlayers();

    boolean showOnBosses();

    boolean showFocusedOnly();

    boolean isDebugInfoEnabled();

    List<String> getBlacklist();

    List<String> getBosses();
}
