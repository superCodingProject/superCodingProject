package study.supercoding_1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AddCommentRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요")
    @Size(min = 2, message = "내용은 2글자 이상이어야 합니다.")
    @Schema(description = "작성할 댓글 내용",example = "정말 좋은 게시물입니다!")
    private String content;

    @NotBlank(message = "댓글 작성자를 입력해주세요")
    @Schema(description = "작성자의 이메일",example = "testman1234@gmail.com")
    private String author;

    @JsonProperty("post_id")
    @Schema(description = "해당 게시물의 ID",example = "1")
    private int postId;
}
