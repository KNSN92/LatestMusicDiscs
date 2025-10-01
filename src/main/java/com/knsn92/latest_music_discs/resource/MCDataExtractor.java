package com.knsn92.latest_music_discs.resource;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class MCDataExtractor {

    private final Path jarLocation;
    private String assetPathPrefix;
    private final Map<String, Path> downloadAssets = Maps.newHashMap();

    public MCDataExtractor(Path jarLocation, String assetPathPrefix) {
        this.jarLocation = jarLocation;
        this.assetPathPrefix = assetPathPrefix;
    }

    public void putAsset(String assetPath, Path path) {
        this.downloadAssets.put(this.assetPathPrefix + "/" + assetPath, path);
    }

    public void setAssetPathPrefix(String assetPathPrefix) {
        this.assetPathPrefix = assetPathPrefix;
    }

    public void extract() throws IOException {
        try(JarInputStream jis = new JarInputStream(Files.newInputStream(this.jarLocation))) {
            JarEntry entry;
            while((entry = jis.getNextJarEntry()) != null) {
                String name = entry.getName();
                if(this.downloadAssets.containsKey(name)) {
                    try(OutputStream out = Files.newOutputStream(this.downloadAssets.get(name))) {
                        byte[] bytes = new byte[512];
                        while(true) {
                            int ret = jis.read(bytes);
                            if(ret <= 0) break;
                            out.write(bytes, 0, ret);
                        }
                    }
                }
            }
        }
    }
}
