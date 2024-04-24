package log.qushe8r.stockdiscussionforum.security.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TokenRepository extends CrudRepository<Token, String> {
   List<Token> findByUserId(Long userId);
}
