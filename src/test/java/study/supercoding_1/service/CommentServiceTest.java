package study.supercoding_1.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.supercoding_1.dto.*;
import study.supercoding_1.entity.Comment;
import study.supercoding_1.entity.Post;
import study.supercoding_1.repository.CommentRepository;
import study.supercoding_1.repository.PostRepository;

import java.util.List;

@SpringBootTest
@Slf4j
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostRepository PostRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void addComment() {
        //given
        String cContent = "내용입니다1";
        String cAuthor = "홍길동";
        Post post = study.supercoding_1.entity.Post.builder()
                .title("포스트제목")
                .content("포스트내용입니다.")
                .author("이순신")
                .build();
        Post savedPost = PostRepository.save(post);

        AddCommentRequest addCommentRequest = new AddCommentRequest(cContent,cAuthor,savedPost.getId());

        //when
        CommentResponse addCommentResponse = commentService.addComment(addCommentRequest);

        //then
        log.info("message = {}",addCommentResponse);
        Assertions.assertThat(cAuthor).isEqualTo("홍길동");

    }

    @Test
    void getCommentList(){

        Post post = study.supercoding_1.entity.Post.builder()
                .title("포스트제목")
                .content("포스트내용입니다.")
                .author("이순신")
                .build();
        Post savedPost = PostRepository.save(post);
        String cContent1 = "내용입니다1";
        String cContent2 = "내용입니다2";
        String cContent3 = "내용입니다3";
        String cAuthor1 = "홍길동1";
        String cAuthor2 = "홍길동2";
        String cAuthor3 = "홍길동3";

        AddCommentRequest addCommentRequest1 = new AddCommentRequest(cContent1,cAuthor1,savedPost.getId());
        AddCommentRequest addCommentRequest2 = new AddCommentRequest(cContent2,cAuthor2,savedPost.getId());
        AddCommentRequest addCommentRequest3 = new AddCommentRequest(cContent3,cAuthor3,savedPost.getId());

        commentService.addComment(addCommentRequest1);
        commentService.addComment(addCommentRequest2);
        commentService.addComment(addCommentRequest3);
        List<GetCommentResponse> getCommentResponses = commentService.getCommentList();
        for (GetCommentResponse comment : getCommentResponses){
            log.info("댓글 = {}",comment);
        }

        Assertions.assertThat(getCommentResponses.size()).isEqualTo(3);

    }

    @Test
    void updateComment(){
        String cContent = "내용입니다1";
        String cAuthor = "홍길동";
        Long commentId = 1L;

        Post post = study.supercoding_1.entity.Post.builder()
                .title("포스트제목")
                .content("포스트내용입니다.")
                .author("이순신")
                .build();
        Post savedPost = PostRepository.save(post);

        AddCommentRequest addCommentRequest = new AddCommentRequest(cContent,cAuthor,savedPost.getId());
        UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest("수정된 내용입니다.");

        commentService.addComment(addCommentRequest);

        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("server error"));

        CommentResponse response = commentService.updateComment(findComment.getId(),updateCommentRequest);

        Assertions.assertThat(response.getMessage()).isEqualTo("댓글이 성공적으로 수정되었습니다.");
    }

    @Test
    void deleteComment(){
        String cContent = "내용입니다1";
        String cAuthor = "홍길동";
        Long commentId = 1L;
        Post post = study.supercoding_1.entity.Post.builder()
                .title("포스트제목")
                .content("포스트내용입니다.")
                .author("이순신")
                .build();
        Post savedPost = PostRepository.save(post);

        AddCommentRequest addCommentRequest = new AddCommentRequest(cContent,cAuthor,savedPost.getId());
        commentService.addComment(addCommentRequest);

        CommentResponse response = commentService.deleteComment(commentId);

        Assertions.assertThat(response.getMessage()).isEqualTo("댓글이 성공적으로 삭제되었습니다.");
    }


}