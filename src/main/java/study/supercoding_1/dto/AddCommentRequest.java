package study.supercoding_1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AddCommentRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요")
    @Size(min = 2, message = "내용은 2글자 이상이어야 합니다.")
    private String content;
    @NotBlank(message = "댓글 작성자를 입력해주세요")
    private String author;
    @JsonProperty("post_id")
    private int postId;
}
