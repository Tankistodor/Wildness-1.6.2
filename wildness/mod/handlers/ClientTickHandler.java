package wildness.mod.handlers;

import java.util.EnumSet;

import wildness.mod.core.WildnessTime;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler  implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.contains(TickType.PLAYER))
        {
            EntityPlayer player = (EntityPlayer)tickData[0];
            World world = player.worldObj;
            WildnessTime.UpdateTime(world);
        }
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "Wildness Client Tick Handler";
	}

}
