package fr.maesloic.library.starterkit.commons.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    public static Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
