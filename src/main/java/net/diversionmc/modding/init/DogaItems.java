package net.diversionmc.modding.init;

import net.diversionmc.modding.Reference;
import net.diversionmc.modding.items.ItemStructureWand;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class DogaItems {
	
	public static CreativeTabs items;
	
	public static ItemStructureWand wand;
	
	public static void init() {

		wand = new ItemStructureWand();
		
	}
	
	public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(wand);
        System.out.println("This works!");
    }
	
	@SideOnly(Side.CLIENT)
	public static void registerModels() {
		registerModel(wand);
	}
	
	public static void registerModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}
	


}
