package study.supercoding_1.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.supercoding_1.entity.Post;
import study.supercoding_1.repository.PostRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private LikeService likeService;

    private Post post;

    @BeforeEach
    public void setUp() {
        post = new Post();
        post.setId(1);
        post.setLikeCount(5); // 원하는 likeCount 값을 설정
    }

    @Test
    public void testGetPostLikeCount_Exists() {
        // Given
        int postId = 1;
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // When
        Integer likeCount = likeService.getPostLikeCount(postId).getLikeCount();

        // Then
        assertEquals(3, likeCount);
        verify(postRepository, times(1)).findById(postId); // 메서드 호출 검증
    }


}
