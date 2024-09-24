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

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();

        // 이메일과 비밀번호 추출
        String email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : null;
        String password = jsonObject.has("password") ? jsonObject.get("password").getAsString() : null;

        return new String[] { email, password };
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String[] credentials = obtainCredentials(request);
        String email = credentials[0];
        String password = credentials[1];

        String s = obtainUsername(request);

        // 스프링 시큐리티에서 email과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);

        // authenticationManager 에게 넘겨서 검증을 받아야 함
            return authenticationManager.authenticate(authToken);

    }

    /*
    인증이 성공하면 실행되는 메소드
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication){

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String email = customUserDetails.getUsername();

        // role을 뽑아옴
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        // email & role & 토큰이 살아있는 시간을 넣어서 token을 만듬
        String token = jwtUtil.createJwt(email,role, 60 * 60 * 10L);

        // 키 : Authorization jwt데이터는 "Bearer(접두사) " 띄어쓰고 token을 넣어줘야함
        response.addHeader("Authorization", "Bearer " + token);

    }

    /*
    인증이 실패하면 실행되는 메소드
     */
    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        response.setStatus(401);
    }
}
