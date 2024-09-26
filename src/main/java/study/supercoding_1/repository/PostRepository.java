package study.supercoding_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.supercoding_1.entity.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

//    @Query("select p from Post p ")
    List<Post> findByUser_Email(String email);// User 엔티티의 email로 필터링
}
