package org.choongang.file.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.commons.entities.BaseMember;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_fileInfo_gid", columnList = "gid"),
        @Index(name = "idx_fInfo_gid_loc", columnList = "gid,location")
})//많이 하는 조회는 인덱스 걸기
public class FileInfo extends BaseMember {

    @Id
    @GeneratedValue
    private Long seq; //파일 등록 번호, 서버에 업로드하는 파일명 기준

    @Column(length = 65, nullable = false)
    private String gid = UUID.randomUUID().toString();//문자열로 랜덤하게 유니크아이디를 만들수 있는 기능중 하나

    @Column (length=65)//그룹안에서 위치별로 구분
    private String location;

    @Column (length = 80)
    private String fileName;

    @Column (length = 30)
    private String extension;

    private boolean done;


}
