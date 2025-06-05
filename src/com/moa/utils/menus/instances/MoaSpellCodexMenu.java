package com.moa.utils.menus.instances;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.moa.utils.MoaEffects;
import com.moa.utils.menus.AbstractMoaGui;
import com.moa.utils.menus.MoaGuiButton;
import com.moa.utils.menus.MoaGuiHandler;

public class MoaSpellCodexMenu extends AbstractMoaGui
{

	public MoaSpellCodexMenu(Player player) 
	{
		super(player, "⋊}- Spell Codex -{⋉", 54, Set.of(10,37,43));
	}

	@Override
	protected void build() 
	{
		MoaGuiButton.button(Material.FIRE_CHARGE).setName("&r&c&lInferno&r&f Arcana").setLore("&r&f&oClick to view the &c&lInferno&r&f Arcana spellcasting guide.").place(this, 10, e->
		{
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.changeMenu(p, new InfernoGuide(p));
		});
		
		//Exits
		MoaGuiButton.button(Material.GREEN_STAINED_GLASS_PANE).setName("&r&f&l<-- &r&a&lGo Back").setLore("&r&f&oClick to return to the Main Menu.").place(this, 37, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.changeMenu(p, new MoaMainMenu(p));
		});
		
		MoaGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("&r&f&oClick to exit.").place(this, 43, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PUT, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.close(p);
		});
		paint();
	}

}
