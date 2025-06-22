package com.moa.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MoaPlayerActions 
{
	
	public static boolean rightClickAir(PlayerInteractEvent e) 
	{	
		return e.getAction().equals(Action.RIGHT_CLICK_AIR);
	}
	
	public static boolean leftClickAir(PlayerInteractEvent e) 
	{
		return e.getAction().equals(Action.LEFT_CLICK_AIR);
	}
	
	public static boolean rightClickBlock(PlayerInteractEvent e) 
	{
		return e.getAction().equals(Action.RIGHT_CLICK_BLOCK);
	}
	
	public static boolean leftClickBlock(PlayerInteractEvent e) 
	{
		return e.getAction().equals(Action.LEFT_CLICK_BLOCK);
	}
	
	public static boolean shiftRightClickAir(PlayerInteractEvent e) 
	{	
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.RIGHT_CLICK_AIR);
	}
	
	public static boolean shiftLeftClickAir(PlayerInteractEvent e) 
	{
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.LEFT_CLICK_AIR);
	}
	
	public static boolean shiftRightClickBlock(PlayerInteractEvent e) 
	{
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK);
	}
	
	public static boolean shiftLeftClickBlock(PlayerInteractEvent e) 
	{
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.LEFT_CLICK_BLOCK);
	}
	
	public static Map<UUID, Long> timeMap = new HashMap<>();
	public static boolean advancedHeldAction(UUID uuid, Action aType, Consumer<PlayerInteractEvent> actionEvent, long holdTicks) 
	{	
		long sysTime = System.currentTimeMillis();
		
		if (!timeMap.containsKey(uuid)) 
		{
			timeMap.put(uuid, sysTime);
		}
		
		long elapsedTime = sysTime - timeMap.get(uuid);
		if (elapsedTime >= (holdTicks * 50)) 
		{
			Player p = Bukkit.getPlayer(uuid);
			
			if (p != null) 
			{
				PlayerInteractEvent e = new PlayerInteractEvent(p, aType, p.getInventory().getItemInMainHand(), null, null);
				actionEvent.accept(e);
			}
			
			timeMap.remove(uuid);
			return true;
		}
		return false;
	}
	
}

//Imported From Project Nexus