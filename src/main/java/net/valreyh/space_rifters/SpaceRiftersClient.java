package net.valreyh.space_rifters;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.valreyh.space_rifters.handlers.CrusherScreen;
import net.valreyh.space_rifters.handlers.LumosStationScreen;

@Environment(EnvType.CLIENT)
public class SpaceRiftersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(SpaceRifters.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        ScreenRegistry.register(SpaceRifters.LUMOS_STATION_SCREEN_HANDLER, LumosStationScreen::new);
    }
}
