package study.supercoding_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCommentRequest {
    private String content;
    private String author;
    private int post_id;
}
