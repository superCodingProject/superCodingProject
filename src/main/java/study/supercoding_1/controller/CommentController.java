package study.supercoding_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.AddCommentRequest;
import study.supercoding_1.dto.CommentResponse;
import study.supercoding_1.dto.GetCommentResponse;
import study.supercoding_1.dto.UpdateCommentRequest;
import study.supercoding_1.exception.response.ErrorResponse;
import study.supercoding_1.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "댓글 컨트롤러",description = "댓글 작성, 조회, 수정, 삭제 API 입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    @Operation(summary = "댓글 작성",description = "새로운 댓글을 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "댓글이 성공적으로 작성되었습니다."
                    ,content = @Content(schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404",description = "게시글을 찾을 수 없습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500",description = "댓글 저장에 실패했습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<CommentResponse> addComment(@RequestBody @Valid AddCommentRequest addCommentRequest){
        return ResponseEntity.ok(commentService.addComment(addCommentRequest));
    }

    @GetMapping("/comments")
    @Operation(summary = "전체 댓글 조회",description = "전체 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200",description = "{\n" + "  \"comments\": [\n" +
            "    {\n" + "      \"id\": 1,\n" + "      \"content\": \"댓글 내용\",\n" +
            "      \"author\": \"testman1234@gmail.com\",\n" + "      \"post_id\": 1,\n" +
            "      \"created_at\": \"2024.09.26 23:33:20\"\n" + "    }" + "  ]\n" + "}"
            ,content = @Content(schema = @Schema(implementation = GetCommentResponse.class)))
    public ResponseEntity<Map<String, List<GetCommentResponse>>> getCommentList(){
        List<GetCommentResponse> comments = commentService.getCommentList();
        Map<String, List<GetCommentResponse>> response = new HashMap<>();
        response.put("comments",comments);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/comments/{comment_id}")
    @Operation(summary = "댓글 수정",description = "댓글 하나를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "댓글이 성공적으로 수정되었습니다."
                    ,content = @Content(schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404",description = "댓글을 찾을 수 없습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500",description = "댓글 수정에 실패했습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<CommentResponse> updateComment(@PathVariable(name = "comment_id") Long commentId,
                                                         @RequestBody @Valid UpdateCommentRequest request){
        return ResponseEntity.ok(commentService.updateComment(commentId,request));
    }

    @DeleteMapping("/comments/{comment_id}")
    @Operation(summary = "댓글 삭제",description = "댓글 하나를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "댓글이 성공적으로 삭제되었습니다."
                    ,content = @Content(schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404",description = "댓글을 찾을 수 없습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500",description = "댓글 삭제에 실패했습니다."
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable(name = "comment_id") Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
