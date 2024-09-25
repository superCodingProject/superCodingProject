package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.supercoding_1.dto.AddCommentDTO;
import study.supercoding_1.dto.GetCommentResponse;
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
    public Comment addComment(AddCommentDTO addCommentDTO){

        Posts post = postsRepository.findById(Integer.valueOf(addCommentDTO.getPost_id()))
                .orElseThrow(()->new RuntimeException("server error"));

        Comment newComment = Comment.builder()
                .content(addCommentDTO.getContent())
                .author(addCommentDTO.getAuthor())
                .post(post)
                .build();

        return commentRepository.save(newComment);
    }

    public List<GetCommentResponse> getCommentList(){
        List<Comment> commentList = commentRepository.findAll();
        return commentList.stream().map(c->c.entityToGetCommentResponse()).toList();
    }

}
