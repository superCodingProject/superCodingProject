package study.supercoding_1.service;

import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.supercoding_1.dto.LikeCountResponse;
import study.supercoding_1.dto.LikeRequest;
import study.supercoding_1.dto.LikeResponse;
import study.supercoding_1.entity.Like;
import study.supercoding_1.entity.Post;
import study.supercoding_1.entity.User;
import study.supercoding_1.exception.errorcode.CommonErrorCode;
import study.supercoding_1.exception.exception.PostException;
import study.supercoding_1.exception.exception.UserException;
import study.supercoding_1.repository.LikeRepository;
import study.supercoding_1.repository.PostRepository;
import study.supercoding_1.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeCountResponse getPostLikeCount(int postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(CommonErrorCode.POST_NOT_FOUND));
        return LikeCountResponse.builder()
                .likeCount(post.getLikeCount())
                .build();
    }

    @Transactional
    public LikeResponse addOrCancelLike(int postId, LikeRequest request) {


        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(CommonErrorCode.POST_NOT_FOUND));

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new UserException(CommonErrorCode.USER_NOT_FOUND);
        }

        Optional<Like> likeOptional = likeRepository.findByUserAndPost(user,post);

        String answer = "";

        if (likeOptional.isPresent()) {
            post.decrementLikeCount();
            postRepository.save(post);
            likeRepository.delete(likeOptional.get());
            answer = "좋아요 삭제 성공";

        }
        else {
            post.incrementLikeCount();
            postRepository.save(post);
            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();
            likeRepository.save(like);
            answer = "좋아요 추가 성공";
        }
        return LikeResponse.builder()
                .message(answer)
                .build();

    }
}
