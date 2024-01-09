package rd.json_marshall.map_to_json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JsonUtil<T> {
    public Type objectType;

    private JsonUtil(Type objectType) {
        this.objectType = objectType;
    }

    /** @param objectType type of the object
     *      can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}.getType()}</code>
     *      <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}.getType()}</code>
     */
    public static <T> JsonUtil<T> of (Type objectType) {
        return new JsonUtil<>(objectType);
    }

    /** Creates object from JSON-string
     * @param fileName where the JSON-string, representing the object to create, is saved     *
     * @return unmarshalled object from json of same type as typeObject
     */
    public T unmarshallFromFile(String fileName) {
        try (Reader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            return gson.fromJson(reader, objectType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T unmarshallFromFileOrDefault(String fileName, T defaultValue) {
        try {
            return unmarshallFromFile(fileName);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public void marshallToFile(String fileName, T object) {
        try (Writer writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            gson.toJson(object, objectType, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Creates JSON-string from object
     * @param object object that should be marshalled to JSON-string
     * @param typeObject  type of the object
     *  can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}.getType()}</code>
     *  <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}.getType()}</code>
     * @return unmarshalled object from json of same type as typeObject
     */
    public static String marshallToJson(Object object, Type typeObject) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.toJson(object, typeObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Creates object from JSON-string
     * @param json JSON-string representing the object to create
     * @param typeObject  typeObject  type of the object
     *      can be created using <code>{@literal new TypeToken<PUT-HERE-CLASS-WITH-GENERIC-PARAMETERS>() {}.getType()}</code>
     *      <br/><br/>Example: <code>{@literal new TypeToken<Map<Integer, String>>() {}.getType()}</code>
     * @return unmarshalled object from json of same type as typeObject
     */
    public static <T> T unmarshallFromJson(String json, Type typeObject) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(json, typeObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
