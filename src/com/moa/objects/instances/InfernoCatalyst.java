package com.moa.objects.instances;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import com.moa.MagiksOfArbania;
import com.moa.objects.AbstractMoaObject;
import com.moa.utils.ElementType;
import com.moa.utils.MoaEffects;
import com.moa.utils.MoaParticles;
import com.moa.utils.MoaPlayerActions;
import com.moa.utils.MoaPrintUtils;
import com.moa.utils.MoaStandardTimer;
import com.moa.utils.RayCastEntity;
import com.moa.utils.WorldEvents;

public class InfernoCatalyst extends AbstractMoaObject
{

	public InfernoCatalyst()
	{
		super("inferno_catalyst", Material.FIRE_CHARGE, true, ElementType.INFERNO, 
				"&r&fA simple manifestation of &c&lInferno&r&f energy.", 
				"&r&fThermal energy radiates outwards as small,",
				"&r&fharmless sparks burst off of it. You can use this as",
				"&r&fa medium for catalyzing ether to cast &cFire&f magiks.");
		
	} 

	private static Map<UUID, Integer> comboTier = new HashMap<>();
	private static Map<UUID, Boolean> comboEndNaturally = new HashMap<>();

	private static Map<UUID, Boolean> isChanneled = new HashMap<>();
	private static Map<UUID, Boolean> isHyperCasting = new HashMap<>();
	
	private static Map<UUID, Integer> combo2Tier = new HashMap<>();
	private static Map<UUID, Boolean> combo2EndNaturally = new HashMap<>();
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		
		if (MoaPlayerActions.shiftLeftClickAir(e)) 
		{
			//Registration if the map to player is null or doesn't contain the player; for Shift+ spells.
			
			if (getIsHyperCasting().get(uuid) == null) 
			{
				getIsHyperCasting().put(uuid, true);
				MoaPrintUtils.PrintToActionBar(p, "Hyper casting..");
				MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
				return true;
			}
			
			//Execute toggle
			if (Boolean.TRUE.equals(getIsHyperCasting().get(uuid))) 
			{
				getIsHyperCasting().put(uuid, false);
			    MoaPrintUtils.PrintToActionBar(p, "Hyper casting disengaged..");
			    MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			    return true;
			} 
			else 
			{
				getIsHyperCasting().put(uuid, true);
			    MoaPrintUtils.PrintToActionBar(p, "Hyper casting..");
			    MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			    return true;
			}
		}
		
		if (MoaPlayerActions.leftClickAir(e)) 
		{
			//Registration if the map to player is null or doesn't contain the player; for primary spells.
			
			if (getIsChanneled().get(uuid) == null) 
			{
				getIsChanneled().put(uuid, true);
				MoaParticles.drawInfernoCastSigil(p);
				MoaPrintUtils.PrintToActionBar(p, "Channeling..");
				MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
				return true;
			}
			
			//Execute toggle
			if (Boolean.TRUE.equals(getIsChanneled().get(uuid))) 
			{
			    getIsChanneled().put(uuid, false);
				MoaParticles.drawInfernoCastSigil(p);
			    MoaPrintUtils.PrintToActionBar(p, "No longer channeling..");
			    MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			    return true;
			} 
			else 
			{
			    getIsChanneled().put(uuid, true);
				MoaParticles.drawInfernoCastSigil(p);
			    MoaPrintUtils.PrintToActionBar(p, "Channeling..");
			    MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			    return true;
			}

		}
		
		//Alt spells
		if (MoaPlayerActions.shiftRightClickAir(e)) 
		{
			if (getIsHyperCasting().get(uuid) == null) getIsHyperCasting().put(uuid, false);
			if (getIsChanneled().get(uuid) == null) getIsChanneled().put(uuid, false);
			
			if ((getIsHyperCasting().get(uuid).equals(false) && getIsChanneled().get(uuid).equals(false))) 
			{
				MoaParticles.drawInfernoCastSigil(p);
				MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, SoundCategory.MASTER, 1, 1);
				MoaEffects.add(p, PotionEffectType.FIRE_RESISTANCE, 600, 0);
				return true;
			}
			
