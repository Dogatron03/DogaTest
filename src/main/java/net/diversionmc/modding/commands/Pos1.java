package net.diversionmc.modding.commands;

import net.diversionmc.modding.api.Selection;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class Pos1 extends CommandBase {

	@Override
	public String getName() {
		return "pos1";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.pos1.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer p = getCommandSenderAsPlayer(sender);
		 Selection sel = null;
	        if(Selection.selections.containsKey(p)){
	            sel = Selection.selections.get(p);
	        } else {
	            sel = new Selection(p);
	        }
	        sel.addFirstBlockPos(new BlockPos(p.getLookVec()));
	        p.sendMessage(new TextComponentTranslation("commands.pos1.success"));
	}
	
		

}
