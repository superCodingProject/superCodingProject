package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.PostDto;
import study.supercoding_1.dto.PostResponseDto;
import study.supercoding_1.service.PostService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> postsave(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.postsave(postDto));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponseDto> findAll() {
        List<PostDto> postDtoList = postService.findAll();

        PostResponseDto response = new PostResponseDto(postDtoList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<PostResponseDto> findByEmail(@RequestParam("author_email") String author) {
        List<PostDto> postDtos = postService.findByEmail(author);  // Directly get the list of PostDto
        PostResponseDto response = new PostResponseDto(postDtos);  // Pass the list to PostResponseDto
        return ResponseEntity.ok(response);
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
