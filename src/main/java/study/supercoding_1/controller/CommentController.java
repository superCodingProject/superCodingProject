package study.supercoding_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.supercoding_1.dto.AddCommentDTO;
import study.supercoding_1.service.CommentService;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @ResponseBody
    @PostMapping("/comments")
    @Operation(summary = "댓글 작성",description = "새로운 댓글을 작성합니다.")
    public ResponseEntity<String> addComment(@RequestBody AddCommentDTO addCommentDTO){
        commentService.addComment(addCommentDTO);
        return ResponseEntity.ok("성공");
    }

}
