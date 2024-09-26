package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.supercoding_1.dto.AddCommentRequest;
import study.supercoding_1.dto.CommentResponse;
import study.supercoding_1.dto.GetCommentResponse;
import study.supercoding_1.dto.UpdateCommentRequest;
import study.supercoding_1.entity.Comment;
import study.supercoding_1.entity.Post;
import study.supercoding_1.repository.CommentRepository;
import study.supercoding_1.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponse addComment(AddCommentRequest addCommentRequest){

        Post post = postRepository.findById(addCommentRequest.getPostId())
                .orElseThrow(()->new RuntimeException("server error"));

        Comment savedComment = commentRepository.save(Comment.createComment(addCommentRequest,post));
        if (savedComment.getId() > 0){
            return CommentResponse.builder()
                    .message("댓글이 성공적으로 작성되었습니다.").build();
        }else {
            throw new RuntimeException("댓글 작성에 실패했습니다.");
        }
    }

    @Transactional
    public List<GetCommentResponse> getCommentList(){
        List<Comment> commentList = commentRepository.findAll();
        return commentList.stream().map(c->c.entityToGetCommentResponse()).toList();
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, UpdateCommentRequest request){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("없는 comment 입니다"));

        commentRepository.save(comment.updateContent(request));

        return CommentResponse.builder()
                .message("댓글이 성공적으로 수정되었습니다.").build();
    }

    @Transactional
    public CommentResponse deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("없는 comment 입니다"));

        commentRepository.delete(comment);
        return CommentResponse.builder()
                .message("댓글이 성공적으로 삭제되었습니다.").build();
    }
}
