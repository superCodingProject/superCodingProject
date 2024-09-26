package study.supercoding_1.dto;

import lombok.Builder;
import lombok.Data;
import study.supercoding_1.entity.Post;

@Data
@Builder
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String author;

    public Post toEntity(PostDto postDto) {
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
