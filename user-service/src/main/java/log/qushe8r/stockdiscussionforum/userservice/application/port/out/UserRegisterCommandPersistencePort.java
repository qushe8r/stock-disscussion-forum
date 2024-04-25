package log.qushe8r.stockdiscussionforum.userservice.application.port.out;

import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationCommand;

public interface UserRegisterCommandPersistencePort {

    UserJpaEntity registerUser(UserRegistrationCommand command, String encodedPassword);

    void activateUser(UserJpaEntity userJpaEntity);

}
