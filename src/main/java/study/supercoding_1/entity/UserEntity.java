package study.supercoding_1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "create_at")
    private Date create_at;
    @Column(name = "role")
    private String role;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
        this.create_at = new Date();
    }
}
