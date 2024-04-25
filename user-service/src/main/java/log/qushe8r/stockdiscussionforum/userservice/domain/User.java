package log.qushe8r.stockdiscussionforum.userservice.domain;

import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserRole;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String bio;
    private String profileImageUrl;
    private UserStatus status;
    private UserRole role;

    public static User create(
            Long id,
            String username,
            String nickname,
            String password,
            String bio,
            String profileImageUrl,
            UserStatus userStatus,
            UserRole userRole
    ) {
        return new User(
                id,
                username,
                nickname,
                password,
                bio,
                profileImageUrl,
                userStatus,
                userRole
        );
    }

    public void modifyUserInformation(String newNickname, String newBio, String newProfileImageUrl) {
        if (newNickname != null) {
            this.nickname = newNickname;
        }
        if (newBio != null) {
            this.bio = newBio;
        }
        if (newProfileImageUrl != null) {
            this.profileImageUrl = newProfileImageUrl;
        }
    }

    public void modifyUserPassword(String newPassword) {
        this.password = newPassword;
    }

    public void modifyUserStatus(UserStatus newStatus) {
        this.status = newStatus;
    }

    public void modifyUserRole(UserRole newRole) {
        this.role = newRole;
    }

}
