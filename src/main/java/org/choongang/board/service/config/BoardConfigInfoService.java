package org.choongang.board.service.config;


import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.controllers.RequestBoardConfig;
import org.choongang.board.entities.Board;
import org.choongang.board.entities.QBoard;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.service.FileInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigInfoService {
    private final BoardRepository boardRepository;
    private final FileInfoService fileInfoService;

    /**
     * 게시판 설정 조회
     * 없으면 예외를 던짐
     *
     * @param bid
     * @return
     */
    public Board get(String bid) {//개별 데이터 조회
        Board board = boardRepository.findById(bid).orElseThrow(BoardNotFoundException::new);//게시글 가져오기

        addBoardInfo(board);//추가 정보를 더 넣기

        return board;
    }

    public RequestBoardConfig getForm(String bid) {//양식을 가져오겠다.
        Board board = get(bid);

        RequestBoardConfig form = new ModelMapper().map(board, RequestBoardConfig.class);
        form.setListAccessType(board.getListAccessType().name());
        form.setViewAccessType(board.getViewAccessType().name());
        form.setWriteAccessType(board.getWriteAccessType().name());
        form.setReplyAccessType(board.getReplyAccessType().name());
        form.setCommentAccessType(board.getCommentAccessType().name());

        form.setMode("edit");//수정 작업을 주로함.

        return form;
    }

    /**
     * 게시판 설정 추가 정보
     *      - 에디터 첨부 파일 목록
     * @param board
     */
    public void addBoardInfo(Board board) {
        //업로드가 완료된 파일을 가져옴
        String gid = board.getGid();

        List<FileInfo> htmlTopImages = fileInfoService.getListDone(gid, "html_top");

        List<FileInfo> htmlBottomImages = fileInfoService.getListDone(gid, "html_bottom");

        board.setHtmlTopImages(htmlTopImages);
        board.setHtmlBottomImages(htmlBottomImages);
    }


}




