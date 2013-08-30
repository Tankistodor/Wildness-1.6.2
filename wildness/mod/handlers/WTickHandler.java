package wildness.mod.handlers;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import wildness.mod.core.events.ClientState;

import java.util.EnumSet;

/**
 * Created with IntelliJ IDEA.
 * User: xlatm
 * Date: 17.08.13
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public class WTickHandler implements ITickHandler {
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EnumSet<TickType> ticks() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getLabel() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SideOnly(Side.CLIENT)
    private void onClientTick()
    {
        Minecraft var1 = Minecraft.getMinecraft();
        ClientState.setWorld(var1.theWorld);
        if (var1.theWorld != null && ClientState.hasWorldChanged(var1.theWorld))
        {
            ClientState.onWorldChanged(var1.theWorld);
        }
    }

}
