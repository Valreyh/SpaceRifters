package net.valreyh.space_rifters;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.client.gui.screen.ingame.BlastFurnaceScreen;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.BlastFurnaceRecipeBookScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.datafixer.fix.FurnaceRecipesFix;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.screen.BlastFurnaceScreenHandler;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.valreyh.space_rifters.blocks.CrusherBlock;
import net.valreyh.space_rifters.blocks.LumosStationBlock;
import net.valreyh.space_rifters.handlers.CrusherScreenHandler;
import net.valreyh.space_rifters.handlers.LumosStationScreenHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpaceRifters implements ModInitializer {
	public static final String MOD_ID = "space_rifters";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	// BLOCKS //
	public static final Block LUMOS_ORE = new Block(FabricBlockSettings.of(Material.AMETHYST).strength(4.0f));
	public static final Block METEOR_ORE = new Block(FabricBlockSettings.of(Material.METAL).strength(4,3));
	public static final Block CRUSHER = new CrusherBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final Block LUMOS_STATION = new LumosStationBlock(FabricBlockSettings.of(Material.WOOD).strength(1.0f));
	// ITEMS //
	public static final Item HAMMER = new Item(new FabricItemSettings().group(ItemGroup.TOOLS).maxDamage(10));

	public static final Item LUMOS_CRAFTING_CORE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item VOLCANO_ORB = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item LUMOS_FRAGMENT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item LUMOS = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item LUMOS_POWDER = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item RALIOS = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item NARSTONE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item TEAR_OF_LIGHT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item METEOR_FRAGMENT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	// TOOLS //
	// SCREEN HANDLER //
	public static ScreenHandlerType<CrusherScreenHandler> CRUSHER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("space_rifters", "crusher_screen"), CrusherScreenHandler::new);
	public static ScreenHandlerType<LumosStationScreenHandler> LUMOS_STATION_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("space_rifters", "lumos_station_screen"), LumosStationScreenHandler::new);

	@Override
	public void onInitialize() {
		// REGISTER BLOCKS //
		Registry.register(Registry.BLOCK, id("lumos_ore"), LUMOS_ORE);
		Registry.register(Registry.BLOCK, id("crusher"), CRUSHER);
		Registry.register(Registry.BLOCK, id("lumos_station"), LUMOS_STATION);
		Registry.register(Registry.BLOCK, id("meteor_ore"), METEOR_ORE);
		// REGISTER ITEMS //
		Registry.register(Registry.ITEM, id("lumos_fragment"), LUMOS_FRAGMENT);
		Registry.register(Registry.ITEM, id("lumos"), LUMOS);
		Registry.register(Registry.ITEM, id("lumos_powder"), LUMOS_POWDER);
		Registry.register(Registry.ITEM, id("ralios"), RALIOS);
		Registry.register(Registry.ITEM, id("narstone"), NARSTONE);
		Registry.register(Registry.ITEM, id("tear_of_light"), TEAR_OF_LIGHT);
		Registry.register(Registry.ITEM, id("meteor_fragment"), METEOR_FRAGMENT);

		Registry.register(Registry.ITEM, id("lumos_ore"), new BlockItem(LUMOS_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, id("crusher"), new BlockItem(CRUSHER, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, id("lumos_station"), new BlockItem(LUMOS_STATION, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, id("meteor_ore"), new BlockItem(METEOR_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, id("hammer"), HAMMER);

		Registry.register(Registry.ITEM, id("lumos_crafting_core"), LUMOS_CRAFTING_CORE);
		Registry.register(Registry.ITEM, id("volcano_orb"), VOLCANO_ORB);

	}

	public static Identifier id(String n) {
		return new Identifier(MOD_ID, n);
	}
}
