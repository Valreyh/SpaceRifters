package net.valreyh.space_rifters.handlers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.valreyh.space_rifters.SpaceRifters;

public class CrusherScreenHandler extends ForgingScreenHandler {

    public CrusherScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, ScreenHandlerContext.EMPTY);
    }

    public CrusherScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(SpaceRifters.CRUSHER_SCREEN_HANDLER, syncId, playerInventory, context);
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return true;
    }

    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        this.input.removeStack(0,1);
        ItemStack itemStack = this.input.getStack(1);
        itemStack.setDamage(itemStack.getDamage()+2);
        boolean bl = itemStack.getDamage() == 0;
        if (bl)
        {
            this.input.removeStack(0,1);
        }}

    @Override
    protected boolean canUse(BlockState state) {
        return true;
    }

    @Override
    public void updateResult() {
        ItemStack itemStack = this.input.getStack(0);
        ItemStack itemStack2 = this.input.getStack(1);
        if (!itemStack.isEmpty() && !itemStack2.isEmpty())
        {
            boolean bl1 = itemStack.isOf(SpaceRifters.LUMOS_FRAGMENT);
            boolean bl2 = itemStack2.isOf(SpaceRifters.HAMMER);
            if (bl1 && bl2)
            {
                this.output.setStack(0, new ItemStack(SpaceRifters.LUMOS_POWDER,2));
            }
        }

    }
}
