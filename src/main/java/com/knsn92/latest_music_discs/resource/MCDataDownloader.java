package com.knsn92.latest_music_discs.resource;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class MCDataDownloader {

    private final String assetURLPrefix;
    private final Map<String, Path> downloadAssets = Maps.newHashMap();

    private Map.Entry<URL, Path> downloadMCJar = null;

    public MCDataDownloader(String assetURLPrefix) {
        this.assetURLPrefix = assetURLPrefix;
    }

    public void setMCJar(URL url, Path path) {
        this.downloadMCJar = Pair.of(url, path);
    }

    public void putAsset(String resourceHash, Path path) {
        downloadAssets.put(resourceHash, path);
    }

    public void download() throws IOException {

        if(this.downloadMCJar != null) {
            URL url = this.downloadMCJar.getKey();
            Path path = this.downloadMCJar.getValue();
            this.downloadAndWrite(url, path);
        }

        for(Map.Entry<String, Path> asset : this.downloadAssets.entrySet()) {
            URL url = getURL(asset.getKey());
            Path path = asset.getValue();
            if(Files.notExists(path)) {
                this.downloadAndWrite(url, path);
            }
        }

    }

    private void downloadAndWrite(URL url, Path path) throws IOException {
        URLConnection conn = url.openConnection();
        try(
                InputStream in = conn.getInputStream();
                OutputStream out = Files.newOutputStream(path)
        ) {
            byte[] bytes = new byte[512];
            while(true) {
                int ret = in.read(bytes);
                if(ret <= 0) break;
                out.write(bytes, 0, ret);
            }
        }
    }

    private URL getURL(String resourceHash) {
        String location = "/" + resourceHash.substring(0, 2) + "/" + resourceHash;
        try {
            return new URL(this.assetURLPrefix + location);
        } catch (MalformedURLException e) {
            return null;
        }
    }

}
