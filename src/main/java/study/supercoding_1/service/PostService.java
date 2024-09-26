package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.supercoding_1.dto.PostDto;
import study.supercoding_1.entity.Post;
import study.supercoding_1.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Object postsave(PostDto postDto) {
        Post post = postDto.toEntity(postDto);
        postRepository.save(post);
        return post;
    }

    public List<PostDto> findAll() {
        List<Post> postEntityList = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : postEntityList) {
            postDtoList.add(post.toDto()); // 어차피 내안에 있는 메서드 사용할건데 나를 매개변수로 줄 필요 없다
        }
        return postDtoList;
    }

    public List<PostDto> findByEmail(String email) {
        List<Post> posts = postRepository.findByUser_Email(email);
        return posts.stream().map(Post::toDto).collect(Collectors.toList());
    }

    public void update(int id, PostDto postDto) {
        // 기존 게시글 찾기
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 업데이트할 내용만 적용
        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        existingPost.setAuthor(postDto.getAuthor());

        // 변경된 내용 저장
        postRepository.save(existingPost);
    }


    public void delete(int id) {
        postRepository.deleteById(id);
    }
}
