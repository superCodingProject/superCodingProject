package study.supercoding_1.exception.exception;

import lombok.Getter;
import study.supercoding_1.exception.errorcode.ErrorCode;

@Getter
public class CommentException extends RuntimeException{
    private final ErrorCode errorCode;

    public CommentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
