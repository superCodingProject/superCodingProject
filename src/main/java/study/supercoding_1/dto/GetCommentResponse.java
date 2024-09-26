package study.supercoding_1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class GetCommentResponse {

    @Schema(description = "댓글의 ID 번호",example = "1")
    private Long id;

    @Schema(description = "댓글의 내용",example = "작성한 댓글의 내용입니다!")
    private String content;

    @Schema(description = "댓글 작성자",example = "testman1234@gmail.com")
    private String author;

    @Schema(description = "댓글이 들어간 게시물의 ID", example = "1")
    @JsonProperty("post_id")
    private Long postId;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
    @Schema(description = "댓글 작성일자",example = "2024.09.26 23:33:20")
    private LocalDateTime createdAt;
}
