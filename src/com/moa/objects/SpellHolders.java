package com.moa.objects;

import org.bukkit.entity.Player;

import com.moa.objects.instances.FireCatalyst;

public class SpellHolders 
{
	public static void init(Player p) 
	{
		//Inferno Maps
		FireCatalyst.isChanneled.put(p.getUniqueId(), false);
		FireCatalyst.isHyperCasting.put(p.getUniqueId(), false);
		
		
	}
}
