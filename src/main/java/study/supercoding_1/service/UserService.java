package study.supercoding_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.supercoding_1.dto.UserDto;
import study.supercoding_1.entity.User;
import study.supercoding_1.repository.UserRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    DTO 객체를 Entity로 바꿔주는 메소드
     */
    public void joinProcess(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        // DB에 유저가 있는지 체크
        Boolean isExist = userRepository.existsByEmail(email);

        // 동일한 이메일이 있을 경우
        if(isExist){

            return;
        }

        // DTO를 Entity로 초기화
        User user = new User();
        user.setEmail(email);
        // 비밀번호 암호화 (직접 지정한 메소드)
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_ADMIN");
        //user.setCreate_at();

        userRepository.save(user);
    }
}