			if ((getIsHyperCasting().get(uuid).equals(false) && getIsChanneled().get(uuid).equals(true))) 
			{
				MoaParticles.drawInfernoCastSigil(p);
				MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, SoundCategory.MASTER, 1, 1);
				MoaEffects.add(p, PotionEffectType.RESISTANCE, 600, 2);
				MoaEffects.add(p, PotionEffectType.HASTE, 600, 2);
				MoaEffects.add(p, PotionEffectType.SPEED, 600, 2);
				MoaEffects.add(p, PotionEffectType.STRENGTH, 600, 2);
				getIsChanneled().put(uuid, false);
				MoaPrintUtils.PrintToActionBar(p, "Hyper casting disengaged..");
				return true;
			}
			
			if ((getIsHyperCasting().get(uuid).equals(true) && getIsChanneled().get(uuid).equals(false))) 
			{
				MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, SoundCategory.MASTER, 1, 1);
				MoaParticles.drawInfernoCastSigil(p);
				WorldEvents.getNearbyEntities(p, 30, (target)->
				{
					target.setFireTicks(500);
					MoaParticles.drawDisc(target.getLocation(), target.getWidth(), 1, 20, 0, Particle.CRIMSON_SPORE, null);
					MoaParticles.drawSpiralVortex(target.getLocation(), 2, target.getHeight()+0.5, 0, Particle.LAVA, null);
				});
				getIsHyperCasting().put(uuid, false);
				MoaPrintUtils.PrintToActionBar(p, "No longer channeling..");
			    MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
				return true; 
			}
			
			if ((getIsHyperCasting().get(uuid).equals(true) && getIsChanneled().get(uuid).equals(true))) 
			{
				MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1, 1);
				MoaParticles.drawInfernoCastSigil(p);
				WorldEvents.getNearbyEntities(p, 30, (target)->
				{
					Vector v = new Vector(0,-5,0);
					Location newLoc = target.getLocation().add(0,45,0);
					
					LargeFireball fb = (LargeFireball) target.getWorld().spawnEntity(newLoc, EntityType.FIREBALL);
					
					fb.setShooter(p);
					fb.setDirection(v);
					fb.setYield(2);
				});
				getIsChanneled().put(uuid, false);
				getIsHyperCasting().put(uuid, false);
				MoaPrintUtils.PrintToActionBar(p, "You feel drained..");
				MoaEffects.add(p, PotionEffectType.NAUSEA, 100, 0);
				MoaEffects.add(p, PotionEffectType.SLOWNESS, 200, 2);
				MoaEffects.add(p, PotionEffectType.WEAKNESS, 200, 2);
				MoaEffects.add(p, PotionEffectType.MINING_FATIGUE, 200, 2);
				MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
				return true;
			}
		}
		
		//Main spells
		if (MoaPlayerActions.rightClickAir(e)) 
		{
			//Registration if the player just logged in and the map is not yet bound to the player.
			if (getIsChanneled().get(uuid) == null) getIsChanneled().put(uuid, false);
			
			//Unchanneled Magiks:
			if (getIsChanneled().get(uuid).equals(false)) 
			{
				Entity target = RayCastEntity.getNearest(p, 5);
				
				if (target == null) 
				{	
					//starts the combo and 10s cooldown
					if (!comboTier.containsKey(uuid)) 
					{
						if (comboEndNaturally.containsKey(uuid)) 
						{
							comboEndNaturally.remove(uuid);
						}
						
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
						MoaParticles.drawInfernoCastSigil(p);
						Fireball fb = (Fireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.5)), EntityType.FIREBALL);
						fb.setYield(1);
						comboTier.put(uuid, 1);
						comboEndNaturally.put(uuid, false);
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							MoaPrintUtils.PrintToActionBar(p, "Combo Start");
						}, 5);
						
						Bukkit.getScheduler().runTaskLaterAsynchronously(MagiksOfArbania.instance, ()->
						{
							if (comboEndNaturally.get(uuid).equals(true)) 
							{
								return;
							}
							if (comboTier.containsKey(uuid)) 
							{	
								comboTier.remove(uuid);
								MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
								MoaPrintUtils.PrintToActionBar(p, "Combo Break");
							}
						}, 205);
						
						return true;
					}
					
					if (comboTier.containsKey(uuid) && comboTier.get(uuid).equals(1)) 
					{
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
						MoaParticles.drawInfernoCastSigil(p);
						SmallFireball fb = (SmallFireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.5)), EntityType.SMALL_FIREBALL);
						fb.setYield(2);
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
							SmallFireball fb2 = (SmallFireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(1.5)), EntityType.SMALL_FIREBALL);
							fb2.setGlowing(true);
						}, 3);
						comboTier.put(uuid, 2);
						return true;
					}
					
					if (comboTier.containsKey(uuid) && comboTier.get(uuid).equals(2)) 
					{
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
						MoaParticles.drawInfernoCastSigil(p);					
						LargeFireball fb = (LargeFireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(2.0)), EntityType.FIREBALL);
						fb.setYield(4);
						fb.setIsIncendiary(true);
						comboTier.put(uuid, 3);
						return true;
					}
					
					if (comboTier.containsKey(uuid) && comboTier.get(uuid).equals(3)) 
					{
						MoaParticles.drawInfernoCastSigil(p);
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1, 1);
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.MASTER, 1, 1);
							DragonFireball fb = (DragonFireball) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().multiply(3.0)), EntityType.DRAGON_FIREBALL);
							fb.setYield(5);						
						}, 4);
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
							MoaPrintUtils.PrintToActionBar(p, "Combo End");
						}, 5);
						comboTier.remove(uuid);
						comboEndNaturally.put(uuid, true);
						return true;
					}
					
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
			
			if (getIsChanneled().get(uuid).equals(true)) 
			{
				if (!combo2Tier.containsKey(uuid)) 
				{
					if (combo2EndNaturally.containsKey(uuid)) 
					{
						combo2EndNaturally.remove(uuid);
					}
					
					MoaParticles.drawInfernoCastSigil(p);
					
					// Get player's eye location and facing direction
					Location eyeLoc = p.getEyeLocation();
					Vector direction = eyeLoc.getDirection().normalize();

					// Get orthogonal direction (right vector) using cross product with up vector
					Vector right = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize();

					// Offsets
					Vector offset1 = right.clone().multiply(2.5).add(new Vector(0, 3, 0));  // right + up
					Vector offset2 = right.clone().multiply(-2.5).add(new Vector(0, 3, 0)); // left + up

					// Spawn positions
					Location spawnLoc1 = eyeLoc.clone().add(offset1);
					Location spawnLoc2 = eyeLoc.clone().add(offset2);

					// Fireball 1
					MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
					Fireball fb1 = (Fireball) p.getWorld().spawnEntity(spawnLoc1, EntityType.FIREBALL);
					fb1.setVelocity(direction.clone().multiply(1.5));
					fb1.setYield(1);

					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						// Fireball 2
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
						Fireball fb2 = (Fireball) p.getWorld().spawnEntity(spawnLoc2, EntityType.FIREBALL);
						fb2.setVelocity(direction.clone().multiply(1.5));
						fb2.setYield(1);
					}, 3);

					
					combo2Tier.put(uuid, 1);
					combo2EndNaturally.put(uuid, false);
					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						MoaPrintUtils.PrintToActionBar(p, "Channeled Combo Start");
					}, 5);
					
					Bukkit.getScheduler().runTaskLaterAsynchronously(MagiksOfArbania.instance, ()->
					{
						if (combo2EndNaturally.get(uuid).equals(true)) 
						{
							return;
						}
						if (combo2Tier.containsKey(uuid)) 
						{	
							combo2Tier.remove(uuid);
							MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
							MoaPrintUtils.PrintToActionBar(p, "Channeled Combo Break");
						}
					}, 205);
					
					return true;
				}
				
				if (combo2Tier.containsKey(uuid) && combo2Tier.get(uuid).equals(1)) 
				{
					// Get player's eye location and facing direction
					Location eyeLoc = p.getEyeLocation();
					Vector direction = eyeLoc.getDirection().normalize();

					// Get orthogonal direction (right vector) using cross product with up vector
					Vector right = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize();

					// Offsets
					Vector offset1 = right.clone().multiply(2.5).add(new Vector(0, 3, 0));  // right + up
					Vector offset2 = right.clone().multiply(-2.5).add(new Vector(0, 3, 0)); // left + up
					Vector offset3 = right.clone().multiply(0).add(new Vector(0,3,0));
					
					// Spawn positions
					Location spawnLoc1 = eyeLoc.clone().add(offset1);
					Location spawnLoc2 = eyeLoc.clone().add(offset2);
					Location spawnLoc3 = eyeLoc.clone().add(offset3);
					
					// Fireball 1
					MoaParticles.drawInfernoCastSigil(p);
					MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
					SmallFireball fb1 = (SmallFireball) p.getWorld().spawnEntity(spawnLoc2, EntityType.SMALL_FIREBALL);
					fb1.setVelocity(direction.clone().multiply(2));

					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						// Fireball 2
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
						SmallFireball fb2 = (SmallFireball) p.getWorld().spawnEntity(spawnLoc1, EntityType.SMALL_FIREBALL);
						fb2.setVelocity(direction.clone().multiply(2));
					}, 3);
					
					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						// Fireball 2
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
						Fireball fb3 = (Fireball) p.getWorld().spawnEntity(spawnLoc3, EntityType.FIREBALL);
						fb3.setVelocity(direction.clone().multiply(2));
						fb3.setYield(3);
					}, 6);
					
					combo2Tier.put(uuid, 2);
					return true;
				}
				
				if (combo2Tier.containsKey(uuid) && combo2Tier.get(uuid).equals(2)) 
				{
					Vector v = new Vector(0,-5,0);
					RayTraceResult result = p.getWorld().rayTraceBlocks(p.getEyeLocation(), p.getEyeLocation().getDirection(), 100, FluidCollisionMode.ALWAYS);
					if (result != null && result.getHitBlock() != null)
					{
						Block target = result.getHitBlock();
						Location newLoc = target.getLocation().add(0,45,0);
						MoaParticles.drawInfernoCastSigil(p);
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1, 1);
						LargeFireball fb = (LargeFireball) target.getWorld().spawnEntity(newLoc, EntityType.FIREBALL);
						
						fb.setShooter(p);
						fb.setDirection(v);
						fb.setYield(6);
						
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
							MoaPrintUtils.PrintToActionBar(p, "Combo End");
						}, 5);
						
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							getIsChanneled().put(uuid, false);
							MoaPrintUtils.PrintToActionBar(p, "No longer channeling..");
							MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
						}, 15);
						
						combo2Tier.remove(uuid);
						combo2EndNaturally.put(uuid, true);
						return true;
					}
					MoaPrintUtils.Print(p, "Invalid target");
					return false;
				}
			}
			
		}
		
		return false;
	}

	public static Map<UUID, Boolean> getIsChanneled() 
	{
		return isChanneled;
	}

	public static void setIsChanneled(Map<UUID, Boolean> isChanneled) 
	{
		InfernoCatalyst.isChanneled = isChanneled;
	}

	public static Map<UUID, Boolean> getIsHyperCasting() 
	{
		return isHyperCasting;
	}

	public static void setIsHyperCasting(Map<UUID, Boolean> isHyperCasting) 
	{
		InfernoCatalyst.isHyperCasting = isHyperCasting;
	}

}
