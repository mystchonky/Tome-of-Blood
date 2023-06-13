package com.luiq54.arsoscura;

import com.hollingsworth.arsnouveau.setup.ClientProxy;
import com.hollingsworth.arsnouveau.setup.IProxy;
import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import com.hollingsworth.arsnouveau.setup.ServerProxy;
import com.luiq54.arsoscura.common.items.ArsOscuraItems;
import com.luiq54.arsoscura.common.lang.ArsOscuraLang;
import com.luiq54.arsoscura.common.network.Networking;
import com.tterrag.registrate.Registrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArsOscura.MODID)
public class ArsOscura {
    public static final String MODID = "ars_oscura";
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new); //TODO: Change to our own

    public static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));
    public static final Logger LOGGER = LogManager.getLogger();

    public static CreativeModeTab ArsOscuraTab = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), MODID) {
        @Override
        public ItemStack makeIcon() {
            return ItemsRegistry.BLANK_GLYPH.get().getDefaultInstance();
        }
    };

    public static Registrate registrate() {
        return REGISTRATE.get();
    }

    public ArsOscura() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        ArsOscuraItems.register();
        ArsOscuraLang.register();
        ArsNouveauRegistry.registerGlyphs();

        modbus.addListener(this::setup);
        modbus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ArsNouveauRegistry.registerSounds();
        Networking.registerMessages();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
