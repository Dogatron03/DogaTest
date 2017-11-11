package net.diversionmc.modding.items;

import net.diversionmc.modding.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemStructureWand extends Item{

	public ItemStructureWand() {
		this.setUnlocalizedName("struc_wand");
		this.setRegistryName(new ResourceLocation(Reference.MODID, "struc_wand"));
	}
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		
		return EnumActionResult.SUCCESS;
	}
	
}
