package com.moa;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.moa.objects.AbstractMoaObject;
import com.moa.objects.MoaItemRegistry;

public class PlayerJoinHandler implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		Player p = e.getPlayer();
		p.setCanPickupItems(true);
		if (p.isOp()) 
		{
			p.setGameMode(GameMode.CREATIVE);
			p.setAllowFlight(true);
			return;
		}

		if (!p.hasPlayedBefore()) 
		{
			AbstractMoaObject crystal = MoaItemRegistry.itemRegistry.values().iterator().next();
			p.getInventory().addItem(crystal.bake());
			return;
		}

		p.setGameMode(GameMode.SURVIVAL);
		p.setAllowFlight(false);
		p.setFlying(false);
		return;
	}
}
