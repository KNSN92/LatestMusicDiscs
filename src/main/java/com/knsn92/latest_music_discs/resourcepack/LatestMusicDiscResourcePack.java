package com.knsn92.latest_music_discs.resourcepack;

import com.google.common.collect.ImmutableSet;
import com.knsn92.latest_music_discs.LatestMusicDiscs;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class LatestMusicDiscResourcePack implements IResourcePack {

    private final Path assetPath;

    public LatestMusicDiscResourcePack(Path assetPath) {
        this.assetPath = assetPath.toAbsolutePath().normalize();
    }

    private Path resourceLocationToPath(ResourceLocation location) {
        return this.assetPath.resolve(location.getResourcePath());
    }

    @Override
    public InputStream getInputStream(ResourceLocation location) throws IOException {
        return Files.newInputStream(this.resourceLocationToPath(location));
    }

    @Override
    public boolean resourceExists(ResourceLocation location) {
        return LatestMusicDiscs.MOD_ID.equals(location.getResourceDomain())
                && Files.exists(this.resourceLocationToPath(location));
    }

    @Override
    public Set getResourceDomains() {
        return ImmutableSet.of(LatestMusicDiscs.MOD_ID);
    }

    @Override
    public IMetadataSection getPackMetadata(IMetadataSerializer location, String p_135058_2_) {
        return null;
    }

    @Override
    public BufferedImage getPackImage() {
        return null;
    }

    @Override
    public String getPackName() {
        return "LatestMusicDiscResourcePack";
    }

}
