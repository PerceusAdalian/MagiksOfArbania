package com.moa.utils;

public enum ElementType 
{
	NONE(null),
	GLACIO("Glacio"),
	INFERNO("Inferno"),
	GEO("Geo"),
	AERO("Aero"),
	CELESTIO("Celestio"),
	MORTIO("Mortio"),
	COSMO("Cosmo"),
	ARCANO("Arcano");

	private final String element;	
	
	ElementType(String element) 
	{
		this.element = element;
	}

	public String getElement() 
	{
		return element;
	}
}

//	TEMPIRA("Tempira"),
//	YUTTRINICA("Yuttrinica"),
//	VECTUS("Vectus"),
//	XENOLUS("")