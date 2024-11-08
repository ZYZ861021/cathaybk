package com.example.cathayunited.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BeanConvertUtil {

    private BeanConvertUtil() {
    }

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T convert(Object object, Class<T> clazz){
        return mapper.convertValue(object, clazz);
    }

    public static <T> T convert(Object object, TypeReference<T> typeReference){
        return mapper.convertValue(object, typeReference);
    }

    public static <T> T readValue(String string, Class<T> clazz) {
        try {
            return mapper.readValue(string, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
