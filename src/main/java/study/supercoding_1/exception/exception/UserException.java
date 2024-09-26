package study.supercoding_1.exception.exception;

import lombok.Getter;
import study.supercoding_1.exception.errorcode.ErrorCode;

@Getter
public class UserException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
