package net.valreyh.space_rifters.objects.recipe.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.valreyh.space_rifters.objects.recipe.SpaceRiftersJsonFormat;
import net.valreyh.space_rifters.objects.recipe.SpaceRiftersRecipe;

public class SpaceRiftersRecipeSerializer implements RecipeSerializer<SpaceRiftersRecipe> {
    // Define ExampleRecipeSerializer as a singleton by making its constructor private and exposing an instance.
    private SpaceRiftersRecipeSerializer() {
    }

    public static final SpaceRiftersRecipeSerializer INSTANCE = new SpaceRiftersRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier("space_rifters:spacerifters_recipe");

    @Override
    // Turns json into Recipe
    public SpaceRiftersRecipe read(Identifier id, JsonObject json) {

        SpaceRiftersJsonFormat recipeJson = new Gson().fromJson(json, SpaceRiftersJsonFormat.class);

        // Validate all fields are there
        if (recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        // We'll allow to not specify the output, and default it to 1.
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new SpaceRiftersRecipe(inputA, inputB, output, id);
    }

    @Override
    public void write(PacketByteBuf packetData, SpaceRiftersRecipe recipe) {
        recipe.getInputA().write(packetData);
        recipe.getInputB().write(packetData);
        packetData.writeItemStack(recipe.getOutput());
    }

    @Override
    public SpaceRiftersRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        // Make sure the read in the same order you have written!
        Ingredient inputA = Ingredient.fromPacket(packetData);
        Ingredient inputB = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        return new SpaceRiftersRecipe(inputA, inputB, output, recipeId);
    }
}
