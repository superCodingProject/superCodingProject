package study.supercoding_1.entity;

import jakarta.persistence.*;
import lombok.*;
import study.supercoding_1.dto.AddCommentRequest;
import study.supercoding_1.dto.GetCommentResponse;
import study.supercoding_1.dto.UpdateCommentRequest;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@ToString(exclude = {"id","user","post"})
public class Comment extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    private String author;

    @Builder
    public Comment(String content, String author,Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }
    @Builder
    public Comment(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public GetCommentResponse entityToGetCommentResponse(){
        return GetCommentResponse.builder()
                .id(this.getId())
                .content(this.getContent())
                .author(this.getAuthor())
                .postId((long) this.getPost().getId())
                .createdAt(this.getCreatedAt()).build();
    }

    public static Comment createComment(AddCommentRequest request, Post post){
        return Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .post(post)
                .build();
    }

    public Comment updateContent(UpdateCommentRequest request){
        this.content = request.getContent();
        return this;
    }

}
