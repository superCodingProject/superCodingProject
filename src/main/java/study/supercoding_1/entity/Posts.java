package study.supercoding_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Posts")
public class Posts { // 일대 다 = 회원에서는 게시글을 여러개 사용할 수 있다 or 게시글이 없을 수 도 있다

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String title;

    @Column(length = 300)
    private String content;

    @Column(length = 50)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY) //  many 쪽이 다 관계
    @JoinColumn(name = "user_id")
    private User user;




}
