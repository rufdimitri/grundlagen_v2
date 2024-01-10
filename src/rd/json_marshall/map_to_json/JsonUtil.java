package rd.json_marshall.map_to_json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonUtil {
    private JsonUtil() {
    }

    /** Creates object from JSON-string, stored in a file
     * @param fileName where the JSON-string, representing the object to create, is saved     *
     * @param typeToken type of the object
     *   can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}}</code>
     *   <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}}</code>
     * @return unmarshalled object from json of same type as typeObject
     */
    public static <T> T unmarshallFromFile(String fileName, TypeToken<T> typeToken) {
        try (Reader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(reader, typeToken.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** same as {@link #unmarshallFromFile(String, TypeToken)} method, returning defaultValue if Exception happens
     * @param fileName where the JSON-string, representing the object to create, is saved     *
     * @param typeToken type of the object
     *   can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}}</code>
     *   <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}}</code>
     * @param defaultValue value returned if Exception happens
     * @return unmarshalled object from json of same type as typeObject
     */
    public static <T> T unmarshallFromFileOrDefault(String fileName, TypeToken<T> typeToken, T defaultValue) {
        try {
            return unmarshallFromFile(fileName, typeToken);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static <T> void marshallToFile(String fileName, T object, TypeToken<T> typeToken) {
        try (Writer writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            gson.toJson(object, typeToken.getType(), writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Creates JSON-string from object
     * @param object object that should be marshalled to JSON-string
     * @param typeToken type of the object
     *   can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}}</code>
     *   <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}}</code>
     * @return unmarshalled object from json of same type as typeObject
     */
    public static <T> String marshallToJson(T object) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.toJson(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Creates object from JSON-string
     * @param json JSON-string representing the object to create
     * @param typeToken type of the object
     *   can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}}</code>
     *   <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}}</code>
     * @return unmarshalled object from json of same type as typeObject
     */
    public static <T>T unmarshallFromJson(String json, TypeToken<T> typeToken) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(json, typeToken.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
