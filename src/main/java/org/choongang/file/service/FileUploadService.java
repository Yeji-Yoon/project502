package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.configs.FileProperties;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.repositories.FileInfoRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {//올라간 파일을 리스트로 정리(json파일로)

    private final FileProperties fileProperties;
    private final FileInfoRepository repository;

    public List<FileInfo> upload(MultipartFile[] files, String gid, String location) {
        /**
         * 1.파일 정보 저장
         * 2. 서버쪽에 파일 업로드 처리
         */

        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();//랜덤하게 유티크한 아이디를 만들때

        String uploadPath = fileProperties.getPath(); //파일 업로드 기본 경로

        for (MultipartFile file : files) {
            /* 파일 정보 저장 S*/
            String fileName = file.getOriginalFilename();//업로드시 원 파일명
            //파일명.확장자 image.png, image.1.png

            //확장자
            String extension = fileName.substring(fileName.lastIndexOf("."));

            String fileType = file.getContentType();//getcontent : 파일 종류. fileType: 썸네일 추가할 때 사용

            FileInfo fileInfo = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .extension(extension)
                    .fileType(fileType)
                    .build();

            repository.saveAndFlush(fileInfo);
            /* 파일 정보 저장 E*/

        }
    }
}
