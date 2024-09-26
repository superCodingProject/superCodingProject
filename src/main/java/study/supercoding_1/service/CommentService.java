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
import study.supercoding_1.exception.errorcode.CommonErrorCode;
import study.supercoding_1.exception.exception.CommentException;
import study.supercoding_1.exception.exception.ResourceNotFoundException;
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
                .orElseThrow(()->new ResourceNotFoundException(CommonErrorCode.POST_NOT_FOUND));

        Comment savedComment = commentRepository.save(Comment.createComment(addCommentRequest,post));

        if (savedComment.getId() > 0){
            return CommentResponse.builder()
                    .message("댓글이 성공적으로 작성되었습니다.").build();
        }else {
            throw new CommentException(CommonErrorCode.COMMENT_SAVE_FAILURE);
        }
    }

    @Transactional
    public List<GetCommentResponse> getCommentList(){
        List<Comment> commentList = commentRepository.findAll();
        return commentList.stream().map(Comment::entityToGetCommentResponse).toList();
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, UpdateCommentRequest request){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException(CommonErrorCode.COMMENT_NOT_FOUND));

        Comment savedComment = commentRepository.save(comment.updateContent(request));

        if (savedComment.getId() > 0){
            return CommentResponse.builder()
                    .message("댓글이 성공적으로 수정되었습니다.").build();
        }else {
            throw new CommentException(CommonErrorCode.COMMENT_UPDATE_FAILURE);
        }

    }

    @Transactional
    public CommentResponse deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException(CommonErrorCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);

        if (commentRepository.findById(commentId).isEmpty()){
            return CommentResponse.builder()
                    .message("댓글이 성공적으로 삭제되었습니다.").build();
        }else {
            throw new CommentException(CommonErrorCode.COMMENT_DELETE_FAILURE);
        }

    }
}
