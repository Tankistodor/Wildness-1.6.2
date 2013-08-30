package wildness.mod.core.events;

import api.player.server.ServerPlayerAPI;
import api.player.server.ServerPlayerBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet43Experience;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreObjectiveCriteria;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import wildness.mod.WildnessMod;
import wildness.mod.WildnessSettings;
import wildness.mod.data.zone.Claims;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static net.minecraft.potion.Potion.*;

/**
 * Created with IntelliJ IDEA.
 * User: kolesnikovak
 * Date: 16.08.13
 * Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
public class WServerPlayerBase extends ServerPlayerBase {

    public WServerPlayerBase(ServerPlayerAPI var1) {
        super(var1);
    }

    @Override
    public boolean canHarvestBlock(Block var1) {
        if (var1.blockMaterial == Material.sand) return false;
        return false;
        //return super.canHarvestBlock(var1);    //To change body of overridden methods use File | Settings | File Templates.
    }

    /*
    public EnumStatus SleepInBedAt(int par1, int par2, int par3) {
        //player.setInvisible(true);
        return EnumStatus.TOO_FAR_AWAY;
    }*/
    
    @Override
    public void afterFall(float var1) {
        super.afterFall(var1);
        if (var1 > WildnessSettings.playerFailHeight) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, WildnessMod.slowTime,5,true));
            //player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,WildnessMod.slowTime,5,true));
        }
    }
    
    
    public void beforeMoveEntity(double x, double y, double z) {

        if ((x != 0)||(y != 0)||(z != 0)) {

            boolean flg = false;
            //Iterator it=WildnessMod.ZoneDB.serverClimeDB.entrySet().iterator();
            //while(it.hasNext())
            //{
            	//Claims c = (Claims)it.next();
            
            for (Object ss : WildnessMod.ZoneDB.serverClimeDB.keySet()) {
        		Claims c = (Claims) WildnessMod.ZoneDB.serverClimeDB.get(ss);
            	
            	if (c.checkInChunk(player.worldObj.provider.dimensionId,player.username,x+player.posX, y, z+player.posZ))
                    flg = true;
            	}
            //}
            
            if (flg) {
                player.setPosition(player.lastTickPosX, player.lastTickPosX, player.lastTickPosX);
                super.beforeMoveEntity(0, 0, 0);
            }
        }
        super.beforeMoveEntity(x, y, z);
    }

}
