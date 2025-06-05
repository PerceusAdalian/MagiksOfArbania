package com.moa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.moa.objects.CastHandler;
import com.moa.objects.MoaItemRegistry;
import com.moa.utils.MoaPrintUtils;
import com.moa.utils.menus.MoaGuiHandler;

public class MagiksOfArbania extends JavaPlugin
{
	public static MagiksOfArbania instance; 
	public static boolean debug;
	
	@Override
	public void onEnable() 
	{		
		instance = this;
		debug = false;
		
		this.getCommand("moa").setExecutor(new MoaCommand());;
		Bukkit.getPluginManager().registerEvents(new CastHandler(), instance);
		MoaGuiHandler.registerEventListener(instance);
		MoaItemRegistry.itemInit();
		MoaPrintUtils.MoaConsolePrint("Magiks Of Arbania -- &aOK");
	}
	
	@Override
	public void onDisable() 
	{
		MoaPrintUtils.MoaConsolePrint("Magiks Of Arbania -- &3Disabling...");
	}
}

/* 											//PROJECT NOTES\\
 * Write a means to streamline catalyst and spell creation. Integrate a means to toggle hypercasting and channeling.
 * Write utilities to check for combo counters, and map membership; getters and setters are necessary.
 */