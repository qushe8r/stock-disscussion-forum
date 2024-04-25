package log.qushe8r.stockdiscussionforum.userservice.application.service;

import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationCommand;
import log.qushe8r.stockdiscussionforum.userservice.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toDomainEntity(UserJpaEntity jpaEntity) {
        return User.create(
                jpaEntity.getId(),
                jpaEntity.getUsername(),
                jpaEntity.getNickname(),
                jpaEntity.getPassword(),
                jpaEntity.getBio(),
                jpaEntity.getProfileImageUrl(),
                jpaEntity.getStatus(),
                jpaEntity.getRole()
        );
    }

    public UserJpaEntity toJpaEntity(UserRegistrationCommand command, String encodedPassword) {
        return new UserJpaEntity(
                command.getUsername(),
                command.getNickname(),
                encodedPassword,
                command.getBio(),
                command.getProfileImageUrl(),
                command.getUserStatus(),
                command.getUserRole()
        );
    }

    public UserJpaEntity toJpaEntity(User user) {
        return new UserJpaEntity(
                user.getUsername(),
                user.getNickname(),
                user.getPassword(),
                user.getBio(),
                user.getProfileImageUrl(),
                user.getStatus(),
                user.getRole()
        );
    }
}
