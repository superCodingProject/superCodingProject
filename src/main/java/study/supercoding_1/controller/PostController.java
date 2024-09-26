package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.PostDto;
import study.supercoding_1.dto.PostResponseDto;
import study.supercoding_1.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> postsave(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.postsave(postDto));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponseDto> findAll() {
        List<PostDto> postDtoList = postService.findAll();

        PostResponseDto response = new PostResponseDto(postDtoList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/search/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        List<PostDto> postDtos = postService.findByEmail(email);
        return ResponseEntity.ok(postDtos);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PostDto postDto ) {
        postService.update(id , postDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        postService.delete(id);
        return ResponseEntity.ok(true);
    }
}
