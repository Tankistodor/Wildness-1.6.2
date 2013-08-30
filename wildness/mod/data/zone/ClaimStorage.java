package wildness.mod.data.zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xlatm
 * Date: 17.08.13
 * Time: 9:29
 * To change this template use File | Settings | File Templates.
 */
public class ClaimStorage {

    //public static List<Claims> climeDB = new ArrayList();
    
    public static HashMap clientClimeDB = new HashMap();
    public static HashMap serverClimeDB = new HashMap();
    
    public static String makeHash(int x, int y) {
    	return x+","+y;
    }
    

}
