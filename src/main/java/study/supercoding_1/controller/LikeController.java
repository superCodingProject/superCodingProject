package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.LikeCountResponse;
import study.supercoding_1.dto.LikeRequest;
import study.supercoding_1.dto.LikeResponse;
import study.supercoding_1.service.LikeService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like/{post_id}")
    public ResponseEntity<LikeCountResponse> getPostLikeCount(@PathVariable(name = "post_id") int postId) {
        LikeCountResponse response = likeService.getPostLikeCount(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/like/{post_id}")
    public ResponseEntity<LikeResponse> addOrCancelLike(@PathVariable(name = "post_id") int postId, @RequestBody LikeRequest request) {
        LikeResponse response = likeService.addOrCancelLike(postId, request);
        return ResponseEntity.ok(response);
    }

}
