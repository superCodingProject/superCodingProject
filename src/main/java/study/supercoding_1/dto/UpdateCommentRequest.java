package study.supercoding_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentRequest {
    @NotBlank(message = "수정할 내용을 입력해주세요")
    @Size(min = 2, message = "내용은 2글자 이상이어야 합니다.")
    private String content;
}
