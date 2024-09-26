package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.supercoding_1.entity.Post;
import study.supercoding_1.exception.errorcode.CommonErrorCode;
import study.supercoding_1.exception.exception.PostException;
import study.supercoding_1.repository.LikeRepository;
import study.supercoding_1.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public Integer getPostLikeCount(int postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(CommonErrorCode.POST_NOT_FOUND));
        log.info(String.valueOf(post.getLikeCount()));
        return post.getLikeCount();
    }
}
