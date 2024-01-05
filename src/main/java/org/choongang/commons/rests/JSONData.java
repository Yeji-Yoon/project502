package org.choongang.commons.rests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class JSONData <T> {
    private HttpStatus status = HttpStatus.OK;
    private boolean success = true;

    @NonNull//데이터는 상수가 아니라 바꿀수 있음.
    private T data;
    private String message;

}
