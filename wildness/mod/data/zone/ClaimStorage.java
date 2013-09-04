package wildness.mod.data.zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.util.MathHelper;

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
    public static HashMap serverUpdateState = new HashMap();
    
    
    public static String makeHash(int dim, int x, int z) {
    	return "" + dim + "," + x + "," + z;
    }
    
    
    public void createClaim(int dim, int x, int z, String owner, boolean trespass, boolean vandalism, List<String> peoples){
    	//serverClimeDB.put(makeHash(dim,x,z), new Claims(dim,owner,x,z,trespass,vandalism, peoples));
    }

    
    public static ClaimOwners addClaimOwner(String owner, String messages) {
    	return addClaimOwner(owner, messages,new ArrayList<String>(),false,false);
    }
    
    public static ClaimOwners addClaimOwner(String owner, String messages, List<String> peoples, boolean trespass, boolean vandalism) {
    	ClaimOwners ca;
    	if (serverClimeUsers.containsKey(owner)) {
    		ca = (ClaimOwners) serverClimeUsers.get(owner);
    	} else {
    		ca = new ClaimOwners(owner, peoples, "message", trespass, vandalism);
    		serverClimeUsers.put(owner, ca);
    		System.out.println("add new PlotOwner on serverSide");
    	}
		return ca;
    }
    
    public static void addPlotToClaim(ClaimOwners ca, int dim, int x, int z) {
    	if (serverClimeUsers.containsKey(ca.owner)) {
    		// check nearest plot
    		if (
    				(isPlotConflict(dim,x+1,z-1,ca.owner))||
    				(isPlotConflict(dim,x+1,z,ca.owner))||
    				(isPlotConflict(dim,x+1,z+1,ca.owner))||
    				
    				(isPlotConflict(dim,x-1,z-1,ca.owner))||
    				(isPlotConflict(dim,x-1,z,ca.owner))||
    				(isPlotConflict(dim,x-1,z+1,ca.owner))||
    				
    				(isPlotConflict(dim,x,z-1,ca.owner))||
    				(isPlotConflict(dim,x,z,ca.owner))||
    				(isPlotConflict(dim,x,z+1,ca.owner))
    			)
     		{
    			// Plot Exists here or neares				
    		} else {
    			serverClimeDB.put(makeHash(dim, x, z), new ClaimPlot(ca,dim,x,z));
    			serverUpdateState.put(makeHash(dim, x, z), "");    	
    		}
    	}
    }
    
    public static void addPlotToClaimBlockCoord(ClaimOwners ca, int dim, int x, int z) {
    	addPlotToClaim(ca,dim,x>>4,z>>4);
    }
    
    public static void addPlotToClaimDoubleCoord(ClaimOwners ca, int dim, double x, double z) {
    	addPlotToClaim(ca,dim,MathHelper.floor_double(x), MathHelper.floor_double(z));
    }
    
    public static boolean isPlotConflict(int dim, int x, int z, String name) {
    	String hash1 = makeHash(dim,x,z);
    	if (serverClimeDB.containsKey(hash1)) {
    		ClaimPlot ca = (ClaimPlot) serverClimeDB.get(hash1);
    		if (ca.getOwner() == name) {
    			return false; 
    		} else {
    			return true;
    		}
    	}
    	return false;
    }
    
}
