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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class NetworkManager implements IPacketHandler {

	public static final int updatePeriod = 2;
	private static int maxNetworkedFieldsToUpdate = 4000;
	private static final int maxPacketDataLength = 32766;

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		try {
			byte type1 = dis.readByte();
			EntityPlayer e = (EntityPlayer) player;
			World world = e.worldObj;

			if ((type1 == 100) && (!world.isRemote)) {
				// Server revice packet from client (req)
				try {
					int dim = dis.readInt();
					int x = dis.readInt();
					int z = dis.readInt();
					
					if (WildnessMod.ZoneDB.serverClimeDB.containsKey(dim+","+x+","+z)) {
						Claims c = (Claims) WildnessMod.ZoneDB.serverClimeDB.get(dim+","+x+","+z);
						sendClaimState(player, dim, x,z,c.getOwner());
						}
				} catch (IOException var17) {
					;
				}
			}
		} catch (Exception var20) {
			;
		}

	}

	
	public void sendClaimState(Player player, int dim, int x, int z, String s) {
		try {
		      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		      DataOutputStream os = new DataOutputStream(buffer);

		      os.writeByte(101);

		      os.writeInt(dim);
		      os.writeInt(x);
		      os.writeInt(z);
		      os.writeUTF(s);

		      os.close();

		      Packet250CustomPayload packet = new Packet250CustomPayload();

		      packet.channel = "WildnessMod";
		      packet.isChunkDataPacket = false;
		      packet.data = buffer.toByteArray();
		      packet.length = buffer.size();

		      PacketDispatcher.sendPacketToPlayer(packet, player);
		      //System.out.println("Client try to send packet " +x +" " + z);
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }		
	}
	
	public void requestClaimData(int i, int j, int k) {
	}

}
