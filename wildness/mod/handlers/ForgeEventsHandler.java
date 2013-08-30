package wildness.mod.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import wildness.mod.WildnessMod;
import wildness.mod.WildnessSettings;
import wildness.mod.core.WildnessTime;
import wildness.mod.core.events.ClientState;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created with IntelliJ IDEA.
 * User: xlatm
 * Date: 17.08.13
 * Time: 0:53
 * To change this template use File | Settings | File Templates.
 */
public class ForgeEventsHandler {

	
    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {

        if (event.entityLiving instanceof IMob){
            if (!(event.source.getDamageType().equals("player"))) {
                event.setCanceled(true);
            }
        }

        if(event.source.getSourceOfDamage() instanceof EntityArrow) {
            if(((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null) {
                if(event.entityLiving instanceof IMob)
                    if(!(((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer)) {
                    {
                        event.setCanceled(true);
                    }
                }
             }
         }

    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent var1)
    {
        ClientState.onRenderWorld(Minecraft.getMinecraft().renderViewEntity, (double) var1.partialTicks);
    }

    /*
     * Disable Sleep at the Bed / TANKE
     */
    @ForgeSubscribe
    public void onPlayerSleeping(PlayerSleepInBedEvent e)
    {
    	if (WildnessSettings.disableSleepInBed) {
    		e.setResult(Result.ALLOW);
        	e.result = EnumStatus.NOT_POSSIBLE_HERE;	
    	}
    }

    /*
     *  Disable BoneMeal
     */
    @ForgeSubscribe
    public void onPlayerUseBonemeal(BonemealEvent e)
    {
    	e.setCanceled(true);
    }
    
    @ForgeSubscribe
    public void onLoad(ChunkEvent.Load event)
    {
    	if (event.world.isRemote) {
    		// Chunk load on client
    		// Request chunk data from server
    		//System.out.println("Event Load on client " + event.getChunk().xPosition+","+event.getChunk().zPosition);
    		WildnessMod.network.requestClaimData(event.world.provider.dimensionId,event.getChunk().xPosition,event.getChunk().zPosition);
    	} else 
    	{
    		// Chunk load on server
    		//System.out.println("Event onLoad on server " + event.getChunk().toString());
    	}    
    }

    @ForgeSubscribe
    public void onUnload(ChunkEvent.Unload event)
    {
    	if (event.world.isRemote) {
    		System.out.println("Event Unload on client " + event.getChunk().xPosition+","+event.getChunk().zPosition);
    	} else 
    	{
    		//System.out.println("Event onUnload on server " + event.getChunk().toString());
    	}
    }
    
    
}