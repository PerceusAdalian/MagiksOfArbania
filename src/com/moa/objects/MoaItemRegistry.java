package com.moa.objects;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moa.objects.instances.GlacioCatalyst;
import com.moa.objects.instances.InfernoCatalyst;

/**
 * @Documented MoaItemRegistry A class to handle Nexus Objects for generation, debugging, etc.
 * @Description 
 *  		Get each class's internal constructor and allow for reflection to access internal methods. 
 *  		Finally, put each object into itemRegistry via their instance and refer to the object's internal name.
 *  		If the object could not be accessed, or doesn't exist, print a stacktrace.
 */
public class MoaItemRegistry 
{
	public static final Map<String, AbstractMoaObject> itemRegistry = new HashMap<>();

    public static void itemInit() 
    {
        List<Class<? extends AbstractMoaObject>> itemClasses = Arrays.asList(
            //Spell Catalysts:
        	InfernoCatalyst.class,
        	GlacioCatalyst.class);
        
        for (Class<? extends AbstractMoaObject> clazz : itemClasses) 
        {
            try 
            {
                Constructor<? extends AbstractMoaObject> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                AbstractMoaObject instance = constructor.newInstance();
                itemRegistry.put(instance.getInternalName(), instance);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}
