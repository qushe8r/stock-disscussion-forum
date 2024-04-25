package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserRole;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String nickname;
    private String password;
    private String bio;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserJpaEntity(String username, String nickname, String password, String bio, String profileImageUrl, UserStatus status, UserRole role) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.status = status;
        this.role = role;
    }
}
