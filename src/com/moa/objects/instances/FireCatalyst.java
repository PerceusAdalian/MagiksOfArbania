package com.moa.objects.instances;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.moa.MagiksOfArbania;
import com.moa.objects.AbstractMoaObject;
import com.moa.utils.ElementType;
import com.moa.utils.MoaEffects;
import com.moa.utils.MoaParticles;
import com.moa.utils.MoaPlayerActions;
import com.moa.utils.MoaStandardTimer;
import com.moa.utils.RayCastEntity;

public class FireCatalyst extends AbstractMoaObject
{

	public FireCatalyst()
	{
		super("fire_catalyst", Material.FIRE_CHARGE, true, ElementType.INFERNO, 
				"&r&fA simple manifestation of &c&lInferno&r&f energy.", 
				"&r&fThermal energy radiates outwards as small,",
				"&r&fharmless sparks burst off of it. You can use this as",
				"&r&fa medium for catalyzing ether to cast &cFire&f magiks.");
		
	} 

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (MoaPlayerActions.rightClickAir(e)) 
		{
			
			Entity target = RayCastEntity.getNearest(p, 10);
			
			if (target == null) 
			{	
				MoaParticles.drawInfernoCastSigil(p);
				MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
				Fireball fireball = (Fireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.5)), EntityType.FIREBALL);
				fireball.setYield(1);
				return true;
			}
			
			MoaParticles.drawInfernoCastSigil(p);
			MoaParticles.drawLine(p.getLocation(), target.getLocation(), 2, 0.5, Particle.FLAME, null);
			MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
			Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
			{
				MoaParticles.drawVerticalVortex(target.getLocation(), target.getWidth(), target.getHeight()+1, 0.5, 3, 5, 0.25, Particle.FALLING_LAVA, null);
				target.setFireTicks(200);
				MoaStandardTimer.runWithCancel(MagiksOfArbania.instance, (r)->
				{
					MoaParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 4, Particle.FLAME, null);
				}, 20, 200);
			}, 20);
			return true;
		
		}
		return false;
	}

}
