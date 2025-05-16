package com.moa.utils;

public enum MoaAbilityTypes 
{
	
	UTILITY("&r&b&oUtility"),
	OFFENSIVE("&r&c&lOffensive"),
	DEFENSIVE("&r&6&lDefensive"),
	SUPPORT("&r&e&oSupport");

	private final String abilityType; 
	
	MoaAbilityTypes(String string) 
	{
		this.abilityType = string;
	}

	public String getAbilityType() 
	{
		return this.abilityType;
	}
	
}

//Imported From Project Nexus