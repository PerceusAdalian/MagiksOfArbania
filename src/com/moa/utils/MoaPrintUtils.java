package com.moa.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class MoaPrintUtils
{
	public static String ColorParser(String msg) 
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static void Print(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser(msg));
	}
	
	public static void Print(Player player, String msg) 
	{
		player.getPlayer().sendMessage(ColorParser(msg));
	}
	
	public static void PrintToActionBar(Player player, String msg) 
	{
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorParser(msg)));
	}
	
	public static void MoaFormatPrint(Player player, String msg) 
	{
		Print(player, "&f<&eμ&r&f> "+msg+" &r&f/&e$&f//");
	}
	
	public static void MoaFormatError(Player player, String msg) 
	{
		Print(player, "&f<&cμ&r&f> "+msg+" &r&f/&c!&f//");
	}
	
	public static void MoaFormatDebug(Player player, String msg) 
	{
		Print(player.getPlayer(), "&f<&bμ&r&f> "+msg+ " &r&f/&b?&f//");
	}
	
	public static void MoaConsolePrint(String msg) 
	{
		Print("&f<&eμ&r&f> "+msg+" &r&f/&e$&f//");
	}
	
	public static void MoaConsoleError(String msg) 
	{
		Print("&f<&cμ&r&f> "+msg+" &r&f/&c!&f//");
	}
	
	public static void MoaConsoleDebug(String msg) 
	{
		Print("&f<&bμ&r&f> "+msg+ " &r&f/&b?&f//");
	}
	
	public static String assignAbilityType(MoaAbilityTypes type, MoaAbilityTypes type2) 
	{
		return ColorParser("&r&f&lAbility Type&r&f: {"+ type.getAbilityType() + "&r&f | " + type2.getAbilityType() + "&r&f}");
	}
	public static String assignAbilityType(MoaAbilityTypes type) 
	{
		return ColorParser("&r&f&lAbility Type&r&f: {"+type.getAbilityType()+"&r&f}");
	}
}

//Imported and Modified From Project Nexus