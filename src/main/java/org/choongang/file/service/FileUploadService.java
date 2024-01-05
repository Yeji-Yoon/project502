package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.choongang.commons.Utils;
import org.choongang.configs.FileProperties;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.repositories.FileInfoRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {//올라간 파일을 리스트로 정리(json파일로)

    private final FileProperties fileProperties;
    private final FileInfoRepository repository;
    private final FileInfoService infoService;
    private final Utils utils;

    public List<FileInfo> upload(MultipartFile[] files, String gid, String location) {
        /**
         * 1. 파일 정보 저장(파일명이 중복하는지 여부)
         * 2. 서버쪽에 파일 업로드 처리
         */

        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();//랜덤하게 유티크한 아이디를 만들때

        String uploadPath = fileProperties.getPath(); //파일 업로드 기본 경로
        String thumbPath = uploadPath +"thumbs/";//썸네일 업로드 기본 경로


        List<int[]> thumbsSize = utils.getThumbSize();//썸네일 사이즈

        List<FileInfo> uploadedFiles = new ArrayList<>(); //업로드 성공 파일 정보 목록

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
            /* 파일 정보 저장 E */

            /* 파일 업로드 처리 S */
            long seq = fileInfo.getSeq();
            File dir = new File(uploadPath + (seq % 10));
            if(!dir.exists()) { //디렉토리가 없으면 -> 생성
                dir.mkdir();
            }

            File uploadFile = new File(dir, seq + extension);
            try {
                file.transferTo(uploadFile);

                /* 썸네일 이미지 처리 S */
                if (fileType.indexOf("image/") != -1 && thumbsSize != null) {
                    File thumbDir = new File(thumbPath + (seq %10L) + "/" + seq);
                    if(!thumbDir.exists()) {
                        thumbDir.mkdirs();
                    }
                    for (int[] sizes : thumbsSize) {
                        String thumbFileName = sizes[0] + "_" + sizes[1] + "_" + seq + extension;


                        System.out.println(Arrays.toString(sizes));

                        File thumb = new File(thumbDir,thumbFileName);
                        Thumbnails.of(uploadFile).size(sizes[0],sizes[1]).toFile(thumb);//너비 높이
                    }

                }
                /* 썸네일 이미지 처리 E */

                infoService.addFileInfo(fileInfo);//파일 추가정보 처리

                uploadedFiles.add(fileInfo);//업로드 성공시 파일 정보 추가
            } catch (IOException e) {
                e.printStackTrace();
                repository.delete(fileInfo); // 업로드 실패시에는 파일정보 제거
                repository.flush();
            }
            /* 파일 업로드 처리 E */


        }
        return uploadedFiles;
    }

    /**
     * 업로드 처리 완료
     * @param gid
     */
    public void procssDon(String gid) {
        List<FileInfo> files = repository.findByGid(gid);
        if (files == null ) {
            return;
        }

        files.forEach(file -> file.setDone(true));
        repository.flush();
    }
}
