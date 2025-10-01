package com.knsn92.latest_music_discs.resource.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class SoundsJson {

    private String soundPrefix;
    private Map<String, SoundElement> soundJson;

    public SoundsJson(String soundPrefix) {
        this.soundPrefix = soundPrefix;
        soundJson = Maps.newHashMap();
    }

    public void addRecord(String name) {
        this.soundJson.put(String.join(".", this.soundPrefix, name), new SoundElement("record", Lists.newArrayList(new SoundElement.Sounds(name, true))));
    }

    public String toJson(Gson gson) {
        return gson.toJson(this.soundJson);
    }

    private static class SoundElement {

        public String category;
        public List<Sounds> sounds;

        public SoundElement(String category, List<Sounds> sounds) {
            this.category = category;
            this.sounds = sounds;
        }

        private static class Sounds {
            public String name;
            public boolean stream;

            public Sounds(String name, boolean stream) {
                this.name = name;
                this.stream = stream;
            }
        }

    }

}
