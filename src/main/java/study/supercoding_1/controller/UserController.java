package study.supercoding_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.UserDto;
import study.supercoding_1.service.UserService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "jwt 이슈( /api/login요청은 컨트롤러를 거치지 않기 때문. )로 /login 요청은 postman을 통해 토큰을 발행해 주세요.")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입",description = "email(primary key)과 password를 통해 회원가입을 실행합니다.")
    public ResponseEntity<Map<String,String>> joinProcess(@RequestBody UserDto userDto) {
        Map<String,String> message = userService.joinProcess(userDto);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/logout")
    @ApiResponse(responseCode = "500", description = "서버오류 발생 (유효한 토큰인지 확인해 주세요. ex: 만료된 토큰이거나 잘못된 형식의 토큰일 가능성이 있습니다.)")
    @Operation(summary = "로그아웃",description = "로그아웃 처리는 서버쪽에서 token을 블랙리스트 처리 했습니다.")
    public ResponseEntity<Map<String,String>> logoutProcess(@RequestHeader("Authorization") String authorization) {
        log.info(authorization);
        Map<String,String> message = userService.logoutProcess(authorization);
        return ResponseEntity.ok(message);
    }
}
