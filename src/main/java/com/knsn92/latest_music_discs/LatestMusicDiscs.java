package com.knsn92.latest_music_discs;

import com.knsn92.latest_music_discs.item.ItemLatestRecord;
import com.knsn92.latest_music_discs.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Mod(modid = LatestMusicDiscs.MOD_ID, name = LatestMusicDiscs.MOD_NAME, version = LatestMusicDiscs.MOD_VERSION)
public class LatestMusicDiscs {

    public static final String MOD_ID = "latest_music_discs";
    public static final String MOD_NAME = "Latest Music Discs";
    public static final String MOD_VERSION = "0.0";

    public static final Logger LOG = LogManager.getLogger(MOD_NAME);

    @Mod.Instance
    public static LatestMusicDiscs INSTANCE;

    @SidedProxy(
            clientSide = "com.knsn92.latest_music_discs.proxy.ClientProxy",
            serverSide = "com.knsn92.latest_music_discs.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    private void preInit(FMLPreInitializationEvent event) throws IOException {
        proxy.setupAssets();
        proxy.registerRecords();
    }

    @Mod.EventHandler
    private void init(FMLInitializationEvent event) {

    }

}
