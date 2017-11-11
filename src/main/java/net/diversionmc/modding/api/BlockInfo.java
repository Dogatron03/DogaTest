package net.diversionmc.modding.api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Saves and loads blocks. Can use files.
 */
public class BlockInfo {
    /**
     * BlockPos relative to which blocks are set or saved.
     */
    public BlockPos anchor;
   
    public World w;
    /**
     * Blocks in NBT format, unparsed. Coordinate tags x, y, z are relative to anchor.
     */
    public List<String> blocks;

    /**
     * Create empty block info.
     *
     * @param anchor BlockPos relative to which blocks are set or saved.
     */
    public BlockInfo(BlockPos anchor, World w) {
        this(anchor, w, new LinkedList());
    }

    /**
     * Create empty block info.
     *
     * @param anchor BlockPos relative to which blocks are set or saved.
     * @param blocks Blocks in NBT format, unparsed. Coordinate tags x, y, z are relative to anchor.
     */
    public BlockInfo(BlockPos anchor, World w,List<String> blocks) {
        this.anchor = anchor;
        this.blocks = blocks;
        this.w = w;
    }

    /**
     * Load a file into block list.
     *
     * @param f File to load
     */
    public void loadFromFile(File f) {
        try {
            blocks = FileUtils.readLines(f, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save block list into a file.
     *
     * @param f File to save to
     */
    public void saveToFile(File f) {
        try {
            FileUtils.writeLines(f, blocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put blocks in a cuboid into block list.
     *
     * @param cuboid Cuboid to get blocks from. Anchor's world is used.
     */
    public void getFromCoordinates(double[] cuboid) {
        Integer[] coords = Arrays.stream(cuboid).boxed().map(Double::intValue).collect(Collectors.toList()).toArray(new Integer[0]);
        BlockPos.getAllInBox(new BlockPos(cuboid[0], cuboid[1], cuboid[2]), new BlockPos(cuboid[3], cuboid[4], cuboid[5]));
//        for (int x = coords[0]; x <= coords[3]; x++) {
//            for (int y = coords[1]; y <= coords[4]; y++) {
//                for (int z = coords[2]; z <= coords[5]; z++) {
//                    if (anchor.getWorld().getBlockAt(x, y, z).getType() == Material.AIR) continue;
//                    NBTTagCompound nbt = NBTParser.getTagCompound(anchor.get.getBlockAt(x, y, z));
//                    nbt.setInt("x", nbt.getInt("x") - anchor.getBlockX());
//                    nbt.setInt("y", nbt.getInt("y") - anchor.getBlockY());
//                    nbt.setInt("z", nbt.getInt("z") - anchor.getBlockZ());
//                    blocks.add(nbt + "");
//                }
//            }
//        }
    }

    /**
     * Set blocks according to block list and anchor.
     */
    public void setBlocks() {
        blocks.forEach(nbt -> {
            NBTParser prsr = new NBTParser(nbt);
            prsr.setForBlock(w, anchor.add(prsr.c.getDouble("x"), prsr.c.getDouble("y"), prsr.c.getDouble("z")));
        });
    }
}

