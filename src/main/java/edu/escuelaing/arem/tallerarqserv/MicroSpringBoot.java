
package edu.escuelaing.arem.tallerarqserv;

import edu.escuelaing.arem.tallerarqserv.mJUnit.Test;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan.cortes-p
 */
public class MicroSpringBoot {
    
    static Map<String,Method> services = new HashMap<>();
    
    public static void main(String... args) throws ClassNotFoundException{
        String className = args[0];
        Class c = Class.forName(className);
        Method[] declaredMethods = c.getDeclaredMethods();
        
        int nServicios = 0;
        
        for (Method n : declaredMethods){
            if (n.isAnnotationPresent(RequestMapping.class)){
                String key = n.getDeclaredAnnotation(RequestMapping.class).value();
                System.out.println("Invoking: "+ n.getName() + "in class " + c.getName());
                services.put(key, n);
                nServicios = nServicios +1;
                
            }
        }
        System.out.println("Numero de servicios: "+ nServicios);
    }
    
    public static boolean existPath(String path){
        return services.containsKey(path);
    }
    
    public static Method getMethod (String path){
        return services.get(path);
    }
}
