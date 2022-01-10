package net.valreyh.space_rifters;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.valreyh.space_rifters.handlers.CrusherScreen;
import net.valreyh.space_rifters.handlers.LumosStationScreen;

import static net.valreyh.space_rifters.SpaceRifters.LUMOS_CROP_BLOCK;

@Environment(EnvType.CLIENT)
public class SpaceRiftersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(SpaceRifters.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        ScreenRegistry.register(SpaceRifters.LUMOS_STATION_SCREEN_HANDLER, LumosStationScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(LUMOS_CROP_BLOCK, RenderLayer.getCutout());
    }
}
