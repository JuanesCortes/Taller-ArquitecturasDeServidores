
package edu.escuelaing.arem.tallerarqserv;

/**
 *
 * @author juan.cortes-p
 */
public class WebServices {
    
    @RequestMapping("/hello")
    public static String hellowWorld(){
        return "Helow World";
    }
    @RequestMapping("/status")
    public static String serverSatus(){
        return "Runnig";
    }
}
