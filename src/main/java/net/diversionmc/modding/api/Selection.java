package net.diversionmc.modding.api;

import java.io.File;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Selection {

	public static HashMap<EntityPlayer, Selection> selections;
	public double[] coords;
	public BlockPos l1;
	public BlockPos l2;
	public EntityPlayer p;

	public Selection(EntityPlayer p, double[] coords) {
		selections.put(p, this);
		this.coords = coords;
		this.l1 = new BlockPos(coords[0], coords[1], coords[2]);
		this.l2 = new BlockPos(coords[3], coords[4], coords[5]);
	}

	public Selection(EntityPlayer p) {
		selections.put(p, this);
		this.l1 = null;
		this.l2 = null;
		coords = null;
	}

	public Selection(BlockPos l1, BlockPos l2, EntityPlayer p) {
		selections.put(p, this);
		this.l1 = l1;
		this.l2 = l2;
		coords = new double[] { l1.getX(), l1.getY(), l1.getZ(), l2.getX(), l2.getY(), l2.getZ() };
	}

	public Selection(BlockPos l1, EntityPlayer p) {
		selections.put(p, this);
		this.l1 = l1;
		this.l2 = null;
		coords = null;
	}

	public void addSecondBlockPos(BlockPos l2) {
		this.l2 = l2;
		if (l1 != null && l2 != null)
			coords = new double[] { l1.getX(), l1.getY(), l1.getZ(), l2.getX(), l2.getY(), l2.getZ() };
	}

	public void addFirstBlockPos(BlockPos l1) {
		this.l1 = l1;
		if (l1 != null && l2 != null)
			coords = new double[] { l1.getX(), l1.getY(), l1.getZ(), l2.getX(), l2.getY(), l2.getZ() };
	}

	public void updateCoords() {
		if (l1 != null && l2 != null) {
			coords = Coordinates.parse(
					l1.getX() + "," + l1.getY() + "," + l1.getZ() + "," + l2.getX() + "," + l2.getY() + "," + l2.getZ(),
					Coordinates.CoordinateType.AREA);
		}
	}

	public void save(String name, BlockPos anchor, World w) {
		updateCoords();
		BlockInfo b = new BlockInfo(anchor, w);
		b.getFromCoordinates(coords);
		File f = new File(w.getSaveHandler().getWorldDirectory() + File.separator + "dnstructures" + File.separator + name + ".dnstruc");
		System.out.println(f);
		b.saveToFile(f);
	}

}
