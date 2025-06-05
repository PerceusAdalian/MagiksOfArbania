package com.moa.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
public @interface Nullable
{
	
}

//Imported From Project Nexus