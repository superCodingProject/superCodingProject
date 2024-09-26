package study.supercoding_1.entity;

import jakarta.persistence.*;
import lombok.*;
import study.supercoding_1.dto.PostDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Posts")
public class Post extends BaseTimeEntity {

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

    public PostDto toDto() {
        return PostDto.builder()
                .id(getId())
                .title(getTitle()) // this 생략
                .content(getContent())
                .author(getAuthor())
                .build();
    }


//    @Builder // 빌더 사용법 .
//    public Post(String title, String content, String author) {
//        this.title = title;
//        this.content = content;
//        this.author = author;
//    }




}
