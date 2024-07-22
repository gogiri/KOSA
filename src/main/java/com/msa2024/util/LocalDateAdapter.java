package com.msa2024.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

// LocalDateAdapter 클래스는 LocalDate 객체를 JSON 형식으로 직렬화하거나 역직렬화하는 기능을 제공
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // LocalDate 객체를 JSON 형식으로 직렬화하는 메서드
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        // LocalDate 객체가 null인 경우 JSON null 값으로 직렬화하고, 그렇지 않으면 LocalDate의 문자열 표현으로 직렬화
        return src == null ? JsonNull.INSTANCE : new JsonPrimitive(src.toString());
    }

    // JSON 형식을 LocalDate 객체로 역직렬화하는 메서드
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // JSON 요소가 null인 경우 null을 반환하고, 그렇지 않으면 문자열로 변환하여 LocalDate 객체로 변환
        return json.isJsonNull() ? null : LocalDate.parse(json.getAsString());
    }
}
