package wildness.mod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TEClaim extends TileEntity implements IInventory {
	
	// 0 - TRESSPASS
	// 1 - VANDAL
	// 2-10 - Cards
	private ItemStack[] dispenserContents = new ItemStack[11];
	protected String customName;
	protected String owner;

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 11;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.customName : "container.dispenser";
    }

    public void setCustomName(String par1Str)
    {
        this.customName = par1Str;
    }

    @Override
    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.customName != null;
    }

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
