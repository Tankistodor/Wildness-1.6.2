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
public class ClaimPlot {
    
    protected int x;
    protected int z;
    protected int dim;
    protected ClaimOwners ca; //Link to ClaimOwners
    
    /**
     * CHUNK COORD
     * @param owner
     * @param dim
     * @param x
     * @param z
     */
    public ClaimPlot(ClaimOwners owner, int dim, int x, int z) {
    	this.ca = owner;
    	this.dim = dim;
    	this.x = x;
    	this.z = z;
    }
    
    
    /**
     * CHUNK Double Coordinates
     * @param owner
     * @param dim
     * @param x
     * @param z
     */
    public ClaimPlot(ClaimOwners owner, int dim, double x, double z) {
    	this(owner,dim,MathHelper.floor_double(x), MathHelper.floor_double(z));
    	System.out.println("Claims chunk coord double X: "+ x + " Z:" + z);
    }
   
    
    /**
     * CHUNK Block Coordinates
     * @param owner
     * @param dim
     * @param x
     * @param z
     */
    public ClaimPlot(ClaimOwners owner, int dim, int x, int z, int blockID) {
    	this(owner, dim, x>>4, z>>4);  
    	System.out.println("Claims(block) chunk coord floor X: "+ (x>>4) + " Z:" + (z>>4));
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
    
    // CLIENTSIDE
    public boolean checkInChunk1(int dim, String name, int x, int z) {
    	return ((dim==this.dim)&&(x == this.x)&&(z == this.z)&&(name != this.ca.owner));
    }
    
    public boolean checkInChunk(int dim,String name, double x, double y, double z) {
    	return checkInChunk1(dim,name, MathHelper.floor_double(x)>>4,MathHelper.floor_double(z)>>4); 
    }
        
    public boolean checkInChunk(int dim,String name, int x, int y, int z) {
    	return checkInChunk1(dim,name,MathHelper.floor_double(x>>4),MathHelper.floor_double(z>>4));
    }

	public String getOwner() {
		return this.ca.owner;
	}
    
	public boolean getTresPass() {
		return this.ca.trespass;
	}
	
	public boolean getVandalism() {
		return this.ca.vandalism;
	}
	
	public void addPersone(String name) {
		this.ca.peoples.add(name);
	}
	
	public void removePerson(String name) {
		this.ca.peoples.remove(name);
	}
	
	public boolean isTresPass(String name) {
		return ((this.ca.peoples.contains(name)&&this.ca.trespass)||(name.equals(this.ca.owner)));
	}
	
	public boolean isVandalism(String name) {
		return ((this.ca.peoples.contains(name)&&this.ca.vandalism)||(name.equals(this.ca.owner)));
	}
}
