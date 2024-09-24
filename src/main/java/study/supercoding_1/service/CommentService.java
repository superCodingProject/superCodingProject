package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.supercoding_1.dto.AddCommentDTO;
import study.supercoding_1.entity.Comment;
import study.supercoding_1.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(AddCommentDTO addCommentDTO){
        Comment newComment = Comment.builder()
                .content(addCommentDTO.getContent())
                .author(addCommentDTO.getAuthor())
                .build();
        commentRepository.save(newComment);
    }
}
