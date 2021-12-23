package net.valreyh.space_rifters;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.valreyh.space_rifters.handlers.CrusherScreenHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpaceRifters implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("space_rifters");

	// BLOCKS //
	public static final Block LUMOS_ORE = new Block(FabricBlockSettings.of(Material.AMETHYST).strength(4.0f));
	public static final Block CRUSHER = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
	// ITEMS //
	public static final Item HAMMER = new Item(new FabricItemSettings().group(ItemGroup.TOOLS));

	public static final Item LUMOS_FRAGMENT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item LUMOS_POWDER = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item RALIOS = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item NARSTONE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item TEAR_OF_LIGHT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	// TOOLS //
	// SCREEN HANDLER //

	@Override
	public void onInitialize() {
		// REGISTER BLOCKS //
		Registry.register(Registry.BLOCK, new Identifier("space_rifters","lumos_ore"), LUMOS_ORE);
		Registry.register(Registry.BLOCK, new Identifier("space_rifters", "crusher"), CRUSHER);
		// REGISTER ITEMS //
		Registry.register(Registry.ITEM, new Identifier("space_rifters","lumos_fragment"), LUMOS_FRAGMENT);
		Registry.register(Registry.ITEM, new Identifier("space_rifters", "lumos_powder"), LUMOS_POWDER);
		Registry.register(Registry.ITEM, new Identifier("space_rifters", "ralios"), RALIOS);
		Registry.register(Registry.ITEM, new Identifier("space_rifters", "narstone"), NARSTONE);
		Registry.register(Registry.ITEM, new Identifier("space_rifters", "tear_of_light"), TEAR_OF_LIGHT);

		Registry.register(Registry.ITEM, new Identifier("space_rifters","lumos_ore"), new BlockItem(LUMOS_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("space_rifters", "crusher"), new BlockItem(CRUSHER, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, new Identifier("space_rifters","hammer"), HAMMER);

		LOGGER.info("Hello Fabric world!");

	}
}
