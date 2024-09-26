package study.supercoding_1.exception.exception;

import lombok.Getter;
import study.supercoding_1.exception.errorcode.ErrorCode;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
