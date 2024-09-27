package study.supercoding_1.repository;

import org.springframework.data.repository.CrudRepository;
import study.supercoding_1.entity.Like;
import study.supercoding_1.entity.Post;
import study.supercoding_1.entity.User;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Integer> {
    Optional<Like> findByUserAndPost(User user, Post post);
}
