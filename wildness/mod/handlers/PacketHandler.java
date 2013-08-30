package wildness.mod.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import wildness.mod.WildnessMod;
import wildness.mod.WildnessSettings;
import wildness.mod.core.WildnessTime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IConnectionHandler{

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		//
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        DataOutputStream dos = new DataOutputStream(bos);
		
		EntityPlayerMP player1 = (EntityPlayerMP)player;
		World world = player1.worldObj;
		
		
		 if (!world.isRemote)
	        {
	            try
	            {
	                dos.writeByte(3);
	                dos.writeLong(world.getSeed());
	                dos.writeInt(WildnessTime.dayLength);
	                dos.writeInt(WildnessTime.daysInYear);
	            }
	            catch (IOException var10)
	            {
	                var10.printStackTrace();
	            }

	            Packet250CustomPayload pkt = new Packet250CustomPayload();
	            pkt.channel = "WildnessMod";
	            pkt.data = bos.toByteArray();
	            pkt.length = bos.size();
	            pkt.isChunkDataPacket = false;
	            ((NetServerHandler)netHandler).sendPacketToPlayer(pkt);
	        }
		
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
		WildnessMod.proxy.onClientLogin();		
	}
	
}
