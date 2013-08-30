package wildness.mod.blocks;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;

import wildness.mod.WildnessMod;
import wildness.mod.WildnessSettings;
import net.minecraftforge.common.Configuration;

public class WildnessBlocks {
	public static int blockClaimBlockID = 2068;
	public static BlockClaim blockClaim;
	
	static Configuration config;
	
	public static void Setup()
    {
		System.out.println("[Wildness] Loading Blocks ID config");
        try
        {
            config = new Configuration(new File(WildnessMod.proxy.getMinecraftDir(), "/config/Wildness/WildnessBlockID.cfg"));
            config.load();
        }
        catch (Exception var1)
        {
            System.out.println("[Wildness] Error while trying to access block id configuration!");
            config = null;
        }
        
        blockClaimBlockID = WildnessSettings.getIntFor(config, "block", "blockClaimBlockID", blockClaimBlockID);
        
        if (config != null)
        {
            config.save();
        }
        
    }
	
	public static void RegisterBlocks()
	{
		System.out.println("[Wildness] Registering Blocks");
		blockClaim = (new BlockClaim(blockClaimBlockID, 3));
		blockClaim.setUnlocalizedName("Claim");
		GameRegistry.registerBlock(blockClaim, "Claim");
	}
	
	
}
