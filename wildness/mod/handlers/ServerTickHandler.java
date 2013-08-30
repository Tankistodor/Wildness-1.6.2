package wildness.mod.handlers;

import java.util.EnumSet;

import wildness.mod.core.WildnessTime;

import net.minecraft.world.WorldServer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.contains(TickType.WORLD))
        {
            WorldServer world1 = (WorldServer)tickData[0];
            WildnessTime.UpdateTime(world1);
        }
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD, TickType.WORLDLOAD, TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "Wildness Servet Tick Handler";
	}

}
