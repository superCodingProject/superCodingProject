package study.supercoding_1.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private SecretKey secretKey;

    // .yml 파일에 지정한 변수 가져오기
    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {

        if(secret == null || secret.isEmpty()){
            throw new IllegalArgumentException("secret 값이 비어있어 key 를 생성 할 수 없습니다.");
        }
        // 객체 타입으로 secretKey 만들기
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {
        // secretKey를 넣어서 검증 (String 타입에 email을 get)
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().get("email", String.class);
    }

    public String getRole(String token) {
        // secretKey를 넣어서 검증 (String 타입에 role을 get)
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        // secretKey를 넣어서 검증 (String 타입에 현재시간 get)
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().getExpiration().before(new Date());
    }

    /*
    token을 생성하는 메소드
     */
    public String createJwt(String email, String role, Long expiredMs){
        return Jwts.builder()
                .claim("role",role)
                // 현재 발행시간
                .issuedAt(new Date(System.currentTimeMillis()))
                // 언제 소멸될지
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                // 암호화 진행
                .signWith(secretKey)
                .compact();
    }
}















