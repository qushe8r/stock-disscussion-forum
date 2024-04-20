package log.qushe8r.stockdiscussionforum.user.entity;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.common.audit.Auditing;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username; // email
    private String nickname;
    private String password;
    private String bio;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(Long id) {
        this.id = id;
    }

    public User(String username, String nickname, String password, String bio) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.bio = bio;
        this.profileImageUrl = "default";
        this.role = UserRole.ROLE_INACTIVE;
        this.status = UserStatus.PENDING;
    }

    public void modify(String nickname, String bio) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (bio != null) {
            this.bio = bio;
        }
    }

    public void updateStatus(UserStatus status) {
        this.status = status;
    }

    public void updateRole(UserRole role) {
        this.role = role;
    }
}
