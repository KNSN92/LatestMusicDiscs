package com.knsn92.latest_music_discs.resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;

public class LangWriter {

    private final Path path;

    private final Map<String, String> translations = Maps.newHashMap();

    public LangWriter(Path path) {
        this.path = path;
    }

    public void add(String key, String value) {
        this.translations.put(key, value);
    }

    public void write() throws IOException {
        List<String> lines = Lists.newArrayList();
        for(Map.Entry<String, String> translation : translations.entrySet()) {
            String line = translation.getKey() + "=" + translation.getValue();
            lines.add(line);
        }
        Files.write(this.path, lines);
    }

}
