package org.choongang.admin.config.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.config.entities.Configs;
import org.choongang.admin.config.repositories.ConfigsRepository;
import org.choongang.board.entities.Board;
import org.choongang.commons.Utils;
import org.choongang.commons.exceptions.AlertException;
import org.choongang.file.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigInfoService {

    private final ConfigsRepository repository;
    private final FileUploadService fileUploadService;
    private final HttpServletRequest request;


    //Optional :  널값 처리
    public <T> Optional<T> get(String code, Class<T> clazz) {//Optional<T> : null값 처리
        return get(code, clazz,null);
    }

    public <T> Optional<T> get(String code, TypeReference<T> typeReference) {
        return get(code,null,typeReference);
    }

    public <T> Optional<T> get(String code, Class<T> clazz, TypeReference<T> typeReference) {
        Configs config = repository.findById(code).orElse(null);
        if (config ==null || !StringUtils.hasText(config.getData())) {
            return Optional.ofNullable(null);
        }

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String jsonString = config.getData();
        try {
            T data = null;
            if(clazz == null) {//TypeReference로 처리
                data = om.readValue(jsonString, new TypeReference<T>() { });//문자열로 바꿈

            }else {// Class로 처리
                data = om.readValue(jsonString,clazz);//문자열을 자바객체로 바꿈
            }
            return Optional.ofNullable(null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }
}
