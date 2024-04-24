package log.qushe8r.stockdiscussionforum.follower.service;

import log.qushe8r.stockdiscussionforum.follower.dto.FollowOperationRequest;
import log.qushe8r.stockdiscussionforum.follower.entity.Follow;
import log.qushe8r.stockdiscussionforum.follower.repository.FollowRepository;
import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;
import log.qushe8r.stockdiscussionforum.timeline.event.DeliveryFolloweeActivityEvent;
import log.qushe8r.stockdiscussionforum.timeline.event.RemoveFolloweeActivityEvent;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {
    private final FollowRepository followRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public boolean operateFollow(Long userId, FollowOperationRequest request) {
        Long followeeId = request.followeeId();

        Optional<Follow> optionalFollow =
                followRepository.findByFollowerIdAndFolloweeId(userId, followeeId);

        optionalFollow.ifPresentOrElse(
                follow -> deleteFollowProcess(follow, userId, followeeId),
                () -> createFollowProcess(userId, followeeId));

        return optionalFollow.isEmpty();
    }

    private void createFollowProcess(Long userId, Long followeeId) {
        Follow follow = createFollow(userId, followeeId);
        followRepository.save(follow);
        applicationEventPublisher
                .publishEvent(new DeliveryFolloweeActivityEvent(userId, ActivityType.FOLLOW, followeeId));
    }

    private void deleteFollowProcess(Follow follow, Long userId, Long followeeId) {
        followRepository.delete(follow);
        applicationEventPublisher
                .publishEvent(new RemoveFolloweeActivityEvent(userId, ActivityType.FOLLOW, followeeId));
    }

    private Follow createFollow(Long userId, Long followeeId) {
        User follower = new User(userId);
        User followee = new User(followeeId);
        return new Follow(follower, followee);
    }
}
