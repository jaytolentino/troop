package com.linkedladies.troop.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.linkedladies.troop.app.TroopApp;

import java.util.Set;

public class UserDataManager {

    private final String FILE_SUFFIX = ".data";

    private SharedPreferences preferences;

    public UserDataManager() {
        String file = TroopApp.getInstance().getPackageName() + FILE_SUFFIX;
        preferences = TroopApp.getInstance().getSharedPreferences(file, Context.MODE_PRIVATE);
    }

    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }

    public void put(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public void put(String key, long value) {
        preferences.edit().putLong(key, value).apply();
    }

    public void put(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public void put(String key, Set<String> values) {
        preferences.edit().putStringSet(key, values).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public Integer getInt(String key) {
        int toReturn = preferences.getInt(key, 0);
        if (toReturn == 0) {
            return null;
        }
        return toReturn;
    }

    public Long getLong(String key) {
        long toReturn = preferences.getLong(key, 0);
        if (toReturn == 0) {
            return null;
        }
        return toReturn;
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

}
