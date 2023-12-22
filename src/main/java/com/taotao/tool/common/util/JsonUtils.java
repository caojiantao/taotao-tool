package com.taotao.tool.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
    }

    @SneakyThrows
    public static String toJson(Object obj) {
        return mapper.writer().writeValueAsString(obj);
    }

    @SneakyThrows
    public static <P, Q> Q convert(P obj, Class<Q> clazz) {
        String json = toJson(obj);
        return mapper.readValue(json, clazz);
    }

    public static <P, Q> List<Q> convert(List<P> obj, Class<Q> clazz) throws JsonProcessingException {
        String json = toJson(obj);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        return mapper.readValue(json, javaType);
    }

    @SneakyThrows
    public static <T> T parse(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return mapper.readValue(json, new TypeReference<List<T>>() {
        });
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
