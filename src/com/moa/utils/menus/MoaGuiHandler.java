package com.moa.utils.menus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.moa.MagiksOfArbania;

public class MoaGuiHandler implements Listener
{
	private static final Map<Player, AbstractMoaGui> openGuis = new HashMap<>();

    public static void open(Player player, AbstractMoaGui gui) 
    {
        openGuis.put(player, gui);
        gui.open();
    }

    public static AbstractMoaGui getOpenGui(Player player) 
    {
        return openGuis.get(player);
    }

    public static void close(Player player) 
    {
        openGuis.remove(player);
        player.closeInventory();
    }

    public static void changeMenu(Player player, AbstractMoaGui gui) 
    {
    	close(player);
    	Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->open(player, gui), 1);
    }
    
    public static void registerEventListener(JavaPlugin plugin) 
    {
        Bukkit.getPluginManager().registerEvents(new Listener() 
        {
            @EventHandler
            public void onClick(InventoryClickEvent e) 
            {
                if (!(e.getWhoClicked() instanceof Player p)) return;
                AbstractMoaGui gui = openGuis.get(p);
                if (gui == null || !e.getView().getTitle().equals(gui.getGuiTitle())) return;

                gui.handleClick(e);
            }

            @EventHandler
            public void onClose(InventoryCloseEvent e) 
            {
            	AbstractMoaGui gui = openGuis.remove((Player) e.getPlayer());
                if (gui != null) gui.handleClose(e);
            }
        }, plugin);
    }
}
