package wildness.mod.data.zone;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xlatm
 * Date: 17.08.13
 * Time: 9:06
 * To change this template use File | Settings | File Templates.
 */
public class ClaimPlotClient {
    
    protected int x;
    protected int z;
    protected int dim;
    protected ClaimOwners ca; //Link to ClaimOwners
    
    protected String owner;
    protected List<String> peoples;
    protected String Messages;
    protected boolean trespass;
    protected boolean vandalism;
    
    
    
    
    //______________________________________________
    
    public ClaimPlotClient(ClaimOwners owner, int dim, int x, int z) {
    	this.ca = owner;
    	this.dim = dim;
    	this.x = x;
    	this.z = z;
    }
    
    
    

    /** Create Claim from CHUNK coordinates 
     * 
     * @param name
     * @param x
     * @param z
     */
    public ClaimPlotClient(int dim, String name, int x, int z, boolean tres, boolean vand) {
    	this.dim = dim;
        this.x = x;
        this.z = z;
        this.trespass = tres;
        this.vandalism = vand;
        this.owner = name;
        this.Messages = "as";
        this.peoples = new ArrayList<String>();
    }
    
    /** Create Claim from CHUNK coordinates and add user perms
     * 
     * @param dim
     * @param name
     * @param x
     * @param z
     * @param tres
     * @param vand
     * @param peoples
     */
    public ClaimPlotClient(int dim, String name, int x, int z, boolean tres, boolean vand, List<String> peoples) {
    	this.dim = dim;
        this.x = x;
        this.z = z;
        
        this.trespass = tres;
        this.vandalism = vand;
        this.Messages = "as";
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getZ() {
        return this.z;
    }
    
    public int getDim() {
        return this.dim;
    }
    
    public boolean checkInChunk(int dim, String name, int x, int z) {
    	return ((dim==this.dim)&&(x == this.x)&&(z == this.z)&&(name != this.owner));
    }
    
    public boolean checkInChunk(int dim,String name, double x, double y, double z) {
    	return checkInChunk(dim,name, MathHelper.floor_double(x)>>4,MathHelper.floor_double(z)>>4); 
    }
        
    /*public boolean checkInChunk(int dim,String name, int x, int y, int z) {
    	return checkInChunk(dim,name,MathHelper.floor_double(x>>4),MathHelper.floor_double(z>>4));
    }*/
    
	public boolean getTresPass() {
		return this.trespass;
	}
	
	public boolean getVandalism() {
		return this.vandalism;
	}
}
