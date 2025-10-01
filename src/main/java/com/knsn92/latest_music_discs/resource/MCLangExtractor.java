package com.knsn92.latest_music_discs.resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.Map;

public class MCLangExtractor {

    private final Path langPath;

    private final IMCLangAccessor accessor;

    private final List<String> extractionKeys = Lists.newArrayList();

    public MCLangExtractor(Path langPath, IMCLangAccessor accessor) {
        this.langPath = langPath;
        this.accessor = accessor;
    }

    public void addKey(String key) {
        this.extractionKeys.add(key);
    }

    public Map<String, String> extract() throws IOException {
        String langFileContents = String.join("\n", Files.readAllLines(this.langPath));
        Map<String, String> langContents = this.accessor.access(langFileContents);
        Map<String, String> extractedLangContents = Maps.newHashMap();
        for(String key : this.extractionKeys) {
            extractedLangContents.put(key, langContents.get(key));
        }
        return extractedLangContents;
    }


}
