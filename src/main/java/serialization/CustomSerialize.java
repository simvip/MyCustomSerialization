package serialization;

import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Base64;

/**
 * Class CustomSerialize
 * Under hood it use Gson library.
 *
 * @author create by ivanslusar
 * 8/22/19
 * @project CustomSerialization
 */
public class CustomSerialize {
    private static final Gson gsonn = new GsonBuilder()
            .registerTypeHierarchyAdapter(byte[].class, new ByteArrayTypeAdapter())
            .disableInnerClassSerialization()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    /**
     * Adapter for serialization or deserialization byte array.
     */
    private static class ByteArrayTypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.getDecoder().decode(json.getAsString());
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
        }
    }

    /**
     * Serialize any object into Json
     *
     * @param object the object that need serialize
     * @return the serialization string
     */
    public static String serialization(Object object) {
        return gsonn.toJson(object);
    }

    /**
     * Deserialize any Json to a relevant object type
     *
     * @param jsonString source string
     * @param classType  the class to deserialize
     * @param <T>
     * @return the Object created
     */
    public static <T> T deserialization(String jsonString, Class<T> classType) {

        if (jsonString.isEmpty()) {
            try {
                throw new IllegalAccessException("Cannot deserialize current stream to object");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try (Reader reader = new StringReader(jsonString)) {
            return gsonn.fromJson(reader, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deserialize any Json to a relevant object type
     *
     * @param jsonString source string
     * @param type  the Type to deserialize
     * @param <T>
     * @return the Object created
     */
    public static <T> T deserialization(String jsonString, Type type) {

        if (jsonString.isEmpty()) {
            try {
                throw new IllegalAccessException("Cannot deserialize current stream to object");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try (Reader reader = new StringReader(jsonString)) {
            return gsonn.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
