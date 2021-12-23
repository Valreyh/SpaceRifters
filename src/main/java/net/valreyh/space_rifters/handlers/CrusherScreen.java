package net.valreyh.space_rifters.handlers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class CrusherScreen extends ForgingScreen<CrusherScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("space_rifters", "textures/gui/container/crushergui.png");
    private TextFieldWidget nameField;
    private final PlayerEntity player;

    public CrusherScreen(CrusherScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.player = playerInventory.player;
        this.titleX = 60;
    }

    public void handledScreenTick() {
        super.handledScreenTick();
        this.nameField.tick();
    }

    protected void setup() {
        assert this.client != null;
        this.client.keyboard.setRepeatEvents(true);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.nameField = new TextFieldWidget(this.textRenderer, i + 62, j + 24, 103, 12, new TranslatableText("Crusher"));
        this.nameField.setFocusUnlocked(false);
        this.nameField.setEditableColor(-1);
        this.nameField.setUneditableColor(-1);
        this.nameField.setDrawsBackground(false);
        this.nameField.setMaxLength(50);
        this.nameField.setText("");
        this.addSelectableChild(this.nameField);
        this.setInitialFocus(this.nameField);
        this.nameField.setEditable(false);
    }

    public void resize(MinecraftClient client, int width, int height) {
        String string = this.nameField.getText();
        this.init(client, width, height);
        this.nameField.setText(string);
    }

    public void removed() {
        super.removed();
        assert this.client != null;
        this.client.keyboard.setRepeatEvents(false);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            assert this.client != null;
            assert this.client.player != null;
            this.client.player.closeHandledScreen();
        }

        return this.nameField.keyPressed(keyCode, scanCode, modifiers) || this.nameField.isActive() || super.keyPressed(keyCode, scanCode, modifiers);
    }
}

