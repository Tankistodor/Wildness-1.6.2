package wildness.mod.core.events;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import wildness.mod.WildnessMod;
import wildness.mod.data.zone.ClaimPlotClient;

/**
 * Created with IntelliJ IDEA.
 * User: xlatm
 * Date: 17.08.13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class ClientState {

    private static World _world = null;

    /*
    @SideOnly(Side.CLIENT)
    private static IntBuffer _chunkDGListBuffer = null;
    @SideOnly(Side.CLIENT)
    private static Set _chunkDGRequests = new HashSet();
    */

    @SideOnly(Side.CLIENT)
    public static void onRenderWorld(Entity var0, double var1) {
        //if (_world != null) {
            double x = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * var1;
            double y = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * var1;
            double z = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * var1;


            GL11.glPushMatrix();
            GL11.glTranslated(-x, -y, -z);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            //GL11.glDisable(GL11.GL_DEPTH_TEST);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(0.9f, 0.5f, 0.2f,0.2f);
            GL11.glLineWidth(2f);
            

            //addVertex3d(51,9);
            for (Object ss : WildnessMod.ZoneDB.clientClimeDB.keySet()) {
        		ClaimPlotClient c = (ClaimPlotClient) WildnessMod.ZoneDB.clientClimeDB.get(ss);
            	addVertex3d(c.getX(), c.getZ(),y);
            }

            //GL11.glEnable(GL11.GL_DEPTH_TEST);

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();

        //}

    }

    
    public static void addVertex3d(int x, int z,double h) {
    	
    	double cHeight = 2;
    	double lowH = h-cHeight-cHeight;
    	double hiH = h+cHeight;
    	
    	lowH = h-1;
    	hiH = h;
    	
    	double l = 0.1d;
    	
    	//QUAD []
    	/*
    	GL11.glBegin(GL11.GL_QUAD_STRIP);
        GL11.glVertex3d(x*16+l,lowH,z*16+l);
        GL11.glVertex3d(x*16+l,hiH,z*16+l);

        GL11.glVertex3d((x+1)*16-l,lowH,z*16+l);
        GL11.glVertex3d((x+1)*16-l,hiH,z*16+l);

        GL11.glVertex3d((x+1)*16-l,lowH,(z+1)*16-l);
        GL11.glVertex3d((x+1)*16-l,hiH,(z+1)*16-l);

        GL11.glVertex3d(x*16+l,lowH,(z+1)*16-l);
        GL11.glVertex3d(x*16+l,hiH,(z+1)*16-l);
        
        GL11.glVertex3d(x*16+l,lowH,z*16+l);
        GL11.glVertex3d(x*16+l,hiH,z*16+l);
        GL11.glEnd();
    	*/
    	
    	GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex3d(x*16+l,lowH,z*16+l);
        GL11.glVertex3d((x+1)*16-l,lowH,z*16+l);
        GL11.glVertex3d((x+1)*16-l-8,hiH,z*16+l);
        GL11.glEnd();

    	GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex3d((x*16)+l,lowH,(z+1)*16-l);
        GL11.glVertex3d((x+1)*16-l,lowH,(z+1)*16-l);
        GL11.glVertex3d((x+1)*16-l-8,hiH,(z+1)*16-l);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex3d((x*16)+l,lowH,(z)*16+l);
        GL11.glVertex3d((x)*16+l,lowH,(z+1)*16-l);
        GL11.glVertex3d((x)*16+l,hiH,(z+1)*16-l-8);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex3d((x+1)*16-l,lowH,(z)*16+l);
        GL11.glVertex3d((x+1)*16-l,lowH,(z+1)*16-l);
        GL11.glVertex3d((x+1)*16-l,hiH,(z+1)*16-l-8);
        GL11.glEnd();
        
    }
    
    @SideOnly(Side.CLIENT)
    public static boolean hasWorldChanged(World var0)
    {
        return _world != var0;
    }

    @SideOnly(Side.CLIENT)
    public static void onWorldChanged(World var0)
    {
        _world = var0;
        //CustomOreGenBase.log.finer("Client world changed to " + (_world == null ? null : _world.getWorldInfo().getWorldName()));
        //clearDebuggingGeometry();
    }

    @SideOnly(Side.CLIENT)
    public static void setWorld(World w)
    {
        _world = w;
    }

}
