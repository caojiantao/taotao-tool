package com.taotao.tool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) throws JsonProcessingException {
        return mapper.writer().writeValueAsString(obj);
    }

    public static <T> T convert(Object obj, Class<T> clazz) throws JsonProcessingException {
        String json = toJson(obj);
        return mapper.readValue(json, clazz);
    }
}
