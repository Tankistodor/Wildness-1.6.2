package wildness.mod.handlers;

import java.util.EnumSet;

import wildness.mod.WildnessMod;
import wildness.mod.core.KeyBindings;
import wildness.mod.gui.GuiCalendar;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindingHandler extends KeyHandler {

	KeyBinding Key_Calendar = new KeyBinding("Key_Calendar", 49);
	
	public KeyBindingHandler()
    {
        super(KeyBindings.gatherKeyBindings(), KeyBindings.gatherIsRepeating());
    }
	
	
	
	public void keyDown(EnumSet<TickType> types, KeyBinding bind, boolean tickEnd, boolean isRepeat) {}

    public void keyUp(EnumSet<TickType> types, KeyBinding bind, boolean tickEnd)
    {
        if (tickEnd)
        {
            
            EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;

            if (bind.keyDescription == this.Key_Calendar.keyDescription && FMLClientHandler.instance().getClient().inGameHasFocus && FMLClientHandler.instance().getClient().currentScreen == null)
            {
                player.openGui(WildnessMod.instance, 27, player.worldObj, 0, 0, 0);
            }
            else if (bind.keyDescription == this.Key_Calendar.keyDescription && FMLClientHandler.instance().getClient().currentScreen instanceof GuiCalendar)
            {
                player.closeScreen();
            }
            
        }
    }

    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.WORLD, TickType.WORLDLOAD, TickType.CLIENT, TickType.PLAYER);
    }



	@Override
	public String getLabel() {
		return "Wildness KeyBindingHandler";
	}
}
