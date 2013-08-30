package wildness.mod;

import java.io.File;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: kolesnikovak
 * Date: 19.08.13
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class CommonProxy implements IGuiHandler {

    public void registerRenderInformation()
    {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	TileEntity te = world.getBlockTileEntity(x, y, z);
    	switch (ID)
        {
            case 0:
                //return new ContainerLogPile(player.inventory, (TileEntityLogPile)te, world, x, y, z);

            case 1:
                //return new ContainerWorkbench(player.inventory, (TileEntityWorkbench)te, world, x, y, z);

            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 27:
            case 30:
            default:
                return null;
        }

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
    public File getMinecraftDir()
    {
        return ModLoader.getMinecraftServerInstance().getFile("");
    }
    
    public World getCurrentWorld()
    {
        return MinecraftServer.getServer().func_130014_f_();
    }
    
    public void registerKeys() {}

    public void registerKeyBindingHandler() {}

    public void registerHighlightHandler() {}

    public void registerSoundHandler() {}

	public void onClientLogin() {
	}
    
	public void setupGuiIngameForge() {}
}
