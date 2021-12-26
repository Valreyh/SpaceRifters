package net.valreyh.space_rifters.handlers;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.valreyh.space_rifters.SpaceRifters;

@Environment(EnvType.CLIENT)
public class LumosStationScreen extends HandledScreen<LumosStationScreenHandler> implements RecipeBookProvider {
    private static final Identifier TEXTURE = SpaceRifters.id("textures/gui/container/lumosstation.png");
    private final RecipeBookWidget recipeBook = new RecipeBookWidget();

    public LumosStationScreen(LumosStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    protected void init() {
        super.init();
        this.titleX = 29;
    }
    public void handledScreenTick() {
        super.handledScreenTick();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    protected boolean isPointWithinBounds(int x, int y, int width, int height, double pointX, double pointY) {
        return (super.isPointWithinBounds(x, y, width, height, pointX, pointY));
    }


    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        super.onMouseClick(slot, slotId, button, actionType);
    }

    public void refreshRecipeBook() {
    }

    public void removed() {
        super.removed();
    }

    public RecipeBookWidget getRecipeBookWidget() {
        return this.recipeBook;
    }

}
