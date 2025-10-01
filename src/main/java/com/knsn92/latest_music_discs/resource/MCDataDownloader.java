package com.knsn92.latest_music_discs.resource;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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

    private static final TrustManager[] ignoreCertificationMgr = new TrustManager[] {
        new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }
    };

    private void downloadAndWrite(URL url, Path path) throws IOException {
        try(
                InputStream in = createConnection(url).getInputStream();
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

    private HttpsURLConnection createConnection(URL url) throws IOException {
        if(!url.getProtocol().equals("https")) {
            throw new IllegalArgumentException("URL protocol must be https url:" + url);
        }
        SSLContext ssl_ctx = null;
        try {
            ssl_ctx = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            ssl_ctx.init(null, ignoreCertificationMgr, null);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setAllowUserInteraction(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestMethod("GET");
        conn.setSSLSocketFactory(ssl_ctx.getSocketFactory());
        return conn;
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
