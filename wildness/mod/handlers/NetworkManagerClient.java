package wildness.mod.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import wildness.mod.WildnessMod;
import wildness.mod.core.WildnessTime;
import wildness.mod.data.zone.Claims;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NetworkManagerClient extends NetworkManager {

	@Override
	public void requestClaimData(int dim, int x, int z) {
		try {
		      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		      DataOutputStream os = new DataOutputStream(buffer);

		      os.writeByte(100);

		      os.writeInt(dim);
		      os.writeInt(x);
		      os.writeInt(z);

		      os.close();

		      Packet250CustomPayload packet = new Packet250CustomPayload();

		      packet.channel = "WildnessMod";
		      packet.isChunkDataPacket = false;
		      packet.data = buffer.toByteArray();
		      packet.length = buffer.size();

		      PacketDispatcher.sendPacketToServer(packet);
		      //System.out.println("Client try to send packet " +x +" " + z);
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		try
        {
            byte type1 = dis.readByte();
            EntityPlayer e = (EntityPlayer)player;
            World world = e.worldObj;
            if (type1 == 3) {
            	if (world.isRemote)
                {
                    long index3 = 0L;

                    try
                    {
                        index3 = dis.readLong();
                        WildnessTime.dayLength = dis.readInt();
                        WildnessTime.daysInYear = dis.readInt();
                        //System.out.println("Server get packet TIME packet");
                    }
                    catch (IOException var17)
                    {
                        ;
                    }
                }
            }
            
            if ((type1 == 101) && (world.isRemote)) {
				// Client received claim data state packet from server 
				try {
					int dim = dis.readInt();
					int x = dis.readInt();
					int z = dis.readInt();
					String owner = dis.readUTF();
					boolean trespass = dis.readBoolean();
					boolean vandalism = dis.readBoolean();
					WildnessMod.ZoneDB.clientClimeDB.put(dim+","+x+","+z, new Claims(dim,owner,x,z, trespass, vandalism));
				} catch (IOException var17) {
					;
				}
			}
            
            
        }
		catch (Exception var20)
        {
            ;
        }
	}
	
}
