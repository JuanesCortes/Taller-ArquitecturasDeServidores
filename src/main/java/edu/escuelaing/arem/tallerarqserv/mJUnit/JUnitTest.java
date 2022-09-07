
package edu.escuelaing.arem.tallerarqserv.mJUnit;

/**
 *
 * @author juan.cortes-p
 */
public class JUnitTest {
    @Test
    public static void m1(){}
    @Test
    public static void m2(){}
    @Test
    public static void m3(){}
    
    @Test
    public static void m4()throws Exception{
        throw (new Exception("Error m4"));
    }
}
