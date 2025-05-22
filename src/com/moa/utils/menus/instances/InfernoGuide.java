package com.moa.utils.menus.instances;

import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.moa.utils.MoaAbilityTypes;
import com.moa.utils.MoaEffects;
import com.moa.utils.MoaPrintUtils;
import com.moa.utils.menus.AbstractMoaGui;
import com.moa.utils.menus.MoaGuiButton;
import com.moa.utils.menus.MoaGuiHandler;

public class InfernoGuide extends AbstractMoaGui
{

	public InfernoGuide(Player player) 
	{
		super(player, "۞}- Inferno Arcana -{۞", 54, Set.of(37,43));
	}

	@Override
	protected void build() 
	{
		MoaGuiButton.button(Material.OAK_SIGN)
		.setName("&r&c&lInferno&r&f Arcanum Overview:")
		.setLore(
				"&r&fThe &r&c&lInferno Arcanum&r&f, often called &cFire&f Magik, is the oldest and",
				"&r&fmore widely practiced form of arcana -- a force as vital as it is volitile.",
				"&r&fWhether you're a cook, smith, or a battle-mage, mishandling fire",
				"&r&fis a swift path to ash. Fickle, consuming, and rarely extinguished,",
				"&r&ffire rewards discipline and punishes arrogance. Its essence is patient,",
				"&r&fslow to fade, yet ever-hungry. Mastering is not about control, but",
				"&r&fcommunion. Make no mistake, &r&c&lInferno&r&f spells are among the most potent",
				"&r&fin the &eArbanian&f codices. But to wield them well, you must heed the whispering",
				"&r&fembers for they remember the primal truths and forgotten wars of yore.")
		.place(this, 4, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			List<Sound> sounds = List.of(Sound.BLOCK_BAMBOO_WOOD_HANGING_SIGN_PLACE,Sound.BLOCK_CHERRY_WOOD_HANGING_SIGN_PLACE,Sound.BLOCK_HANGING_SIGN_PLACE);
			MoaEffects.playSound(p, p.getLocation(), sounds.iterator().next(), SoundCategory.MASTER, 1, 1);
		});

		MoaGuiButton.button(Material.BOOK).setName("Inferno Main Combo")
		.setLore("&r&fThe main combo for the Inferno Catalyst may be activated using",
				"&r&f&lRight-Click&r&f and consists of four spells:",
				"&r&f1&l. &r&fFireball",
				"&r&f&l2. &r&fArcfire",
				"&r&f&l3. &r&fSol Gate",
				"&r&f&l4. &r&fDragons' Respite",
				"&r&f&lCombo Timer&r&f: 10 &b&oseconds")
		.place(this, 10, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lCombo Spell 1: &r&fFireball")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click",
		"&r&f&lDescription: ",
		"&r&fSummon forth a ball of &cFire&f that explodes on impact.")
		.place(this, 11, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lCombo Spell 2: &r&fArcfire")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click",
		"&r&f&lDescription: ",
		"&r&fSummon forth several small whisps of &cFire&f that ignites objects.")
		.place(this, 12, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lCombo Spell 3: &r&fSol Gate")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click",
		"&r&f&lDescription: ",
		"&r&fSummon forth a large ball of &cFire&f that explodes on impact.")
		.place(this, 13, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lCombo Spell 4: &r&fDragons' Respite")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click",
		"&r&f&lDescription: ",
		"&r&fSummon forth a concentrated &dChaos Flame&f projectile that",
		"&r&fexplodes on impact and leaves behind a DOT-AOE.")
		.place(this, 14, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lSpell: &r&fIgnite")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click &b&o&&r&f target <= &f&l5&r &d&ometers",
		"&r&f&lDescription: ",
		"&r&fIgnite thy enemy within close range for 10s.")
		.place(this, 19, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);		
		});
		
		//Exits
		
		MoaGuiButton.button(Material.GREEN_STAINED_GLASS_PANE).setName("&r&f&l<-- &r&a&lGo Back").setLore("&r&f&oClick to return to the Spell Codex.").place(this, 37, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
			MoaGuiHandler.changeMenu(p, new MoaSpellCodexMenu(p));
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
