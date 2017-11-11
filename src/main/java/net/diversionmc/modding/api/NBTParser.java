package net.diversionmc.modding.api;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * Utils for manipulating NBT.
 */
@SuppressWarnings({"ConstantConditions", "deprecation"})
public class NBTParser {
    public NBTTagCompound c;
    
    public NBTParser(String s) {
        this.c = parseCompound(s);
    }
    
    /**
     * Get item from stored compound.
     * @return Bukkit ItemStack, null if no compound is stored.
     */
    public ItemStack toItem() {
        return new ItemStack(c);
    }

    /**
     * Get item from stored compound.
     * @return Bukkit ItemStack, null if no compound is stored.
     */
    public void setForBlock(World w, BlockPos p) {
        setTagCompound(w, p, c);
    }
    
    /**
     * Parse compound out of string.
     * @param s NBT string.
     * @return Parsed NBT tag.
     */
    public static NBTTagCompound parseCompound(String s) {
        try {
			return JsonToNBT.getTagFromJson(s);
		} catch (NBTException e) {
			return null;
		}
    }
    
    /**
     * Get NBT tag compound of already existing ItemStack.
     * @param i Bukkit ItemStack.
     * @return Parsed NBT tag.
     */
    public static NBTTagCompound getTagCompound(ItemStack i) {
        return i.serializeNBT();
    }

    /**
     * Get NBT tag compound of already existing ItemStack.
     * @param b Bukkit Block.
     * @return Parsed NBT tag.
     */
    public static NBTTagCompound getTagCompound(World w, BlockPos p) {
    	TileEntity te = w.getTileEntity(p);
        NBTTagCompound nbt = new NBTTagCompound();
        if (te == null) {
            nbt.setDouble("x", p.getX());
            nbt.setDouble("y", p.getY());
            nbt.setDouble("z", p.getZ());
        } else nbt = te.serializeNBT();
        NBTUtil.writeBlockState(nbt, w.getBlockState(p));
        return nbt;
    }
    
    /**
     * Set NBT tag compound of ItemStack clone.
     * @param i Bukkit ItemStack.
     * @param nbt Parsed NBT tag.
     * @return ItemStack with changed tag.
     */
    public static ItemStack setTagCompound(ItemStack i, NBTTagCompound nbt) {
    	i.deserializeNBT(nbt);
        return i;
    }

    /**
     * Set NBT tag compound of Block.
     * @param b Bukkit Block.
     * @param nbt Parsed NBT tag.
     * @return Block with changed tag.
     */
    public static void setTagCompound(World w, BlockPos p, NBTTagCompound nbt) {
        nbt.setDouble("x", p.getX());
        nbt.setDouble("y", p.getY());
        nbt.setDouble("z", p.getZ());
        if (nbt.hasKey("State")) {
            IBlockState state = NBTUtil.readBlockState(nbt);          
            w.setBlockState(p, state);
        }
        TileEntity te = w.getTileEntity(p);
        te.deserializeNBT(nbt);
    }
}
