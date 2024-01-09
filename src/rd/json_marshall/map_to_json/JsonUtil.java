package rd.json_marshall.map_to_json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JsonUtil <T> {
    public Class<T> objectClass;

    private JsonUtil(Class<T> objectClass) {
        this.objectClass = objectClass;
    }

    public static <T> JsonUtil<T> of (Class<T> objectClass) {
        return new JsonUtil<>(objectClass);
    }

    public T unmarshallFromFile(String fileName) {
        try (Reader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            return gson.fromJson(reader, objectClass);
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

            gson.toJson(object, writer);
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (Writer writer = new OutputStreamWriter(baos, StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(object, typeObject, writer);
            //gson.toJson(object, writer);

            writer.flush();
            return baos.toString(StandardCharsets.UTF_8);
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
