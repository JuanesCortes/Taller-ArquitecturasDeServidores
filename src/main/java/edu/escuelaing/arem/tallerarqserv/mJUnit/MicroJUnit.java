
package edu.escuelaing.arem.tallerarqserv.mJUnit;

import java.lang.reflect.Method;

/**
 *
 * @author juan.cortes-p
 */
public class MicroJUnit {
    
    
    public static void main(String... args) throws ClassNotFoundException{
        String className = args[0];
        Class c = Class.forName(className);
        Method[] declaredMethods = c.getDeclaredMethods();
        
        int passed = 0;
        int failed = 0;
        
        for (Method n : declaredMethods){
            if (n.isAnnotationPresent(Test.class)){
                try {
                    n.invoke(null);
                    System.out.println("Invoking: "+ n.getName() + "in class " + c.getName());
                    passed = passed +1;
                }catch(Exception e){
                    failed = failed + 1;
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Passed: " + passed + ", failed: "+ failed);
    }
}
