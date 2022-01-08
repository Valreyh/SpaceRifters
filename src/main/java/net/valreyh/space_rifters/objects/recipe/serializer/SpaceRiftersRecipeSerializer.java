package net.valreyh.space_rifters.objects.recipe.serializer;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.valreyh.space_rifters.objects.recipe.SpaceRiftersRecipe;

import java.util.Iterator;
import java.util.Map;

public class SpaceRiftersRecipeSerializer implements RecipeSerializer<SpaceRiftersRecipe> {
    // Define ExampleRecipeSerializer as a singleton by making its constructor private and exposing an instance.
    private SpaceRiftersRecipeSerializer() {
    }

    public static final SpaceRiftersRecipeSerializer INSTANCE = new SpaceRiftersRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier("space_rifters:recipe");

    @Override
    // Turns json into Recipe
    public SpaceRiftersRecipe read(Identifier id, JsonObject json) {
        Map<String, Ingredient> map = SpaceRiftersRecipe.readSymbols(JsonHelper.getObject(json, "key"));
        String[] strings = SpaceRiftersRecipe.removePadding(SpaceRiftersRecipe.getPattern(JsonHelper.getArray(json, "pattern")));
        int i = strings[0].length();
        int j = strings.length;
        DefaultedList<Ingredient> defaultedList = SpaceRiftersRecipe.createPatternMatrix(strings, map, i, j);
        ItemStack itemStack = SpaceRiftersRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
        return new SpaceRiftersRecipe(i,j,defaultedList,itemStack,id);
    }

    @Override
    public void write(PacketByteBuf packetData, SpaceRiftersRecipe recipe) {
        packetData.writeVarInt(SpaceRiftersRecipe.width);
        packetData.writeVarInt(SpaceRiftersRecipe.height);
        Iterator<Ingredient> var3 = SpaceRiftersRecipe.input.iterator();

        while(var3.hasNext()) {
            Ingredient ingredient = var3.next();
            ingredient.write(packetData);
        }

        packetData.writeItemStack(SpaceRiftersRecipe.outputStack);
    }

    @Override
    public SpaceRiftersRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        int i = packetData.readVarInt();
        int j = packetData.readVarInt();
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i * j, Ingredient.EMPTY);
        ItemStack output = packetData.readItemStack();

        for(int k = 0; k < defaultedList.size(); ++k) {
            defaultedList.set(k, Ingredient.fromPacket(packetData));
        }

        ItemStack k = packetData.readItemStack();
        return new SpaceRiftersRecipe(i,j,defaultedList,output,recipeId);
    }
}
