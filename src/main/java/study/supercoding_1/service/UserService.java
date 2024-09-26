package study.supercoding_1.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.supercoding_1.dto.UserDto;
import study.supercoding_1.entity.User;
import study.supercoding_1.repository.UserRepository;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Set<String> blacklistTokens = new HashSet<>();

    /*
    DTO 객체를 Entity로 바꿔주는 메소드
     */
    public Map<String,String> joinProcess(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Map<String, String> map = new HashMap<>();

        // DB에 유저가 있는지 체크
        Boolean isExist = userRepository.existsByEmail(email);

        // 동일한 이메일이 있을 경우
        if(isExist){
            map.put("message","동일한 이메일이 있습니다.");
            return map;
        }

        // DTO를 Entity로 초기화
        User user = new User();
        user.setEmail(email);
        // 비밀번호 암호화 (직접 지정한 메소드)
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_ADMIN");
        //user.setCreate_at();

        userRepository.save(user);
        map.put("message","회원가입이 성공적으로 완료되었습니다.");
        return map;
    }

    /*
    로그아웃시 token을 블랙리스트에 추가시켜 토큰 소멸 시킴
     */
    public Map<String,String> logoutProcess(String authorization) {
        Map<String, String> map = new HashMap<>();
        if(authorization == null){
            map.put("message", "유효하지 않은 토큰입니다.");
            return map;
        }

        String token = authorization.split(" ")[1];
        blacklistTokens.add(token);
        log.info(token);
        map.put("message", "로그아웃되었습니다.");
        return map;
    }
}



























