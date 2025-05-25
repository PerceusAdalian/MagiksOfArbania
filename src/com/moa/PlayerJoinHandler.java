package com.moa;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.moa.objects.AbstractMoaObject;
import com.moa.objects.MoaItemRegistry;
import com.moa.objects.SpellHolders;

public class PlayerJoinHandler implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		Player p = e.getPlayer();
		p.setCanPickupItems(true);
		
		if (p.isOp()) 
		{
			SpellHolders.init(p);
			p.setGameMode(GameMode.CREATIVE);
			p.setAllowFlight(true);
			return;
		}

		if (!p.hasPlayedBefore()) 
		{
			SpellHolders.init(p);
			AbstractMoaObject crystal = MoaItemRegistry.itemRegistry.values().iterator().next();
			p.getInventory().addItem(crystal.bake());
			return;
		}
		
		SpellHolders.init(p);
		p.setGameMode(GameMode.SURVIVAL);
		p.setAllowFlight(false);
		p.setFlying(false);
		return;
	}
}
