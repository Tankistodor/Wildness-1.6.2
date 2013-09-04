package wildness.mod;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import api.player.client.ClientPlayerAPI;
import api.player.server.ServerPlayerAPI;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import wildness.mod.blocks.WildnessBlocks;
import wildness.mod.core.events.ClientState;
import wildness.mod.core.events.WClientPlayerBase;
import wildness.mod.core.events.WServerPlayerBase;
import wildness.mod.data.zone.ClaimOwners;
import wildness.mod.data.zone.ClaimStorage;
import wildness.mod.data.zone.ClaimPlot;
import wildness.mod.handlers.ClientTickHandler;
import wildness.mod.handlers.ForgeEventsHandler;
import wildness.mod.handlers.NetworkManager;
import wildness.mod.handlers.NetworkManagerClient;
import wildness.mod.handlers.PacketHandler;
import wildness.mod.handlers.ServerTickHandler;
import wildness.mod.items.WildnessItems;


@Mod(
		modid = "WildnessMod",
		name = "WildnessMod",
		version = "1.0"
	)
/*@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = true,
		packetHandler = PacketHandler.class
	)*/


@NetworkMod(
		clientSideRequired=true,
		clientPacketHandlerSpec=@NetworkMod.SidedPacketHandler(
				channels={"WildnessMod"},
				packetHandler=NetworkManagerClient.class
				),
		serverPacketHandlerSpec=@NetworkMod.SidedPacketHandler(
				channels={"WildnessMod"},
				packetHandler=NetworkManager.class)
		)


public class WildnessMod {

    public static int slowTime = 20*60*6;

    @Instance("WildnessMod")
    public static WildnessMod instance;
    
    @SidedProxy(
            clientSide = "wildness.mod.ClientProxy",
            serverSide = "wildness.mod.CommonProxy"
        )
    public static CommonProxy proxy;
    
    @SidedProxy(
    		clientSide="wildness.mod.handlers.NetworkManagerClient",
    		serverSide="wildness.mod.handlers.NetworkManager"
   	)
    public static NetworkManager network;
    
    public static ClaimStorage ZoneDB = new ClaimStorage();
    

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        
        instance = this;
        
        this.loadSettings();
        
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        
        WildnessBlocks.Setup();
        WildnessBlocks.RegisterBlocks();
        WildnessItems.Setup();
        WildnessItems.RegisterBlocks();
        
        proxy.registerKeys();
        proxy.registerKeyBindingHandler();
        proxy.registerHighlightHandler();
        //proxy.registerTileEntities(true);
        proxy.registerSoundHandler();        
        
        MinecraftForge.EVENT_BUS.register(new ForgeEventsHandler());
        //MinecraftForge.EVENT_BUS.register(new ForgeEventsHandler());
        
    }
    
    @EventHandler
    public void initialize(FMLInitializationEvent evt)
    {
    	NetworkRegistry.instance().registerGuiHandler(this, proxy);
    	//NetworkRegistry.instance().registerConnectionHandler(new PacketHandler());
    	proxy.setupGuiIngameForge();
    	
		/*if (evt.getSide().isClient()) {
	        ClientPlayerAPI.register("WildnessMod",WClientPlayerBase.class); } else {
	        ServerPlayerAPI.register("WildnessMod",WServerPlayerBase.class); }*/
    	
    	
    }
    
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt) {}

	@EventHandler
    public void load(FMLInitializationEvent event) {
        ClientPlayerAPI.register("WildnessMod",WClientPlayerBase.class);
        ServerPlayerAPI.register("WildnessMod",WServerPlayerBase.class);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	//Try to set unbkeakbl dirt
    	//Block.grass.setBlockUnbreakable();
    	//Block.dirt.setBlockUnbreakable();
    	//Block.dirt.setHardness(60f);
    	
    	Item.itemsList[Block.dirt.blockID].setMaxStackSize(3);

    	List a = new ArrayList<String>();
    	a.add("Tankistodor");
    	//ZoneDB.createClaim(0, -74, 29, "Tankistodor2", true, false,a);
    	//ZoneDB.createClaim(0, -74, 28, "Tankistodor2", true, false,new ArrayList<String>());
    	
    		ClaimOwners cam = ZoneDB.addClaimOwner("2Tankistodor", "messages");
    		ZoneDB.addPlotToClaim(cam,0,-72,29);
    		ZoneDB.addPlotToClaim(cam,0,-72,30);
    	
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
   	
    }



    private void loadSettings() {
    	
    	Configuration config;

        try
        {
            config = new Configuration(new File(proxy.getMinecraftDir(), "/config/Wildness/WildnessOptions.cfg"));
            config.load();
        }
        catch (Exception var3)
        {
            System.out.println("[Wildness] Error while trying to access settings configuration!");
            config = null;
        }
	
        System.out.println("[Wildness] Loading Settings");
        WildnessSettings.disableSleepInBed = WildnessSettings.getBooleanFor(config, "General", "disableSleepInBed", true);
        
        WildnessSettings.playerFailHeight = WildnessSettings.getIntFor(config, "General", "playrFailHeight", 6, "Add Potion slowdown effect when player is fail from hi height.");
        
        WildnessSettings.dayLength = WildnessSettings.getIntFor(config, "General", "dayLength", 24000, "This is how many ticks are in a minecraft day. 24000 is a standard MC cycle. Setting to 48000 will double the length of days.");
        WildnessSettings.yearLength = WildnessSettings.getIntFor(config, "General", "yearLength", 96, "This is how many days are in a year. Keep this to multiples of 12.");
        
		
    	if (config != null)
        {
            config.save();
        }
	}


}
