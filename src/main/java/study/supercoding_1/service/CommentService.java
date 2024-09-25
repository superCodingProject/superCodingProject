package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.supercoding_1.dto.*;
import study.supercoding_1.entity.Comment;
import study.supercoding_1.entity.Posts;
import study.supercoding_1.repository.CommentRepository;
import study.supercoding_1.repository.PostsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public AddCommentResponse addComment(AddCommentRequest addCommentRequest){

        Posts post = postsRepository.findById(addCommentRequest.getPost_id())
                .orElseThrow(()->new RuntimeException("server error"));

        Comment newComment = Comment.builder()
                .content(addCommentRequest.getContent())
                .author(addCommentRequest.getAuthor())
                .post(post)
                .build();
        commentRepository.save(newComment);
        return new AddCommentResponse("댓글이 성공적으로 작성되었습니다.");
    }

    @Transactional
    public List<GetCommentResponse> getCommentList(){
        List<Comment> commentList = commentRepository.findAll();
        return commentList.stream().map(c->c.entityToGetCommentResponse()).toList();
    }

    @Transactional
    public UpdateCommentResponse updateComment(Long commentId, UpdateCommentRequest request){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("없는 comment 입니다"));

        commentRepository.save(comment.updateContent(request));

        return UpdateCommentResponse.builder()
                .message("댓글이 성공적으로 수정되었습니다.").build();
    }

    @Transactional
    public DeleteCommentResponse deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("없는 comment 입니다"));

        commentRepository.delete(comment);
        return DeleteCommentResponse.builder()
                .message("댓글이 성공적으로 삭제되었습니다.").build();
    }
}
