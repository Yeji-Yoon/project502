package org.choongang.admin.config.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.config.entities.Configs;
import org.choongang.admin.config.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {//저장처리
    private final ConfigsRepository repository;

    public <T> void save(String code, T data) {
        Configs configs = repository.findById(code).orElseGet(Configs::new);
        //조회가 되면 영속성 상태여서 수정, 조회가 안되면 orelseget으로 생성
        //반환값이 optional(orElseGet)임.

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        try {
            String jsonString = om.writeValueAsString(data);
            configs.setData(jsonString);
            configs.setCode(code);
            repository.saveAndFlush(configs);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
