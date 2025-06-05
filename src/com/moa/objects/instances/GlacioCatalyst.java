package com.moa.objects.instances;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.moa.MagiksOfArbania;
import com.moa.objects.AbstractMoaObject;
import com.moa.utils.ElementType;
import com.moa.utils.MoaEffects;
import com.moa.utils.MoaParticles;
import com.moa.utils.MoaPlayerActions;
import com.moa.utils.MoaPrintUtils;
import com.moa.utils.RayCastEntity;
import com.moa.utils.WorldEvents;

//See magiks of Arbania for project refactoring.
public class GlacioCatalyst extends AbstractMoaObject
{

	public GlacioCatalyst() 
	{
		super("glacio_catalyst", Material.PRISMARINE_CRYSTALS, true, ElementType.GLACIO, 
				"&r&fA simple manifestation of &b&lGlacio&r&f energy.",
				"&r&fCold condensate oscillates between liquid and solid,",
				"&r&fradiating outwards as frost and mist form toroidally.",
				"&r&fYou can use this as a medium to cast &cIce&f magiks.");
	}

	private static Map<UUID, Integer> comboTier = new HashMap<>();
	private static Map<UUID, Boolean> comboEndNaturally = new HashMap<>();

	private static Map<UUID, Boolean> isChanneled = new HashMap<>();
	private static Map<UUID, Boolean> isHyperCasting = new HashMap<>();
	
	//private static Map<UUID, Integer> combo2Tier = new HashMap<>();
	//private static Map<UUID, Boolean> combo2EndNaturally = new HashMap<>();
	
	//NOTE: Class is not fully implemented. Combo 2 when type == null arrows do not fly inwards and level. Spiral particles too high, and targeted combo arcs do not play. 
	
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
		
		if (MoaPlayerActions.shiftRightClickAir(e)) 
		{
			//implement
			return false;
		}
		
