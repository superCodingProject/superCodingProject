package study.supercoding_1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    private String message;
}
