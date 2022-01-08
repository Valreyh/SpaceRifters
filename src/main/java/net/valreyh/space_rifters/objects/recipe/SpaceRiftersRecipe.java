package net.valreyh.space_rifters.objects.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.valreyh.space_rifters.objects.recipe.serializer.SpaceRiftersRecipeSerializer;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static net.minecraft.recipe.ShapedRecipe.getItem;

public class SpaceRiftersRecipe implements Recipe<CraftingInventory> {
    public static int height;
    public static int width;
    public static DefaultedList<Ingredient> input;
    public static ItemStack outputStack;
    private final Identifier id;

    public SpaceRiftersRecipe(int width, int height, DefaultedList<Ingredient> input, ItemStack outputStack, Identifier id) {
        SpaceRiftersRecipe.input = input;
        SpaceRiftersRecipe.width = width;
        SpaceRiftersRecipe.height = height;
        SpaceRiftersRecipe.outputStack = outputStack;
        this.id = id;
    }

    public DefaultedList<Ingredient> getIngredients() {
        return input;
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        for(int i = 0; i <= craftingInventory.getWidth() - width; ++i) {
            for(int j = 0; j <= craftingInventory.getHeight() - height; ++j) {
                if (this.matchesPattern(craftingInventory, i, j, true)) {
                    return true;
                }

                if (this.matchesPattern(craftingInventory, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean matchesPattern(CraftingInventory inv, int offsetX, int offsetY, boolean flipped) {
        for(int i = 0; i < inv.getWidth(); ++i) {
            for(int j = 0; j < inv.getHeight(); ++j) {
                int k = i - offsetX;
                int l = j - offsetY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < width && l < height) {
                    if (flipped) {
                        ingredient = input.get(width - k - 1 + l * width);
                    } else {
                        ingredient = input.get(k + l * width);
                    }
                }

                if (!ingredient.test(inv.getStack(i + j * inv.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static DefaultedList<Ingredient> createPatternMatrix(String[] pattern, Map<String, Ingredient> symbols, int width, int height) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(symbols.keySet());
        set.remove(" ");
        set.remove("[");
        set.remove("]");

        for(int i = 0; i < pattern.length; ++i) {
            for(int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                Ingredient ingredient = symbols.get(string);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
                }
                set.remove(string);
                defaultedList.set(j + width * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return defaultedList;
        }
    }

    @VisibleForTesting
    public static String[] removePadding(String... pattern) {
        int i = 2147483647;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int m = 0; m < pattern.length; ++m) {
            String string = pattern[m];
            i = Math.min(i, findFirstSymbol(string));
            int n = findLastSymbol(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pattern.length == l) {
            return new String[0];
        } else {
            String[] m = new String[pattern.length - l - k];

            for(int string = 0; string < m.length; ++string) {
                m[string] = pattern[string + k].substring(i, j + 1);
            }

            return m;
        }
    }

    public static String[] getPattern(JsonArray json) {
        String[] strings = new String[json.size()];
        if (strings.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        } else if (strings.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for(int i = 0; i < strings.length; ++i) {
                String string = JsonHelper.asString(json.get(i), "pattern[" + i + "]");
                if (string.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
                }

                if (i > 0 && strings[0].length() != string.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                strings[i] = string;
            }

            return strings;
        }
    }

    private static int findFirstSymbol(String line) {
        int i;
        for(i = 0; i < line.length() && line.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int findLastSymbol(String pattern) {
        int i;
        for(i = pattern.length() - 1; i >= 0 && pattern.charAt(i) == ' '; --i) {
        }

        return i;
    }

    public static ItemStack outputFromJson(JsonObject json) {
        Item item = getItem(json);
        if (json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        } else {
            int i = JsonHelper.getInt(json, "count", 1);
            if (i < 1) {
                throw new JsonSyntaxException("Invalid output count: " + i);
            } else {
                return new ItemStack(item, i);
            }
        }
    }

    public static Map<String, Ingredient> readSymbols(JsonObject json) {
        Map<String, Ingredient> map = Maps.newHashMap();
        Iterator<Map.Entry<String, JsonElement>> var2 = json.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, JsonElement> entry = var2.next();
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    @Override
    public ItemStack craft(CraftingInventory Craft) {
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
