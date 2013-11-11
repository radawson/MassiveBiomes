package com.massivecraft.biometool;

import java.util.Map;

import org.bukkit.World;

import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.util.BiomeUtil;

public class CmdBiomeToolConvert extends BiomeToolCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdBiomeToolConvert()
	{
		// Aliases
		this.addAliases("convert");
		
		// Args
		this.addRequiredArg("fromId|all");
		this.addRequiredArg("toId");
		
		// Requirements
		this.addRequirements(new ReqHasPerm(Perm.CONVERT.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		boolean all = false;
		
		Map<Integer, String> biomeIdNames = BiomeUtil.getBiomeIdNames();
		World world = me.getWorld();
		
		Integer fromId = null;
		Integer toId = null;
		
		String fromName = null;
		String toName = null;
		
		if (this.arg(0).toLowerCase().startsWith("a"))
		{
			all = true;
		}
		else
		{
			fromId = this.arg(0, ARInteger.get());
			if (fromId == null) return;
			
			fromName = biomeIdNames.get(fromId);
			if (fromName == null) {
				msg("<e>Invalid fromId");
				return;
			}
		}
		
		toId = this.arg(1, ARInteger.get());
		if (toId == null) return;
		toName = biomeIdNames.get(toId);
		if (toName == null) {
			msg("<e>Invalid toId");
			return;
		}
		
		int xmin = mme.getXMin();
		int xmax = mme.getXMax();
		int zmin = mme.getZMin();
		int zmax = mme.getZMax();
		
		int area = (xmax - xmin + 1) * (zmax - zmin + 1);
		
		msg("<i>Now converting <h>"+area+" coords to <h>"+toName+"<i>.");
		
		for (int x = xmin; x <= xmax; x++)
		{
			for (int z = zmin; z <= zmax; z++)
			{
				if ( ! all)
				{
					int biomeIdHere = BiomeUtil.getBiomeIdAt(world, x, z);
					if (biomeIdHere != fromId)
					{
						continue;
					}
				}
				BiomeUtil.setBiomeIdAt(world, x, z, toId);
			}
		}
		msg("<i>DONE");
	}
	
}