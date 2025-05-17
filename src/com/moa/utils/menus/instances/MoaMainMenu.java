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

public class MoaMainMenu extends AbstractMoaGui
{

	public MoaMainMenu(Player player) 
	{
		super(player, "✧}- Magiks of Arbania -{✧", 27, Set.of(10,13,16));
	}

	@Override
	protected void build() 
	{
		MoaGuiButton.button(Material.WRITTEN_BOOK).setName("&e&lSpell Guide").setLore("&r&f&oClick to view the spellcasting guide and codex.").place(this, 13, e->
		{
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHISELED_BOOKSHELF_PICKUP_ENCHANTED, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.changeMenu(p, new MoaSpellCodexMenu(p));
		});
		
		//Exits
		MoaGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("&r&f&oClick to exit.").place(this, 10, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PUT, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.close(p);
		});
		
		MoaGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("&r&f&oClick to exit.").place(this, 16, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PUT, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.close(p);
		});
		paint();
	}

}
