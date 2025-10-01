package com.knsn92.latest_music_discs.resource;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class VersionManifest {

    private static final URL VERSION_MANIFEST_URL;

    static {
        try {
            VERSION_MANIFEST_URL = new URL("https://piston-meta.mojang.com/mc/game/version_manifest_v2.json");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public final Latest latest;
    public final Version[] versions;

    private VersionManifest(Latest latest, Version[] versions) throws IOException {
        this.latest = latest;
        this.versions = versions;
    }

    public static VersionManifest fetch() throws IOException {
        URLConnection conn = VERSION_MANIFEST_URL.openConnection();
        String versionManifestJsonText = IOUtils.toString(conn.getInputStream());

        Gson gson = new Gson();
        return gson.fromJson(versionManifestJsonText, VersionManifest.class);
    }

    public static class Latest {
        public final String release;
        public final String snapshot;

        public Latest(String release, String snapshot) {
            this.release = release;
            this.snapshot = snapshot;
        }
    }

    public static class Version {
        public final String id;
        public final String type;
        public final String url;
        public final String time;
        public final String releaseTime;
        public final String sha1;
        public final int complianceLevel;

        private Version(String id, String type, String url, String time, String releaseTime, String sha1, int complianceLevel) {
            this.id = id;
            this.type = type;
            this.url = url;
            this.time = time;
            this.releaseTime = releaseTime;
            this.sha1 = sha1;
            this.complianceLevel = complianceLevel;
        }
    }
}
