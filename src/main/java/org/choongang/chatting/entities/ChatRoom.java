package org.choongang.chatting.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.commons.entities.Base;
import org.choongang.member.entities.Member;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends Base {
    @Id
    @Column(length = 65)
    private String roodId;

    @Column(length = 45)
    private String roomNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberSeq")
    private Member member;

    private int capacity = 2;//방 인원수

}
