package com.moa.utils.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.moa.utils.MoaPrintUtils;

public class MoaGuiButton 
{
	private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore = new ArrayList<>();
    
    public MoaGuiButton(Material material) 
    {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }
    
    public MoaGuiButton(ItemStack obj) 
    {
		this.item = new ItemStack(obj);
		this.meta = obj.getItemMeta();
    }
    
    public static MoaGuiButton button(Material material) 
    {
        return new MoaGuiButton(material);
    }
    
    public static MoaGuiButton button(ItemStack stack) 
    {
    	return new MoaGuiButton(stack);
    }
    
    public MoaGuiButton setName(String name) 
    {
        meta.setDisplayName(MoaPrintUtils.ColorParser(name));
        return this;
    }

    public MoaGuiButton setLore(String... lines) 
    {
        for (String line : lines) 
        {
            lore.add(MoaPrintUtils.ColorParser("&r&f" + line));
        }
        return this;
    }
    
    public void place(AbstractMoaGui gui, int slot, Consumer<InventoryClickEvent> action) 
    {
        meta.setLore(lore);
        item.setItemMeta(meta);
        gui.getInventory().setItem(slot, item);
        gui.clickActions.put(slot, action);
    }
    
}
