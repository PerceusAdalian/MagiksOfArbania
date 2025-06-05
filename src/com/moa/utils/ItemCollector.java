package com.moa.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;

import com.moa.MagiksOfArbania;

public class ItemCollector 
{
	public static void remove(PlayerInteractEvent e) 
	{
		if (MagiksOfArbania.debug == false) 
		{
			Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
			{
				e.getItem().setAmount(e.getItem().getAmount() - 1);
			}, 1);			
		}
	}
}

//Imported From Project Nexus