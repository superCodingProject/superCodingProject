package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercoding_1.dto.UserDto;
import study.supercoding_1.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> joinProcess(@RequestBody UserDto userDto) {
        Map<String,String> message = userService.joinProcess(userDto);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String,String>> logoutProcess(@RequestHeader("Authorization") String authorization) {
        log.info(authorization);
        Map<String,String> message = userService.logoutProcess(authorization);
        return ResponseEntity.ok(message);
    }
}
