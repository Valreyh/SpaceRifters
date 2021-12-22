package net.valreyh.space_rifters;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.valreyh.space_rifters.handlers.CrusherScreen;
import net.valreyh.space_rifters.handlers.CrusherScreenHandler;

@Environment(EnvType.CLIENT)
public class SpaceRiftersClient {
    public static void init() {
        ScreenRegistry.register(SpaceRifters.CRUSHER_SCREEN_HANDLER, CrusherScreen::new)
    }
}
