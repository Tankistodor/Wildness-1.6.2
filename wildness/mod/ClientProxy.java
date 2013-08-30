package wildness.mod;

import java.io.File;

import wildness.mod.core.KeyBindings;
import wildness.mod.gui.GuiCalendar;
import wildness.mod.handlers.KeyBindingHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created with IntelliJ IDEA.
 * User: kolesnikovak
 * Date: 19.08.13
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
public class ClientProxy extends CommonProxy {
    @SideOnly(Side.CLIENT)
    public void registerRenderInformation()
    {

    }
    
    
    @SideOnly(Side.CLIENT)
    public void setupGuiIngameForge()
    {
        //GuiIngameForge.renderHealth = false;
        //GuiIngameForge.renderArmor = false;
        //GuiIngameForge.renderFood = false;
    }
    
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te;

        try
        {
            te = world.getBlockTileEntity(x, y, z);
        }
        catch (Exception var9)
        {
            te = null;
        }

        switch (ID)
        {
            case 0:
            case 1:
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
            case 30:
            default:
                return null;
            case 27:
                return new GuiCalendar(player, world, x, y, z);

        }
    }
    
    public File getMinecraftDir()
    {
        return ModLoader.getMinecraftInstance().mcDataDir;
    }
    
    public World getCurrentWorld()
    {
        return ModLoader.getMinecraftInstance().theWorld;
    }
    
    public void registerKeys()
    {
        KeyBindings.addKeyBinding("Key_Calendar", 49);
        KeyBindings.addIsRepeating(false);
    }

    public void registerKeyBindingHandler()
    {
        KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }

    public void registerHighlightHandler()
    {
    	/*
        MinecraftForge.EVENT_BUS.register(new ChiselHighlightHandler());
        MinecraftForge.EVENT_BUS.register(new FarmlandHighlightHandler());
        MinecraftForge.EVENT_BUS.register(new PlankHighlightHandler());
        */
    }

    public void registerSoundHandler()
    {
        //MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }
    
    public void onClientLogin() {}
}
