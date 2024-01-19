package org.choongang.board.service.comment;

import org.choongang.commons.Utils;
import org.choongang.commons.exceptions.AlertException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends AlertException {

    public CommentNotFoundException () {
        super(Utils.getMessage("NotFound.comment", "errors"), HttpStatus.NOT_FOUND);

    }

}
