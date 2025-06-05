package com.moa.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.moa.MagiksOfArbania;
import com.moa.utils.ElementType;
import com.moa.utils.MoaPrintUtils;

public abstract class AbstractMoaObject 
{
	public static final NamespacedKey moaObject = new NamespacedKey(MagiksOfArbania.instance, "moa_object");
	public static final NamespacedKey moaCatalyst = new NamespacedKey(MagiksOfArbania.instance, "moa_magic_catalyst");
	private String name;
	protected String internalName;
	private String[] itemDescription;
	private Material material;
	private ElementType elementType;
	private boolean isCatalyst = false;

	public AbstractMoaObject(String name, String internalName, Material material, String...itemDescription) 
	{
		this.name = name;
		this.internalName = internalName;
		this.material = material;
		this.itemDescription = itemDescription;
	}
	
	public AbstractMoaObject(String internalName, Material material, boolean isCatalyst, ElementType elementType, String...itemDescription)
	{
		this.internalName = internalName;
		this.material = material;
		this.itemDescription = itemDescription;
		this.elementType = elementType;
		this.isCatalyst = isCatalyst;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getInternalName() 
	{
		return internalName;
	}
	public void setInternalName(String internalName) 
	{
		this.internalName = internalName;
	}
	public String[] getItemDescription() 
	{
		return itemDescription;
	}
	public void setItemDescription(String[] itemDescription) 
	{
		this.itemDescription = itemDescription;
	}
	public Material getMaterial() 
	{
		return material;
	}
	public void setMaterial(Material material) 
	{
		this.material = material;
	}
	public boolean isCatalyst() 
	{
		return isCatalyst;
	}
	public ElementType getElementType() 
	{
		return elementType;
	}
	public static NamespacedKey getMoaobject() 
	{
		return moaObject;
	}
	
	public abstract boolean Cast(PlayerInteractEvent e);
	
	public ItemStack bake() 
	{
		ItemStack stack = new ItemStack(material, 1);
		ItemMeta meta = stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		meta.setEnchantmentGlintOverride(true);
		lore.add("\n");
		
		if (isCatalyst()) 
		{
			
			char color = switch (elementType) 
			{
				case NONE -> color = 'f';
				case INFERNO -> color = 'c';
				case GLACIO -> color = 'b';
				case GEO -> color = '6';
				case AERO -> color = 'd';
				case CELESTIO -> color = 'e';
				case MORTIO -> color = '4';
				case COSMO -> color = '3';
				case ARCANO -> color = '9';
			};
			
			meta.setDisplayName(MoaPrintUtils.ColorParser("&r&"+color+"&ko&r&f Catalyst of &l"+elementType.getElement()+" &r&"+color+"&ko"));	
			meta.getPersistentDataContainer().set(moaCatalyst, PersistentDataType.STRING, internalName.toString());
		} 
		else 
		{			
			meta.setDisplayName(MoaPrintUtils.ColorParser("&r&f "+name));	
		}
		
		for (String line : itemDescription) 
		{
			lore.add(MoaPrintUtils.ColorParser("&r&f" + line) + "\n");
		}
		lore.add("\n");
		
		if (isCatalyst()) 
		{
			lore.add(MoaPrintUtils.ColorParser("&r&fUse &b&o/moa spellhelp&r&f for more details."));
		}
		
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(moaObject, PersistentDataType.STRING, internalName.toString());
		stack.setItemMeta(meta);
		
		return stack;
	}
}

//Add relics branch later <3
