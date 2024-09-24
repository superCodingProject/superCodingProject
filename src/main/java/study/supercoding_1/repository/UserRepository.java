package study.supercoding_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.supercoding_1.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // 유저가 존재하는지 email로 체크하는 메소드
    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
