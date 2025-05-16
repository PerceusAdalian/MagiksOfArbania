package com.moa.objects.instances;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.moa.objects.AbstractMoaObject;
import com.moa.objects.ElementType;
import com.moa.utils.MoaEffects;
import com.moa.utils.MoaParticles;
import com.moa.utils.MoaPlayerActions;

public class FireCatalyst extends AbstractMoaObject
{

	public FireCatalyst()
	{
		super("fire_catalyst", Material.FIRE_CHARGE, true, ElementType.INFERNO, 
				"&r&fThe &r&c&lInferno Arcanum&r&f, often called &cFire&f Magik, is the oldest and",
				"&r&fmore widely practiced form of arcana -- a force as vital as it is volitile.",
				"&r&fWhether you're a cook, smith, or a battle-mage, mishandling fire",
				"&r&fis a swift path to ash. Fickle, consuming, and rarely extinguished,",
				"&r&ffire rewards discipline and punishes arrogance. Its essence is patient,",
				"&r&fslow to fade, yet ever-hungry. Mastering is not about control, but",
				"&r&fcommunion. Make no mistake, &r&c&lInferno&r&f spells are among the most potent",
				"&r&fin the &eArbanian&f codices. But to wield them well, you must heed the whispering",
				"&r&fembers for they remember the primal truths and forgotten wars of yore.");
		
	} 

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (MoaPlayerActions.rightClickAir(e)) 
		{
			Player p = e.getPlayer();
			MoaParticles.drawDisc(p.getLocation(), p.getWidth(), 1, 17, 0.5, Particle.FLAME, null);
			MoaParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 10, 0.5, Particle.ASH, null);
			MoaParticles.drawDisc(p.getLocation(), p.getWidth()-0.25, 2, 7, 0.25, Particle.SMOKE, null);
			MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
			Fireball fireball = (Fireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.5)), EntityType.FIREBALL);
			fireball.setYield(1);
			return true;
		}
		return false;
	}

}
