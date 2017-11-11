package net.diversionmc.modding;

import java.util.HashMap;

import net.diversionmc.modding.api.Selection;
import net.diversionmc.modding.commands.LoadCommand;
import net.diversionmc.modding.commands.Pos1;
import net.diversionmc.modding.commands.Pos2;
import net.diversionmc.modding.commands.SaveCommand;
import net.diversionmc.modding.init.DogaItems;
import net.diversionmc.modding.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
@Mod(name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTEDMCVERSIONS, modid = Reference.MODID)
public class DogaTest {

	@Instance(Reference.MODID)
	public static DogaTest instance;

	@SidedProxy(clientSide = Reference.CLIENTPROXYCLASS, serverSide = Reference.SERVERPROXYCLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void onPreInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(instance);
		proxy.onPreInit();
		DogaItems.init();
        Selection.selections = new HashMap<>();
	}

	@EventHandler
	public static void onInit(FMLInitializationEvent e) {
		proxy.onInit();
	}

	@EventHandler
	public static void onPostInit(FMLPostInitializationEvent e) {
		proxy.onPostInit();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		DogaItems.registerItems(event.getRegistry());
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		DogaItems.registerModels();
	}

	@EventHandler
	public static void onServerStart(FMLServerStartingEvent e) {
		e.registerServerCommand(new LoadCommand());
		e.registerServerCommand(new SaveCommand());
		e.registerServerCommand(new Pos1());
		e.registerServerCommand(new Pos2());		
	}

}
