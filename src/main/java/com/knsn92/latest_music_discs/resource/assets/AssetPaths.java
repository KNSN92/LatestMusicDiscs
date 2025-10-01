package com.knsn92.latest_music_discs.resource.assets;

import net.minecraft.client.Minecraft;

import java.nio.file.Path;

public class AssetPaths {

    public static final Path ROOT = Minecraft.getMinecraft().mcDataDir.toPath().resolve("latest_music_discs").toAbsolutePath().normalize();

    public static final Path SOUNDS = ROOT.resolve("sounds");
    public static final Path TEXTURES_ITEMS = ROOT.resolve("textures").resolve("items");
    public static final Path LANG = ROOT.resolve("lang");

    public static final Path VERSION_NAME = ROOT.resolve("mc_version.txt");
    public static final Path CLIENT_JAR = ROOT.resolve("client.jar");
    public static final Path SOUNDS_JSON = ROOT.resolve("sounds.json");

}
