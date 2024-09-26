package study.supercoding_1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    @Schema(description = "댓글 CRUD 성공 메시지",example = "댓글이 성공적으로 작성되었습니다.")
    private String message;
}
