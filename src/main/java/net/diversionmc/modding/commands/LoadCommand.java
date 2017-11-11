package net.diversionmc.modding.commands;

import java.io.File;

import net.diversionmc.modding.api.BlockInfo;
import net.diversionmc.modding.api.ChatColor;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class LoadCommand extends CommandBase {

	@Override
	public String getName() {
		return "sload";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.sload.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer p = getCommandSenderAsPlayer(sender);
		if (args.length != 1) {
			throw new WrongUsageException("commands.sload.usage");
		}

		File f = new File(p.getEntityWorld().getSaveHandler().getWorldDirectory() + File.separator + "dnstructures/", args[0] + ".dnstruc");
		if (!f.exists()) {
			p.sendMessage(new TextComponentTranslation("commands.sload.fileNotExist"));
			return;
		}
		BlockInfo b = new BlockInfo(p.getPosition(), p.getEntityWorld());
		b.loadFromFile(f);
		b.setBlocks();
		p.sendMessage(new TextComponentTranslation("commands.sload.success"));
	}
}
