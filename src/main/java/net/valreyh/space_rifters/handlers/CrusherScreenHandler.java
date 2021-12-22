package net.valreyh.space_rifters.handlers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.valreyh.space_rifters.SpaceRifters;
import org.jetbrains.annotations.Nullable;

public class CrusherScreenHandler extends ForgingScreenHandler {
    public CrusherScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return false;
    }

    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {

    }

    @Override
    protected boolean canUse(BlockState state) {
        return false;
    }

    @Override
    public void updateResult() {
        Item Powder = SpaceRifters.LUMOS_POWDER;
        ItemStack itemStack = this.input.getStack(0);
        ItemStack itemStack2 = this.input.getStack(1);
        if (!itemStack.isEmpty() && !itemStack2.isEmpty())
        {
            boolean bl1 = itemStack.isOf(SpaceRifters.LUMOS_FRAGMENT);
            boolean bl2 = itemStack2.isOf(SpaceRifters.HAMMER);
            if (bl1 && bl2)
            {
                this.output.setStack(0, new ItemStack(SpaceRifters.LUMOS_POWDER));
            }
        }

    }
}
