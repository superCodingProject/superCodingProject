package study.supercoding_1.exception.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.supercoding_1.exception.errorcode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final ErrorCode errorCode;
}
