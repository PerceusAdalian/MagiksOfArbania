package com.moa.objects;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.moa.MagiksOfArbania;
import com.moa.utils.MoaPrintUtils;

public class CastHandler implements Listener
{
	// Make the cast e for Moa objects
	@EventHandler
	public boolean onCast(PlayerInteractEvent e) 
	{
		ItemStack held = e.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
		
		if (e.getHand() == null) return false;
		if (e.getHand().equals(EquipmentSlot.OFF_HAND) || !e.getHand().equals(EquipmentSlot.HAND)) return false;
		if (held == null || held.getType().equals(Material.AIR)) return false;		
		if (held.getItemMeta() == null || !held.getItemMeta().getPersistentDataContainer().has(AbstractMoaObject.moaObject, PersistentDataType.STRING)) return false;

		if (MoaItemRegistry.itemRegistry.get(held.getItemMeta().getPersistentDataContainer().get(AbstractMoaObject.moaObject, PersistentDataType.STRING)).Cast(e)) 
	    {
	    	e.setCancelled(true);
			
			if (MagiksOfArbania.debug) 
			{

				MoaPrintUtils.MoaFormatDebug(e.getPlayer(), e.getAction().toString());
				MoaPrintUtils.MoaFormatDebug(e.getPlayer(), e.getHand().toString());
				String internalName = null;
				if (held.getItemMeta() != null) 
				{
					internalName = held.getItemMeta().getPersistentDataContainer().get(AbstractMoaObject.moaObject, PersistentDataType.STRING);
				}
				
				if (internalName == null) 
				{
					MoaPrintUtils.MoaConsoleError("From: MoaCastHandler.java | Could not retrieve internal name from baked item.");
	                return true;
				}
				
				AbstractMoaObject moaObject = MoaItemRegistry.itemRegistry.get(internalName);
				if (moaObject == null) 
				{
					MoaPrintUtils.MoaConsoleError("From: MoaCastHandler.java | Internal name exists, but item is not in the registry.");
	                return true;
				}
				MoaPrintUtils.MoaConsoleDebug(e.getPlayer().getName() + " has used item: " + moaObject.getName());
			}
			return true;
	    }
		return false;
	}
}
