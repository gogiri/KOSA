package com.msa2024.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// LocalDateTimeAdapter 클래스는 LocalDateTime 객체를 JSON 형식으로 직렬화하거나 역직렬화하는 기능을 제공
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    // ISO_LOCAL_DATE_TIME 형식을 사용하여 LocalDateTime 객체를 포맷팅하기 위한 DateTimeFormatter 객체
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // LocalDateTime 객체를 JSON 형식으로 직렬화하는 메서드
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        // LocalDateTime 객체가 null인 경우 JSON null 값으로 직렬화하고, 그렇지 않으면 ISO_LOCAL_DATE_TIME 형식의 문자열로 직렬화
        // 대부분의 프로그래밍 언어 및 라이브러리에서 ISO 8601 형식을 기본적으로 지원합니다.
        // 이를 통해 별도의 커스터마이징 없이도 손쉽게 날짜 및 시간 데이터를 다룰 수 있다.
        if (localDateTime == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(localDateTime.format(formatter));
        }
    }

    // JSON 형식을 LocalDateTime 객체로 역직렬화하는 메서드
    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        // JSON 요소가 null인 경우 null을 반환하고, 그렇지 않으면 문자열로 변환하여 LocalDateTime 객체로 변환합니다.
        return jsonReader.peek() == com.google.gson.stream.JsonToken.NULL ? null : LocalDateTime.parse(jsonReader.nextString(), formatter);
    }
}
