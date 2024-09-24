package study.supercoding_1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@NoArgsConstructor
//@ToString(exclude = {"id","user","post"})
public class Comment extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    private String author;

    @Builder
    public Comment(String content, String author) {
        this.content = content;
        this.author = author;
    }


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
}
