package wildness.mod.core.events;

import java.util.Iterator;

import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.MathHelper;
import wildness.mod.WildnessMod;
import wildness.mod.WildnessSettings;
import wildness.mod.data.zone.Claims;

/**
 * Created with IntelliJ IDEA.
 * User: kolesnikovak
 * Date: 16.08.13
 * Time: 9:29
 * To change this template use File | Settings | File Templates.
 */
public class WClientPlayerBase  extends ClientPlayerBase {
    public WClientPlayerBase(ClientPlayerAPI var1) {
        super(var1);
    }

    /*public void jump()
    {
     //player.setHealth(0.5f);
    }*/

    /*
    @Override
    public boolean canHarvestBlock(Block var1) {
        return false;
        //return super.canHarvestBlock(var1);    //To change body of overridden methods use File | Settings | File Templates.
    } */

    /*
    @Override
    public EnumStatus sleepInBedAt(int par1, int par2, int par3) {
        //player.setInvisible(true);
        return EnumStatus.TOO_FAR_AWAY;
    }*/

    @Override
    public void afterFall(float var1) {
        if (var1 > WildnessSettings.playerFailHeight) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, WildnessMod.slowTime,5,true));
            //player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,WildnessMod.slowTime,5,true));
        }
    }
    
    
    /*
    public void moveEntityWithHeading(float var1, float var2){
    	player.sendChatToPlayer(new ChatMessageComponent().func_111079_a("v1:"+var1+" v2: "+var2));
    	super.moveEntityWithHeading(var1, var2);
    }*/
    
    public void moveEntity(double x, double y, double z)
    {
    	double xx = x;
    	double yy = y;
    	double zz = z;
    	
    	for (Object ss : WildnessMod.ZoneDB.clientClimeDB.keySet()) {
    		Claims c = (Claims) WildnessMod.ZoneDB.clientClimeDB.get(ss);
    		if (c.checkInChunk(player.worldObj.provider.dimensionId,player.username,x+player.posX, y, z+player.posZ)) {
        		xx = 0; zz = 0;
        	}
    	}
    	/*Iterator it=WildnessMod.ZoneDB.clientClimeDB.entrySet().iterator();
        while(it.hasNext())
        {
        	Claims c = (Claims)it.next();
        	if (c.checkInChunk(player.worldObj.provider.dimensionId,player.username,x+player.posX, y, z+player.posZ)) {
        		xx = 0; zz = 0;
        	}

        }*/
    	super.moveEntity(xx,yy,zz);
    }
    
}
