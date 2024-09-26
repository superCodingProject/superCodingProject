package study.supercoding_1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(description = "사용자 이메일", nullable = false, example = "k12@gmail.com")
    private String email;
    @Schema(description = "사용자 비밀번호", nullable = false, example = "pwd123")
    private String password;
}
