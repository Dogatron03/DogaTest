package net.diversionmc.modding.commands;

import net.diversionmc.modding.api.Selection;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class SaveCommand extends CommandBase {

	@Override
	public String getName() {
		return "ssave";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.ssave.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
			EntityPlayerMP p = getCommandSenderAsPlayer(sender);
		if (args.length != 1) {
			throw new WrongUsageException("commands.ssave.usage");
		}
		if (!Selection.selections.containsKey(p)) {
			p.sendMessage(new TextComponentTranslation("commands.ssave.spos"));
			return;
		}
		Selection selection = Selection.selections.get(p);
		if (selection.l1 == null || selection.l2 == null) {
			p.sendMessage(new TextComponentTranslation("commands.ssave.selection"));
			return;
		}
		selection.save(args[0], p.getPosition(), p.getServerWorld());
		p.sendMessage(new TextComponentTranslation("commands.ssave.success"));
	}

}
