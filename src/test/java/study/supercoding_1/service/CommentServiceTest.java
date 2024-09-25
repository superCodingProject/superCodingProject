package study.supercoding_1.service;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.supercoding_1.dto.AddCommentDTO;
import study.supercoding_1.entity.Comment;
import study.supercoding_1.entity.Posts;
import study.supercoding_1.repository.PostsRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostsRepository postsRepository;

    @Test
    void addComment() {
        //given
        String cContent = "내용입니다1";
        String cAuthor = "홍길동";
        Posts posts = Posts.builder()
                .title("포스트제목")
                .content("포스트내용입니다.")
                .author("이순신")
                .build();
        Posts savedPost = postsRepository.save(posts);

        AddCommentDTO addCommentDTO = new AddCommentDTO(cContent,cAuthor,String.valueOf(savedPost.getId()));

        //when
        Comment savedComment = commentService.addComment(addCommentDTO);
        log.info("댓글 내용 = {}",savedComment.getContent());

        //then
        Assertions.assertThat(savedComment.getAuthor()).isEqualTo(cAuthor);


    }
}