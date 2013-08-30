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
    public static HashMap serverClimeUsers = new HashMap();
    
    public static String makeHash(int dim, int x, int z) {
    	return "" + dim + "," + x + "," + z;
    }
    
    
    public void createClaim(int dim, int x, int z, String owner, boolean trespass, boolean vandalism, List<String> peoples){
    	serverClimeDB.put(makeHash(dim,x,z), new Claims(dim,owner,x,z,trespass,vandalism, peoples));
    }

}
