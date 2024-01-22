package org.choongang.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.Authority;

@Data
@Entity
//한개일때는 컬럼.여러개일때는 table로 묶기
//enum이 3개여서 unique조건을 걸어서 한개만 제약
@Table(indexes=@Index(name="uq_member_authority", columnList = "member_seq, authority", unique = true))
public class Authorities {
    //Many  권한 to one : 사용자
    @Id
    @GeneratedValue
    private Long seq;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_seq")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(length=15, nullable = false)
    private Authority authority;
}
