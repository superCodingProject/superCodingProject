package study.supercoding_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.supercoding_1.service.LikeService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class likeController {

    private final LikeService likeService;

}
