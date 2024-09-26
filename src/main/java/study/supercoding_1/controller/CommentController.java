package study.supercoding_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.*;
import study.supercoding_1.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    @Operation(summary = "댓글 작성",description = "새로운 댓글을 작성합니다.")
    public ResponseEntity<CommentResponse> addComment(@RequestBody @Valid AddCommentRequest addCommentRequest){
        return ResponseEntity.ok(commentService.addComment(addCommentRequest));
    }

    @GetMapping("/comments")
    @Operation(summary = "전체 댓글 조회",description = "전체 댓글을 조회합니다.")
    public ResponseEntity<Map<String, List<GetCommentResponse>>> getCommentList(){
        List<GetCommentResponse> comments = commentService.getCommentList();
        Map<String, List<GetCommentResponse>> response = new HashMap<>();
        response.put("comments",comments);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/comments/{comment_id}")
    @Operation(summary = "댓글 수정",description = "댓글 하나를 수정합니다.")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable(name = "comment_id") Long commentId,
                                                         @RequestBody @Valid UpdateCommentRequest request){
        return ResponseEntity.ok(commentService.updateComment(commentId,request));
    }

    @DeleteMapping("/comments/{comment_id}")
    @Operation(summary = "댓글 삭제",description = "댓글 하나를 삭제합니다.")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable(name = "comment_id") Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
