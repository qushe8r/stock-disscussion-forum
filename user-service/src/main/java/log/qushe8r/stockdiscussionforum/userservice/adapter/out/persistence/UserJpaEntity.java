package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserRole;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "us_users")
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "nickname", nullable = false, length = 16)
	private String nickname;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "bio", columnDefinition = "Text")
	private String bio;

	@Column(name = "profile_img_url", length = 128)
	private String profileImageUrl;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_status", nullable = false, length = 16)
	private UserStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", nullable = false, length = 16)
	private UserRole role;

	public UserJpaEntity(String username, String nickname, String password, String bio, String profileImageUrl,
		UserStatus status, UserRole role) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.bio = bio;
		this.profileImageUrl = profileImageUrl;
		this.status = status;
		this.role = role;
	}

	public void modify(UserJpaEntity userJpaEntity) {
		String newNickname = userJpaEntity.getNickname();
		String newBio = userJpaEntity.getBio();
		String newProfileImageUrl = userJpaEntity.getProfileImageUrl();
		UserStatus newStatus = userJpaEntity.getStatus();
		UserRole newRole = userJpaEntity.getRole();

		if (newNickname != null) {
			this.nickname = newNickname;
		}
		if (newBio != null) {
			this.bio = newBio;
		}
		if (newProfileImageUrl != null) {
			this.profileImageUrl = newProfileImageUrl;
		}
		if (newStatus != null) {
			this.status = newStatus;
		}
		if (newRole != null) {
			this.role = newRole;
		}
	}

}
