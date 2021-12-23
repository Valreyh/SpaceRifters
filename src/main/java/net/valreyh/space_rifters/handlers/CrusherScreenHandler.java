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
        ItemStack hammerStack = this.input.getStack(1);
        if(hammerStack.damage(2, this.player.getRandom(), null)) hammerStack.setCount(0);
    }

    @Override
    protected boolean canUse(BlockState state) {
        return state.isOf(SpaceRifters.CRUSHER);
    }

    @Override
    public void updateResult() {
        ItemStack ingredientStack = this.input.getStack(0);
        ItemStack hammerStack = this.input.getStack(1);
        if (!ingredientStack.isEmpty() && !hammerStack.isEmpty())
        {
            boolean bl1 = ingredientStack.isOf(SpaceRifters.LUMOS_FRAGMENT);
            boolean bl2 = hammerStack.isOf(SpaceRifters.HAMMER);
            if (bl1 && bl2)
            {
                this.output.setStack(0, new ItemStack(SpaceRifters.LUMOS_POWDER,2));
            }
        }
    }
}
