package com.knsn92.latest_music_discs.resource;

public class RecordData {

    public final String name;

    public final String soundResourceIndex;
    public final String soundName;

    public final String textureName;

    public final String musicDiscTranslationKey;

    public RecordData(String name, String soundResourceIndex, String soundName, String textureName, String musicDiscTranslationKey) {
        this.name = name;
        this.soundResourceIndex = soundResourceIndex;
        this.soundName = soundName;
        this.textureName = textureName;
        this.musicDiscTranslationKey = musicDiscTranslationKey;
    }

    public String getSoundFileName() {
        return this.soundName + ".ogg";
    }

    public String getTextureFileName() {
        return this.textureName + ".png";
    }

    public String getSoundResourceIndexHead2Chars() {
        return this.soundResourceIndex.length() >= 2 ? this.soundResourceIndex.substring(0, 2) : null;
    }
}
