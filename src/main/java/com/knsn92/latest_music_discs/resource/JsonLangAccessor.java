package com.knsn92.latest_music_discs.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonLangAccessor implements IMCLangAccessor {

    @Override
    public Map<String, String> access(String content) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return new Gson().fromJson(content, type);
    }
}
