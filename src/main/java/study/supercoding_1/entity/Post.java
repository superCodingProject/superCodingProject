package study.supercoding_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;
import study.supercoding_1.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
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

    public LocalDateTime getCreateAt() {
        return super.getCreatedAt();
    }

    @ManyToOne(fetch = FetchType.LAZY) //  many 쪽이 다 관계
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "like_count")
    @ColumnDefault("0")
    private Integer likeCount; // 기본값 0으로 설정

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private List<Like> likes;

    public PostDto toDto() {
        return PostDto.builder()
                .id(getId())
                .title(getTitle()) // this 생략
                .content(getContent())
                .author(getAuthor())
                .createdAt(getCreatedAt())
                .build();
    }


//    @Builder // 빌더 사용법 .
//    public Post(String title, String content, String author) {
//        this.title = title;
//        this.content = content;
//        this.author = author;
//    }

    // 좋아요 증가 메서드
    public void incrementLikeCount() {
        this.likeCount++;
    }

    // 좋아요 취소 메서드
    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
}
