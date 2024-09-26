package study.supercoding_1.repository;

import org.springframework.data.repository.CrudRepository;
import study.supercoding_1.entity.Like;

public interface LikeRepository extends CrudRepository<Like, Integer> {
}
