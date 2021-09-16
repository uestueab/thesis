package com.test.viewpagerfun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.viewpagerfun.model.entity.Note;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Manages the shared preferences. SharedPreferences are related to context.
 * You can only reference it through a context. That's why a non-static approach (through constructor) was used.
 */
public class PrefManager<T> {

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public PrefManager(Context context) {
        this.prefs = context.getSharedPreferences("prefs", MODE_PRIVATE);
        this.editor = prefs.edit();
    }

        /**
         * Set any type of shared preferences value
         * @param key of the shared preferences which the caller is desire to set
         * @param value types:
         *                     Boolean ,
         *                     String,
         *                     Float,
         *                     Long,
         *                     Int
         */
        public void set(String key, T value) {

            if (value instanceof String) {
                editor.putString(key, (String) value);
            }
            else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            }
            else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            }
            else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            }
            else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            }else{
                new IllegalArgumentException("This " + value + " doesn't exist, please specify it.");
            }

            editor.apply();
        }

        /**
         * Get any type of value from the shared preference
         * @param key the key of the shared preference
         * @param defaultValue types:
         *                     Boolean ,
         *                     StringSet,
         *                     String,
         *                     Float,
         *                     Long,
         *                     Int
         * @return same type of the default value which has been passed in
         */
        public T get(String key, T defaultValue) {

            if (defaultValue instanceof Boolean) {
                Boolean ret = prefs.getBoolean(key, (Boolean) defaultValue);
                return (T) ret;
            }
            else if (defaultValue instanceof String) {
                String ret = prefs.getString(key, (String) defaultValue);
                return (T) ret;
            }
            else if (defaultValue instanceof Float) {
                Float result = prefs.getFloat(key, (Float) defaultValue);
                return (T) result;
            }
            else if (defaultValue instanceof Long) {
                Long result = prefs.getLong(key, (Long) defaultValue);
                return (T) result;
            }
            else if (defaultValue instanceof Integer) {
                Integer result = prefs.getInt(key, (Integer) defaultValue);
                return (T) result;
            }

            return null;
        }

    /**
     * @return set list of notes to the specified key in shared preference
     */
    public void setNotes(String key, List<Note> list){
            Gson gson = new Gson();

            //prepare value
            Type type = new TypeToken<List<Note>>(){}.getType();
            String json = gson.toJson(list,type);

            editor.putString(key, json);
            editor.apply();
        }

    /**
     * @return get list of notes from shared preference
     *@param key key of the shared preference
     */
        public List<Note> getNotes(String key){
            Gson gson = new Gson();
            String json = prefs.getString(key, "");
            Type type = new TypeToken<List<Note>>(){}.getType();
            return gson.fromJson(json, type);
        }

    //Remove key from SharedPreferences
        public void remove(String key){
            editor.remove(key).apply();
        }
}


