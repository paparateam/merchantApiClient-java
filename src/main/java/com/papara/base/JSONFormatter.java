package com.papara.base;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * JSONFormatter converts objects to JSON representation and vice-versa. This
 * class depends on Google's GSON library to do the transformation. This class
 * is not thread-safe.
 */
public final class JSONFormatter {

    /**
     * FieldNamingPolicy used by the underlying Gson library. Alter this property to
     * set a fieldnamingpolicy other than UPPER_CAMEL_CASE used by Papara REST APIs
     */
    private static FieldNamingPolicy FIELD_NAMING_POLICY = FieldNamingPolicy.UPPER_CAMEL_CASE;
    /**
     * Gson
     */
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /*
     * JSONFormatter is coupled to the stubs generated using the SDK generator.
     * Since Papara REST APIs support only JSON, this class is bound to the stubs
     * for their json representation.
     */
    private JSONFormatter() {
    }

    /**
     * Set a format for gson FIELD_NAMING_POLICY. See {@link FieldNamingPolicy}
     *
     * @param FIELD_NAMING_POLICY
     */
    public static final void setFIELD_NAMING_POLICY(FieldNamingPolicy FIELD_NAMING_POLICY) {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Converts a Raw Type to JSON String
     *
     * @param <T> Type to be converted
     * @param t   Object of the type
     * @return JSON representation
     */
    public static <T> String toJSON(T t) {
        return GSON.toJson(t);
    }

    /**
     * Converts a JSON String to object representation
     *
     * @param <T>            Type to be converted
     * @param responseString JSON representation
     * @param clazz          Target class
     * @return Object of the target type
     */
    public static <T> T fromJSON(String responseString, Class<T> clazz, Type type) {
        T t = null;

        if (clazz.isAssignableFrom(responseString.getClass())) {
            t = clazz.cast(responseString);
        } else {
            t = GSON.fromJson(responseString, type);
        }
        return t;
    }

}
