package com.msa2024.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GenericFileUtil<T> {
    private String basePath;
    private ObjectMapper objectMapper;

    /**
     * GenericFileUtil 생성자.
     * @param basePath 파일 기본 경로.
     */
    public GenericFileUtil(String basePath) {
        this.basePath = basePath;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록
    }

    /**
     * 파일이 존재하지 않으면 파일을 생성
     * @param filename 생성할 파일 이름.
     * @throws IOException 파일 생성 중 오류가 발생한 경우.
     */
    public void createFileIfNotExists(String filename) throws IOException {
        File file = new File(basePath + filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 지정된 파일에서 객체 리스트를 읽어온다.
     * @param filename 읽을 파일 이름.
     * @param typeReference 읽어올 객체의 타입 참조.
     * @return 파일에서 읽어온 객체 리스트. 파일이 없거나 비어 있으면 빈 리스트를 반환
     */
    public List<T> readFromFileWithJackson(String filename, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(basePath + filename);
            if (!file.exists() || file.length() == 0) { // 파일이 존재하지 않거나 비어 있는 경우
                return Collections.emptyList();
            }
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 객체 리스트를 지정된 파일에 쓴다,.
     * @param filename 쓸 파일 이름.
     * @param items 파일에 쓸 객체 리스트.
     */
    public void writeToFileWithJackson(String filename, List<T> items) {
        try {
            objectMapper.writeValue(new File(basePath + filename), items);
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
