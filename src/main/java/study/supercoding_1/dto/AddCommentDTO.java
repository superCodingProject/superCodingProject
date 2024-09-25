package study.supercoding_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCommentDTO {
    private String content;
    private String author;
    private String post_id;
}
