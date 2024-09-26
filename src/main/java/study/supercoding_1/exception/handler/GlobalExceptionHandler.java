package study.supercoding_1.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.supercoding_1.exception.errorcode.CommonErrorCode;
import study.supercoding_1.exception.errorcode.ErrorCode;
import study.supercoding_1.exception.exception.CommentException;
import study.supercoding_1.exception.exception.PostException;
import study.supercoding_1.exception.exception.ResourceNotFoundException;
import study.supercoding_1.exception.exception.RestApiException;
import study.supercoding_1.exception.response.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // RuntimeException 처리
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e) {
        return handleExceptionInternal(e.getErrorCode());
    }

    // NotFoundException 처리
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e){
        return handleExceptionInternal(e.getErrorCode());
    }

    // CommentException 처리
    @ExceptionHandler(CommentException.class)
    public ResponseEntity<Object> handleCommentException(CommentException e){
        return handleExceptionInternal(e.getErrorCode());
    }

    // PostException 처리
    @ExceptionHandler(PostException.class)
    public ResponseEntity<Object> handlePostException(PostException e){
        return handleExceptionInternal(e.getErrorCode());
    }

    // 대부분의 에러 처리
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 대부분의 에러 처리 메세지를 보내기 위한 메소드
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }
    // 에러 처리 메세지를 만드는 메소드 분리
    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.warn("handleMethodArgumentNotValid", e);
        return handleMethodArgumentNotValidInternal(e, CommonErrorCode.INVALID_INPUT_VALUE);
    }

    private ResponseEntity<Object> handleMethodArgumentNotValidInternal(MethodArgumentNotValidException  e, ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    private ErrorResponse makeErrorResponse(MethodArgumentNotValidException  e, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
}
