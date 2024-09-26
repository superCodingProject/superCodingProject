package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.supercoding_1.service.LikeService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like/{post_id}")
    public ResponseEntity<Integer> getPostLikeCount(@PathVariable(name = "post_id") int postId) {
        Integer response = likeService.getPostLikeCount(postId);
        return ResponseEntity.ok(response);
    }

}
