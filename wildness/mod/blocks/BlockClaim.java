package wildness.mod.blocks;

import wildness.mod.WildnessMod;
import wildness.mod.data.zone.ClaimOwners;
import wildness.mod.tileentity.TEClaim;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: xlatm
 * Date: 17.08.13
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
public class BlockClaim extends Block {	
	
	Icon icons;

    public BlockClaim(int id, int par2){
        super(id, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(89.3F);
        this.setResistance(89.5F);
        this.setStepSound(soundMetalFootstep);
    }
    
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is)
    {
    	//System.out.println("onBlockPlacedBy Block coord: X: "+ i + " Y:" + k);
    	if (entityliving instanceof EntityPlayer) {
    		EntityPlayer p = (EntityPlayer) entityliving;
    		if (world.isRemote)
            {
    			//WildnessMod.ZoneDB.clientClimeDB.put(world.provider.dimensionId+","+i+","+k,new Claims(world.provider.dimensionId,p.username,i,j,k,false,false));
            } else {
            	//ClaimOwners ca = new addClaimOwner(p.username,"");
            	WildnessMod.ZoneDB.addPlotToClaimBlockCoord(WildnessMod.ZoneDB.addClaimOwner(p.username,""),world.provider.dimensionId, i, j);
            	//serverClimeDB.put(world.provider.dimensionId+","+i+","+k,new Claims(world.provider.dimensionId,p.username,i,j,k,false,false));
            }
    	}
    	
        if (world.isRemote)
        {
            int metadata = world.getBlockMetadata(i, j, k);
            System.out.println("Meta=" + this.getUnlocalizedName() + ":" + metadata);
        }
    }
    
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        this.onBlockPlacedBy(world, i, j, k, entityliving, (ItemStack)null);
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int i, int j)
    {
        return this.icons;
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister registerer)
    {
        this.icons = registerer.registerIcon("wildness:Claim");
        Block.planks.registerIcons(registerer);
    }

    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new TEClaim();
    }
    
}
