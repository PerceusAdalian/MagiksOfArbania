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
		.place(this, 3, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			List<Sound> sounds = List.of(Sound.BLOCK_BAMBOO_WOOD_HANGING_SIGN_PLACE,Sound.BLOCK_CHERRY_WOOD_HANGING_SIGN_PLACE,Sound.BLOCK_HANGING_SIGN_PLACE);
			MoaEffects.playSound(p, p.getLocation(), sounds.iterator().next(), SoundCategory.MASTER, 1, 1);
		});

		MoaGuiButton.button(Material.BOOK).setName("Channeled Magiks Explanation")
		.setLore("&r&fSome magiks, including &c&lInferno&r&f, can be channeled.",
				"&r&fChanneling requirements are different from each arcanum school,",
				"&r&fHowever &c&lInferno&r&f can be channeled with: <&lLeft-Click&r&f>",
				"&r&fThere are some, however, that can be hyper-casted.",
				"&r&fHyper-casted magiks are usually come at a great cost to cast,",
				"&r&fHowever &c&lInferno&r&f can be Hyper-casted with: <&lShift_Left-Click&r&f>",
				"&r&fChanneled and Hyper-casted magiks usually alter the original",
				"&r&fand alternative spells for their respective catalyst.",
				"&r&fPlease make note: only the most studied mages may be allowed",
				"&r&fto cast such powerful and adept forms of arcana.")
		.place(this, 5, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHISELED_BOOKSHELF_PICKUP, SoundCategory.MASTER, 1, 1);
		});
		
		//Main Combo Guide
		MoaGuiButton.button(Material.BOOK).setName("Inferno Main Combo")
		.setLore("&r&fThe main combo for the Inferno Catalyst may be activated using",
				"&r&f&lRight-Click&r&f and consists of four spells:",
				"&r&f1&l. &r&fFireball",
				"&r&f&l2. &r&fArcfire",
				"&r&f&l3. &r&fSol Gate",
				"&r&f&l4. &r&fDragons' Respite",
				"&r&f&lCombo Timer&r&f: 10 &b&oseconds")
		.place(this, 11, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHISELED_BOOKSHELF_PICKUP, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lCombo Spell 1: &r&fFireball")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click",
		"&r&f&lDescription: ",
		"&r&fSummon forth a ball of &cFire&f that explodes on impact.")
		.place(this, 12, e->
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
		.place(this, 13, e->
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
		.place(this, 14, e->
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
		.place(this, 15, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		//Channeled Combo Guide
		MoaGuiButton.button(Material.ENCHANTED_BOOK).setName("Inferno Channeled Combo")
		.setLore("&r&fThe channeled combo for the Inferno Catalyst may be activated using",
				"&r&fChannel Toggle + &lRight-Click&r&f and consists of three spells:",
				"&r&f1&l. &r&fSol Gate II",
				"&r&f&l2. &r&fSolar Flare",
				"&r&f&l3. &r&fDraconic Meteor",
				"&r&f&lCombo Timer&r&f: 10 &b&oseconds")
		.place(this, 20, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHISELED_BOOKSHELF_PICKUP_ENCHANTED, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("Sol Gate II")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
				"&r&f&lActivation&r&f: Right-Click",
				"&r&f&lDescription: ",
				"&r&fSummon forth two concentrated &cSolar&f projectiles",
				"&r&ffrom above that explodes on impact.")
		.place(this, 21, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("Solar Flare")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
				"&r&f&lActivation&r&f: Right-Click",
				"&r&f&lDescription: ",
				"&r&fSummon forth three concentrated &cSolar&f projectiles",
				"&r&ffrom above that explodes on impact.")
		.place(this, 22, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		MoaGuiButton.button(Material.PAPER).setName("Draconic Meteor")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
				"&r&f&lActivation&r&f: Right-Click",
				"&r&f&lDescription: ",
				"&r&fSummon forth a concentrated &cSolar&f projectile",
				"&r&ffrom above that explodes greatly on impact.")
		.place(this, 23, e->{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
		});
		
		//Other Spells
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lSpell: &r&fIgnite")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: Right-Click &b&o&&r&f target <= &f&l5&r &d&ometers",
		"&r&f&lDescription: ",
		"&r&fIgnite thy enemy within close range for 10s.")
		.place(this, 29, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);		
		});

		MoaGuiButton.button(Material.PAPER).setName("&r&f&lSpell: &r&fMagma Armor")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.DEFENSIVE),
		"&r&f&lActivation&r&f: Shift_Right-Click",
		"&r&f&lDescription: ",
		"&r&fGrants temporary fire resistance (&730s)")
		.place(this, 30, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);		
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lSpell: &r&fDraconic Overdrive")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.DEFENSIVE,MoaAbilityTypes.SUPPORT),
		"&r&f&lActivation&r&f: <Channeled> Shift_Right-Click",
		"&r&f&lDescription: ",
		"&r&fGrants temporary &bResistance&f, &bHaste&f, &bSpeed&f, and &bStrength&f &b&lIII&r&f (&730s&f)")
		.place(this, 31, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);		
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lSpell: &r&fHeat Wave")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: <Hyper-Casting> Shift_Right-Click",
		"&r&f&lDescription: ",
		"&r&fSet ablaze all within &b30 meters&f for &b25 seconds&f.")
		.place(this, 32, e->
		{
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);		
		});
		
		MoaGuiButton.button(Material.PAPER).setName("&r&f&lSpell: &r&fCastrum Infernis")
		.setLore(MoaPrintUtils.assignAbilityType(MoaAbilityTypes.OFFENSIVE),
		"&r&f&lActivation&r&f: <Hyper-Casting> <Channeled> Shift_Right-Click",
		"&r&f&lDescription: ",
		"&r&fCall down meteors from hell, targeting all within 30 meters.",
		"&r&fThese projectiles deal massive damage and explode on impact.")
		.place(this, 33, e->
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
