package study.supercoding_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.supercoding_1.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // 유저가 존재하는지 email로 체크하는 메소드
    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
