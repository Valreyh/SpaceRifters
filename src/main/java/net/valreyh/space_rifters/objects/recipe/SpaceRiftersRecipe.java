package net.valreyh.space_rifters.objects.recipe;

import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.valreyh.space_rifters.objects.recipe.serializer.SpaceRiftersRecipeSerializer;

public class SpaceRiftersRecipe implements Recipe<Inventory> {
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack outputStack;
    private final Identifier id;

    public SpaceRiftersRecipe(Ingredient inputA, Ingredient inputB, ItemStack outputStack, Identifier id) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.outputStack = outputStack;
        this.id = id;
    }

    public Ingredient getInputA() {
        return inputA;
    }

    public Ingredient getInputB() {
        return inputB;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 2) return false;
        return inputA.test(inventory.getStack(0)) && inputB.test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.getOutput().copy();
    }
    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }
    @Override
    public ItemStack getOutput() {
        return outputStack;
    }
    @Override
    public Identifier getId() {
        return id;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return SpaceRiftersRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<SpaceRiftersRecipe> {
        // Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
        private Type() {}
        public static final Type INSTANCE = new Type();
        // This will be needed in step 4
        public static final String ID = "two_slot_recipe";
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

}
