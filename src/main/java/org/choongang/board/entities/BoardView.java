package org.choongang.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
@IdClass(BoardViewId.class) // 기본키 조합
public class BoardView {

    @Id
    private Long seq; //게시글 번호

    @Id
    @Column(name="_uid")
    private int uid;

}
