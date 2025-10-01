package com.knsn92.latest_music_discs.item;

import com.knsn92.latest_music_discs.LatestMusicDiscs;
import com.knsn92.latest_music_discs.resource.RecordData;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ItemLatestRecord extends ItemRecord {

    private RecordData record;

    public ItemLatestRecord(RecordData record) {
        super(record.name);
        this.setUnlocalizedName("record");
        this.setTextureName(LatestMusicDiscs.MOD_ID+":"+record.textureName);

        this.record = record;
    }

    @Override
    public String getRecordNameLocal() {
        return StatCollector.translateToLocal(record.musicDiscTranslationKey);
    }

    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(LatestMusicDiscs.MOD_ID, name);
    }
}
