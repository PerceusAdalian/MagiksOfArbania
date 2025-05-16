package com.moa.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

public class WorldEvents 
{
	public static List<Block> getNearbyBlocks(Location origin, int radius) 
	{
		List<Block> locations = new ArrayList<>();
		for (int iy = -radius; iy <= radius; iy++)
		{
			for (int ix = -radius; ix <= radius; ix++)
			{
				for (int iz = -radius; iz <= radius; iz++)
				{
					Location newLoc = origin.clone().add(ix,iy,iz);
					locations.add(newLoc.getBlock());
				}
			}
		}
		return locations;
	}
	
	public static Block rayTraceBlock(Player p, double range) 
	{
		Location eye = p.getEyeLocation();
		RayTraceResult result = p.getWorld().rayTraceBlocks(eye, eye.getDirection(), range);

	    if(result == null || result.getHitBlock() == null) return null;
	    return result.getHitBlock();
	}
}

//Imported From Project Nexus