package DelirusCrux.Netherlicious;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

import DelirusCrux.Netherlicious.Biomes.Utility.FogEventListener;
import DelirusCrux.Netherlicious.Common.Blocks.GlowshroomBlock;
import DelirusCrux.Netherlicious.Common.Blocks.Render.GlowshroomModel;
import DelirusCrux.Netherlicious.Common.Blocks.Utility.ModBlocks;
import DelirusCrux.Netherlicious.Dimension.DimensionProvider;
import DelirusCrux.Netherlicious.Utility.Configuration.NetherliciousConfiguration;
import DelirusCrux.Netherlicious.Utility.DungeonUtility.ChestLoot;
import DelirusCrux.Netherlicious.World.Worldgen.NetherDirtGrassGen;
import DelirusCrux.Netherlicious.World.Worldgen.RockWorldGenHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Netherlicious.MODID, name = "Netherlicious",dependencies = "after:harvestcraft", version = Netherlicious.VERSION)
public class Netherlicious {
	public static final String MODID = "netherlicious";
	public static final String VERSION = "1.0.0";
	public static final Netherlicious INSTANCE = new Netherlicious();
	
	
    public static int Boneyard;
    public static int CorruptedSands;
    public static int PhantasmagoricInferno;
    public static int PolarChasm;
    public static int Undergarden;
    public static int VisceralHeap;
	
	
	@EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
		
		MinecraftForge.EVENT_BUS.register(new FogEventListener());

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ModCreativeTab.init();
	

		NetherliciousConfiguration.configurate(event.getSuggestedConfigurationFile());
		
		
		ModBlocks.registerBlocks();
		GlowshroomBlock.registerRenderers();
		GlowshroomBlock.load();
		RockWorldGenHandler.register();
		NetherDirtGrassGen.register();
		
		ChestLoot.initChestGenHook();
		
		

		
		
		
    	Configuration BiomeNames;
        {
          if(Loader.isModLoaded("BiomesOPlenty")) {
             BiomeNames = new Configuration(new File(event.getModConfigurationDirectory() + "/biomesoplenty/ids.cfg"));
             Boneyard = BiomeNames.get("biome ids", "Boneyard ID", -1).getInt();
             CorruptedSands = BiomeNames.get("biome ids", "Corrupted Sands ID", -1).getInt();
             PhantasmagoricInferno = BiomeNames.get("biome ids", "Phantasmagoric Inferno ID", -1).getInt();
             PolarChasm = BiomeNames.get("biome ids", "Polar Chasm ID", -1).getInt();
             Undergarden = BiomeNames.get("biome ids", "Undergarden ID", -1).getInt();
             VisceralHeap = BiomeNames.get("biome ids", "Visceral Heap ID", -1).getInt();
             
          }
       }

		
		
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
	
	@EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {

        DimensionProvider.init();
    }

	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event){

	}
}
