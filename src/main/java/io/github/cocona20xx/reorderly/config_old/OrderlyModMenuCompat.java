package io.github.cocona20xx.reorderly.config_old;

import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

@SuppressWarnings("unused")
public class OrderlyModMenuCompat implements ModMenuApi {
    @Override
    public Function<Screen, ? extends Screen> getModConfigScreenFactory() {
        return ReOrderlyConfigAccessorImpl::createConfigScreen;
    }
}
