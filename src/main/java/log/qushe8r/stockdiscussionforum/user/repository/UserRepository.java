package log.qushe8r.stockdiscussionforum.user.repository;

import log.qushe8r.stockdiscussionforum.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
