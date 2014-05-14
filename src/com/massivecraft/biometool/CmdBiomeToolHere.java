package com.massivecraft.biometool;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;

import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBiomeToolHere extends BiomeToolCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdBiomeToolHere()
	{
		// Aliases
		this.addAliases("here");
		
		// Requirements
		this.addRequirements(new ReqHasPerm(Perm.HERE.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		Location loc = me.getLocation();
				
		World world = loc.getWorld();
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		
		Biome biome = world.getBiome(x, z);
		
		Integer id = biome.ordinal();
		String name = biome.toString();
		
		msg("<i>Biome here is <k>id <v>%s<i> and <k>Name <v>%s<i>.", id.toString(), name);
	}
	
}