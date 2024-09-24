package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.supercoding_1.dto.UserDto;
import study.supercoding_1.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody UserDto userDto) {
        userService.joinProcess(userDto);
        return "ok";
    }
}
