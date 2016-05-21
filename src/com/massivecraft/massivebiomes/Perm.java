package com.massivecraft.massivebiomes;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.util.PermissionUtil;

public enum Perm
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	BASECOMMAND("basecommand"),
	HERE("here"),
	LIST("list"),
	POS1("pos1"),
	POS1_HERE("pos1.here"),
	POS1_COORD("pos1.coord"),
	POS2("pos2"),
	POS2_HERE("pos2.here"),
	POS2_COORD("pos2.coord"),
	CONVERT("convert"),
	VERSION("version"),
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final String node;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	Perm(final String node)
	{
		this.node = "massivebiomes."+node;
    }
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
	public boolean has(CommandSender sender, boolean informSenderIfNot)
	{
		return PermissionUtil.hasPermission(sender, this.node, informSenderIfNot);
	}
	
	public boolean has(CommandSender sender)
	{
		return has(sender, false);
	}
	
}
