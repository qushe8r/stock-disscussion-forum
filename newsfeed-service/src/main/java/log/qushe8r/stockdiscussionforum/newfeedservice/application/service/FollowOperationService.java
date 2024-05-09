package log.qushe8r.stockdiscussionforum.newfeedservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence.FollowJpaEntity;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.FollowOperationUseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.FollowOperationPersistencePort;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.FollowQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.newfeedservice.domain.Follow;
import log.qushe8r.stockdiscussionforum.newfeedservice.domain.exception.FollowException;
import log.qushe8r.stockdiscussionforum.newfeedservice.domain.exception.FollowExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FollowOperationService implements FollowOperationUseCase {

    private final FollowQueryPersistencePort queryPort;
    private final FollowOperationPersistencePort persistencePort;

    @Transactional
    @Override
    public boolean operateFollow(Long requestingUserId, Long followeeId) {
        Optional<FollowJpaEntity> optional = queryPort.findByFollowerIdAndFolloweeId(requestingUserId, followeeId);
        optional.ifPresentOrElse(
                follow -> unfollow(requestingUserId, followeeId),
                () -> follow(requestingUserId, followeeId)
        );
        return optional.isEmpty();
    }

    private void follow(Long requestingUserId, Long followeeId) {
        Follow follow = Follow.of(requestingUserId, followeeId, 0);
        if (follow.isSelfFollow()) {
            throw new FollowException(FollowExceptionCode.CANNOT_FOLLOW_SELF);
        }
        persistencePort.follow(requestingUserId, followeeId);
    }

    private void unfollow(Long requestingUserId, Long followeeId) {
        persistencePort.unfollow(requestingUserId, followeeId);
    }

}
