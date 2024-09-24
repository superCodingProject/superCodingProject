package study.supercoding_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.supercoding_1.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
