package study.supercoding_1.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode{
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드입니다."),
    CONFLICT(HttpStatus.CONFLICT, "데이터 충돌이 발생했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "서비스를 이용할 수 없습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    INVALID_USER_CREDENTIALS(HttpStatus.UNAUTHORIZED, "잘못된 인증 정보입니다."),
    DUPLICATE_POST_TITLE(HttpStatus.CONFLICT, "중복된 게시글 제목입니다."),
    COMMENT_SAVE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 저장에 실패했습니다."),
    COMMENT_UPDATE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 수정에 실패했습니다."),
    COMMENT_DELETE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 삭제에 실패했습니다."),
    POST_SAVE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "게시글 저장에 실패했습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "유효하지 않은 입력 값입니다."),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "인증 토큰이 만료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
