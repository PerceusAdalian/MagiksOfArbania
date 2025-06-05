package com.moa.objects;

import org.bukkit.entity.Player;

import com.moa.objects.instances.GlacioCatalyst;
import com.moa.objects.instances.InfernoCatalyst;

public class SpellHolders 
{
	public static void init(Player p) 
	{
		//Inferno Maps
		InfernoCatalyst.getIsChanneled().put(p.getUniqueId(), false);
		InfernoCatalyst.getIsHyperCasting().put(p.getUniqueId(), false);
		
		GlacioCatalyst.getIsChanneled().put(p.getUniqueId(), false);
		GlacioCatalyst.getIsHyperCasting().put(p.getUniqueId(), false);
	}
}
