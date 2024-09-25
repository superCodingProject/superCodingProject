package study.supercoding_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.supercoding_1.entity.Posts;
@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {
}