		//Main spells
		if (MoaPlayerActions.rightClickAir(e)) 
		{
			//Registration if the player just logged in and the map is not yet bound to the player.
			if (getIsChanneled().get(uuid) == null) getIsChanneled().put(uuid, false);
			
			Entity target = RayCastEntity.getNearest(p, 10);
			
			if (target == null) 
			{	
				//starts the combo and 10s cooldown
				if (!comboTier.containsKey(uuid)) 
				{
					if (comboEndNaturally.containsKey(uuid)) 
					{
						comboEndNaturally.remove(uuid);
					}
					
					MoaParticles.drawGlacioCastSigil(p);
					MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, SoundCategory.MASTER, 1, 1);
					Arrow arrow = p.launchProjectile(Arrow.class);
						arrow.setGravity(false);
						arrow.setShooter(p);
						arrow.setSilent(true);						
						arrow.setInvulnerable(true);
						arrow.setCritical(false);
						arrow.setPickupStatus(PickupStatus.DISALLOWED);
						arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 0), true);
						arrow.setColor(Color.TEAL);
						arrow.setPierceLevel(1);
						arrow.setDamage(4);
					
					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						if (!arrow.isDead() || arrow.isOnGround()) arrow.remove();
					}, 25);
					
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
					MoaParticles.drawGlacioCastSigil(p);
					
					// Get player's eye location and facing direction
					Location eyeLoc = p.getEyeLocation();
					Vector direction = eyeLoc.getDirection().normalize();

					// Get orthogonal direction (right vector) using cross product with up vector
					Vector right = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize();

					// Offsets
					Vector offset1 = right.clone().multiply(1.5).add(new Vector(0, 0.5, 0));  // right + up
					Vector offset2 = right.clone().multiply(-1.5).add(new Vector(0, 0.5, 0)); // left + up

					// Spawn positions
					Location spawnLoc1 = eyeLoc.clone().add(offset1);
					Location spawnLoc2 = eyeLoc.clone().add(offset2);

					// Arrow 1
					MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, SoundCategory.MASTER, 1, 1);
					Arrow a1 = (Arrow) p.getWorld().spawnEntity(spawnLoc1, EntityType.ARROW);
						a1.setVelocity(direction.clone().multiply(1.5));
						a1.setGravity(false);
						a1.setShooter(p);
						a1.setSilent(true);						
						a1.setInvulnerable(true);
						a1.setCritical(false);
						a1.setPickupStatus(PickupStatus.DISALLOWED);
						a1.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 0), true);
						a1.setColor(Color.TEAL);
						a1.setPierceLevel(2);
						a1.setDamage(5);
					
					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						if (!a1.isDead() || a1.isOnGround()) a1.remove();
					}, 25);
					
					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						// Arrow 2
						MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, SoundCategory.MASTER, 1, 1);
						Arrow a2 = (Arrow) p.getWorld().spawnEntity(spawnLoc2, EntityType.ARROW);
							a2.setVelocity(direction.clone().multiply(1.5));
							a1.setGravity(false);
							a1.setShooter(p);
							a1.setSilent(true);						
							a1.setInvulnerable(true);
							a1.setCritical(false);
							a1.setPickupStatus(PickupStatus.DISALLOWED);
							a1.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 0), true);
							a1.setColor(Color.TEAL);
							a1.setPierceLevel(2);
							a1.setDamage(5);
						
						Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
						{
							if (!a2.isDead() || a2.isOnGround()) a2.remove();
						}, 25);
						
					}, 3);
					
					comboTier.put(uuid, 2);
					return true;
				}
				
				if (comboTier.containsKey(uuid) && comboTier.get(uuid).equals(2)) 
				{
					MoaParticles.drawGlacioCastSigil(p);					
					MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_TRIDENT_THROW, SoundCategory.MASTER, 1, 1);
					Trident tArr = p.launchProjectile(Trident.class);
						tArr.getVelocity().multiply(1.2);
						tArr.setCritical(false);
						tArr.setDamage(10);
						tArr.setGravity(true);
						tArr.setInvulnerable(true);
						tArr.setPickupStatus(PickupStatus.DISALLOWED);
						tArr.setPierceLevel(3);
						tArr.setShooter(p);
						tArr.setSilent(true);
						
					Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
					{
						if (tArr.isOnGround()) 
						{
							MoaEffects.playSound(p, p.getLocation(), Sound.ITEM_TRIDENT_THUNDER, SoundCategory.MASTER, 1, 1);
							tArr.getLocation().getWorld().strikeLightningEffect(tArr.getLocation());
							WorldEvents.getNearbyEntities(tArr, 10, a->
							{
								MoaParticles.drawLine(tArr.getLocation(), a.getLocation(), 2, 0.5, Particle.SNOWFLAKE, null);
								Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
								{
									MoaEffects.add(a, PotionEffectType.SLOWNESS, 300, 99);
									MoaParticles.drawSpiralVortex(a.getLocation(), 3, 3, 0.1, Particle.SNOWFLAKE, null);
									MoaParticles.drawDisc(a.getLocation(), a.getWidth(), 1, 20, 0.5, Particle.SNOWFLAKE, null);
								}, 20);
							});
							tArr.remove();
						}
						tArr.remove();
					}, 25);
					
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
			
			//Target != Null
			if (!comboTier.containsKey(uuid)) 
			{
				if (comboEndNaturally.containsKey(uuid)) 
				{
					comboEndNaturally.remove(uuid);
				}
				
				MoaParticles.drawGlacioCastSigil(p);
				MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, SoundCategory.MASTER, 1, 1);

				MoaParticles.drawAngledArcLine(p.getLocation(), target.getLocation(), 2, 2, 0, 1, Particle.SNOWFLAKE, null);			
				
				Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
				{					
					((Damageable) target).damage(3);
				}, 20);
				
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
				MoaParticles.drawGlacioCastSigil(p);
				
				MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, SoundCategory.MASTER, 1, 1);
				MoaParticles.drawAngledArcLine(p.getLocation(), target.getLocation(), 2, 2, 180, 1, Particle.SNOWFLAKE, null);			
				Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
				{					
					((Damageable) target).damage(3);
				}, 20);
				
				comboTier.put(uuid, 2);
				return true;
			}
			
			if (comboTier.containsKey(uuid) && comboTier.get(uuid).equals(2)) 
			{
				MoaParticles.drawGlacioCastSigil(p);					
				MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, SoundCategory.MASTER, 1, 1);
				MoaParticles.drawLine(p.getLocation(), target.getLocation(), 2, 0.5, Particle.SNOWFLAKE, null);
				Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
				{
					MoaParticles.drawSpiralVortex(target.getLocation(), 3, 2, 0.1, Particle.SNOWFLAKE, null);
					((Damageable) target).damage(5);
				}, 20);
				
				comboTier.put(uuid, 3);
				return true;
			}
			
			if (comboTier.containsKey(uuid) && comboTier.get(uuid).equals(3)) 
			{
				
				MoaParticles.drawGlacioCastSigil(p);
				MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.MASTER, 1, 1);
				MoaParticles.drawSphere(target.getLocation(), target.getWidth()+1, 5, 10, Particle.SNOWFLAKE, null);
				
				Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
				{
					MoaEffects.playSound(p, p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, SoundCategory.MASTER, 1, 1);
					MoaParticles.drawSphere(target.getLocation(), target.getWidth()+1, 5, 10, Particle.BUBBLE_POP, null);					

					((Damageable) target).damage(8);
					MoaEffects.add(target, PotionEffectType.SLOWNESS, 200, 2);
					MoaEffects.add(target, PotionEffectType.MINING_FATIGUE, 200, 2);
					MoaEffects.add(target, PotionEffectType.HUNGER, 200, 2);
					MoaEffects.add(target, PotionEffectType.WEAKNESS, 200, 2);
					if (target.isDead()) 
					{
						MoaEffects.add(p, PotionEffectType.CONDUIT_POWER, 600, 2);	
					}
				}, 25);
				
				Bukkit.getScheduler().runTaskLater(MagiksOfArbania.instance, ()->
				{
					MoaEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
					MoaPrintUtils.PrintToActionBar(p, "Combo End");
				}, 5);
				comboTier.remove(uuid);
				comboEndNaturally.put(uuid, true);
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	public static Map<UUID, Boolean> getIsChanneled() 
	{
		return isChanneled;
	}

	public static void setIsChanneled(Map<UUID, Boolean> isChanneled) 
	{
		GlacioCatalyst.isChanneled = isChanneled;
	}

	public static Map<UUID, Boolean> getIsHyperCasting() 
	{
		return isHyperCasting;
	}

	public static void setIsHyperCasting(Map<UUID, Boolean> isHyperCasting) 
	{
		GlacioCatalyst.isHyperCasting = isHyperCasting;
	}
}
