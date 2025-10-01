package com.knsn92.latest_music_discs.proxy;

import com.knsn92.latest_music_discs.item.ItemLatestRecord;
import com.knsn92.latest_music_discs.resource.RecordData;
import com.knsn92.latest_music_discs.resource.assets.Assets;
import cpw.mods.fml.common.registry.GameRegistry;

import java.io.IOException;

public class CommonProxy {

    public void setupAssets() throws IOException {
    }

    public void registerRecords() {
        for(RecordData record : Assets.RECORDS) {
            GameRegistry.registerItem(new ItemLatestRecord(record), record.name);
        }
    }

}
