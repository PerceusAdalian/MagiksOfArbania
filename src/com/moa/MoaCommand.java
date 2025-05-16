package com.moa;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.moa.objects.AbstractMoaObject;
import com.moa.objects.MoaItemRegistry;
import com.moa.utils.MoaPrintUtils;

public class MoaCommand implements CommandExecutor, TabCompleter
{
	
	public MoaCommand() 
	{
		Bukkit.getPluginCommand("moa").setTabCompleter(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) 
	{
		if (!(sender instanceof Player)) 
		{
			return false;
		}
		Player p = (Player) sender;
		
		if (!sender.isOp()) 
		{
			MoaPrintUtils.MoaFormatError(p, "&7Permission Denied");
			return false;
		}
		
		if (args.length == 0) 
		{
			MoaPrintUtils.MoaFormatError(p, "&7Invalid Argument(s)");
			return false;
		}
		
		if (args[0].equals("debug"))
		{
			if (MagiksOfArbania.debug == false) 
			{
				MagiksOfArbania.debug = true;
				MoaPrintUtils.MoaFormatDebug(p, "&7Console logging has been turned &a&lON");
				MoaPrintUtils.MoaConsoleDebug("&7Console logging has been turned &a&lON");
				return true;
			}
			MagiksOfArbania.debug = false;
			MoaPrintUtils.MoaFormatDebug(p, "&7Console logging has been turned &c&lOFF");
			MoaPrintUtils.MoaConsoleDebug("&7Console logging has been turned &c&lOFF");
			return true;
		}
		
		if (args[0].equals("generate") && MoaItemRegistry.itemRegistry.containsKey(args[1])) 
		{
				AbstractMoaObject obj = MoaItemRegistry.itemRegistry.get(args[1]);
				ItemStack stack = obj.bake();
				p.getInventory().addItem(stack);
				
				if (MagiksOfArbania.debug == true) 
				{
					String internalName = null;
		            if (stack.getItemMeta() != null) 
		            {
		                internalName = stack.getItemMeta().getPersistentDataContainer().get(AbstractMoaObject.moaObject, PersistentDataType.STRING);
		            }
		            
		            if (internalName == null) 
		            {
		                MoaPrintUtils.MoaConsoleError("Could not retrieve internal name from baked item.");
		                return true;
		            }
		            
		            AbstractMoaObject nexusObject = MoaItemRegistry.itemRegistry.get(internalName);
		            if (nexusObject == null) 
		            {
		                MoaPrintUtils.MoaConsoleError("Internal name exists, but item is not in the registry.");
		                return true;
		            }
		            
					MoaPrintUtils.MoaConsoleDebug("&7Generated &f'" + nexusObject.getName() + "' &a&oSuccessfully");
				}
				
				return true;
		}
		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) 
	{
		
		return switch(args.length) 
		{
			case 0 -> List.of("moa");
			case 1 -> List.of("debug", "generate");
			case 2 -> 
			{
				yield switch(args[0])
				{
					case "debug" -> List.of();
					case "generate" -> new ArrayList<>(MoaItemRegistry.itemRegistry.keySet());
//					case "Moamenu" -> List.of();
					default -> List.of();
				};
			}
			
			default -> List.of();
		};
	}
	
}
