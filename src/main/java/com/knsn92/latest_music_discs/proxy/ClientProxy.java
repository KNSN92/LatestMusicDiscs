package com.knsn92.latest_music_discs.proxy;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knsn92.latest_music_discs.resource.*;
import com.knsn92.latest_music_discs.resource.assets.AssetPaths;
import com.knsn92.latest_music_discs.resource.assets.Assets;
import com.knsn92.latest_music_discs.resource.model.SoundsJson;
import com.knsn92.latest_music_discs.resourcepack.LatestMusicDiscResourcePack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.List;

public class ClientProxy extends CommonProxy {

    @Override
    public void setupAssets() throws IOException {

        if(Files.exists(AssetPaths.VERSION_NAME)) {
            List<String> lines = Files.readAllLines(AssetPaths.VERSION_NAME);
            if(!lines.isEmpty() && !lines.get(0).equals(Assets.MC_VERSION)) {
                FileUtils.deleteDirectory(AssetPaths.ROOT.toFile());
            }
        }

        Files.createDirectories(AssetPaths.ROOT);

        Files.createDirectories(AssetPaths.SOUNDS);
        Files.createDirectories(AssetPaths.TEXTURES_ITEMS);
        Files.createDirectories(AssetPaths.LANG);

//        System.out.println(VersionManifest.fetch().latest.release);

        MCDataDownloader mcAssetDownloader = new MCDataDownloader(Assets.ASSET_URL_PREFIX);
        mcAssetDownloader.setMCJar(new URL(Assets.CLIENT_JAR_URL), AssetPaths.CLIENT_JAR);

        for(RecordData record : Assets.RECORDS) {
            mcAssetDownloader.putAsset(record.soundResourceIndex, AssetPaths.SOUNDS.resolve(record.getSoundFileName()));
        }

        mcAssetDownloader.download();
        SoundsJson soundsJson = new SoundsJson("records");
        for(RecordData record : Assets.RECORDS) {
            String recordName = record.soundName;
            soundsJson.addRecord(recordName);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Files.write(AssetPaths.SOUNDS_JSON, Lists.newArrayList(soundsJson.toJson(gson)));

        MCDataExtractor mcAssetExtractor = new MCDataExtractor(AssetPaths.CLIENT_JAR, "assets/minecraft/textures/item");

        for(RecordData record : Assets.RECORDS) {
            mcAssetExtractor.putAsset(record.getTextureFileName(), AssetPaths.TEXTURES_ITEMS.resolve(record.getTextureFileName()));
        }

        mcAssetExtractor.setAssetPathPrefix("assets/minecraft/lang");
        mcAssetExtractor.putAsset(Assets.EN_US_LANG, AssetPaths.ROOT.resolve(Assets.EN_US_LANG));

        mcAssetExtractor.extract();

        MCLangExtractor recordLangExtractor = new MCLangExtractor(AssetPaths.ROOT.resolve(Assets.EN_US_LANG), new JsonLangAccessor());

        for(RecordData record : Assets.RECORDS) {
            recordLangExtractor.addKey(record.musicDiscTranslationKey);
        }

        Map<String, String> recordMusicTranslations = recordLangExtractor.extract();

        LangWriter langWriter = new LangWriter(AssetPaths.LANG.resolve("en_US.lang"));
        for(Map.Entry<String, String> entry : recordMusicTranslations.entrySet()) {
            langWriter.add(entry.getKey(), entry.getValue());
        }
        langWriter.write();

        Files.deleteIfExists(AssetPaths.ROOT.resolve(Assets.EN_US_LANG));

        Files.deleteIfExists(AssetPaths.CLIENT_JAR);

        Files.write(AssetPaths.VERSION_NAME, Lists.newArrayList(Assets.MC_VERSION));

        List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
        defaultResourcePacks.add(new LatestMusicDiscResourcePack(AssetPaths.ROOT));
    }
}
