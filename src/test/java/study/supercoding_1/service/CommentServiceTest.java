package study.supercoding_1.service;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.supercoding_1.dto.AddCommentDTO;
import study.supercoding_1.dto.GetCommentResponse;
import study.supercoding_1.entity.Comment;
import study.supercoding_1.entity.Posts;
import study.supercoding_1.repository.PostsRepository;

import java.util.List;

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

    @Test
    void getCommentList(){

        Posts posts = Posts.builder()
                .title("포스트제목")
                .content("포스트내용입니다.")
                .author("이순신")
                .build();
        Posts savedPost = postsRepository.save(posts);
        String cContent1 = "내용입니다1";
        String cContent2 = "내용입니다2";
        String cContent3 = "내용입니다3";
        String cAuthor1 = "홍길동1";
        String cAuthor2 = "홍길동2";
        String cAuthor3 = "홍길동3";

        AddCommentDTO addCommentDTO1 = new AddCommentDTO(cContent1,cAuthor1,String.valueOf(savedPost.getId()));
        AddCommentDTO addCommentDTO2 = new AddCommentDTO(cContent2,cAuthor2,String.valueOf(savedPost.getId()));
        AddCommentDTO addCommentDTO3 = new AddCommentDTO(cContent3,cAuthor3,String.valueOf(savedPost.getId()));

        commentService.addComment(addCommentDTO1);
        commentService.addComment(addCommentDTO2);
        commentService.addComment(addCommentDTO3);
        List<GetCommentResponse> getCommentResponses = commentService.getCommentList();
        for (GetCommentResponse comment : getCommentResponses){
            log.info("댓글 = {}",comment);
        }

        Assertions.assertThat(getCommentResponses.size()).isEqualTo(3);

    }
}