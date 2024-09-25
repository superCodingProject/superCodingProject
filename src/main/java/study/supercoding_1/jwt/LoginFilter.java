package study.supercoding_1.jwt;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.supercoding_1.dto.CustomUserDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/login"); // 로그인 URL을 설정합니다.
    }

    private String[] obtainCredentials(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String line;

        // JSON 본문을 읽기
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("읽기 실패", e);
        }

        // 요청 본문을 JSON으로 파싱
        String requestBody = sb.toString();
        JsonObject jsonObject = Optional.of(requestBody)
                .map(body -> {
                    try {
                        return JsonParser.parseString(body).getAsJsonObject();
                    } catch (JsonSyntaxException e) {
                        return null;
                    }
                })
                .orElse(null);

        // 이메일과 비밀번호 추출
        String email = Optional.ofNullable(jsonObject)
                .map(json -> json.has("email") ? json.get("email").getAsString() : null)
                .orElseThrow(() -> new IllegalArgumentException("email 정보가 없습니다."));

        String password = Optional.ofNullable(jsonObject)
                .map(json -> json.has("password") ? json.get("password").getAsString() : null)
                .orElseThrow(() -> new IllegalArgumentException("password 정보가 없습니다."));

        return new String[] { email, password };
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String[] credentials = obtainCredentials(request);
        String email = credentials[0];
        String password = credentials[1];

        // 스프링 시큐리티에서 email과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);

        // authenticationManager 에게 넘겨서 검증을 받아야 함
        return authenticationManager.authenticate(authToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = customUserDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        GrantedAuthority auth = authorities.iterator().next();
        String role = auth.getAuthority();

        // JWT 생성 및 오류 처리
        String token = Optional.of(jwtUtil.createJwt(email, role, 60 * 60 * 10L))
                .orElseThrow(() -> new RuntimeException("토큰 생성 실패"));

        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
        log.error("유저 인증 실패");
    }
}
