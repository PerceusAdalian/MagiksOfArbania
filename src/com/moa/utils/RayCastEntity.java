package com.moa.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

public class RayCastEntity 
{
	public static Entity getNearest(Player p, int range)
	{
	    RayTraceResult result = p.getLocation().getWorld().rayTraceEntities(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(2)),p.getEyeLocation().getDirection(),range);
	    
	    if(result == null || result.getHitEntity() == null) return null;
	    return result.getHitEntity();
	}
}

//Imported From Project Nexus