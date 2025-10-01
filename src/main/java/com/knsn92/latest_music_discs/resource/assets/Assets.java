package com.knsn92.latest_music_discs.resource.assets;

import java.util.List;
import com.google.common.collect.Lists;
import com.knsn92.latest_music_discs.resource.RecordData;

public class Assets {

    public static final String MC_VERSION = "1.21.9";

    public static final String CLIENT_JAR_URL = "https://piston-data.mojang.com/v1/objects/ce92fd8d1b2460c41ceda07ae7b3fe863a80d045/client.jar";
    public static final String ASSET_URL_PREFIX = "https://resources.download.minecraft.net";

    public static final List<RecordData> RECORDS = Lists.newArrayList();

    public static final String EN_US_LANG = "en_us.json";

    static {

        RECORDS.add(new RecordData(
                "pigstep",
                "9ffb1791e8aba8f266a673abec1846c3bf8fb8cc",
                "pigstep",
                "music_disc_pigstep",
                "item.minecraft.music_disc_pigstep.desc"
        ));
        RECORDS.add(new RecordData(
                "otherside",
                "a5effd79795773422bb4de85841838f3ad9c216d",
                "otherside",
                "music_disc_otherside",
                "item.minecraft.music_disc_otherside.desc"
        ));
        RECORDS.add(new RecordData(
                "5",
                "ef72603358a5f2a717cf0b718af677025f9d3c79",
                "5",
                "music_disc_5",
                "item.minecraft.music_disc_5.desc"
        ));
        RECORDS.add(new RecordData(
                "relic",
                "f6c8af3b270a609b12eddd4a56fe2139662782e8",
                "relic",
                "music_disc_relic",
                "item.minecraft.music_disc_relic.desc"
        ));
        RECORDS.add(new RecordData(
                "precipice",
                "a069bdaa24c0d9b9c0a2907113c5716ae5775846",
                "precipice",
                "music_disc_precipice",
                "item.minecraft.music_disc_precipice.desc"
        ));
        RECORDS.add(new RecordData(
                "creator_music_box",
                "e15def690303d9631fde9f60ea6d38de8e62b5db",
                "creator_music_box",
                "music_disc_creator_music_box",
                "item.minecraft.music_disc_creator_music_box.desc"
        ));
        RECORDS.add(new RecordData(
                "creator",
                "d1c3c66face8215ff2f9635f4d7c168fdea34d88",
                "creator",
                "music_disc_creator",
                "item.minecraft.music_disc_creator.desc"
        ));
        RECORDS.add(new RecordData(
                "tears",
                "fcfacc37f008476785db7bd0b95d1e00836697c0",
                "tears",
                "music_disc_tears",
                "item.minecraft.music_disc_tears.desc"
        ));
        RECORDS.add(new RecordData(
                "lava_chicken",
                "3fad13f1e9f4fa6eedcab98ca691c6c112087b07",
                "lava_chicken",
                "music_disc_lava_chicken",
                "item.minecraft.music_disc_lava_chicken.desc"
        ));
    }
}
