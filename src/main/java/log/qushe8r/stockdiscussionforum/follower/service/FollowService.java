package log.qushe8r.stockdiscussionforum.follower.service;

import log.qushe8r.stockdiscussionforum.follower.dto.FollowOperationRequest;
import log.qushe8r.stockdiscussionforum.follower.entity.Follow;
import log.qushe8r.stockdiscussionforum.follower.repository.FollowRepository;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public boolean operateFollow(FollowOperationRequest request, AuthenticatedUser authenticatedUser) {
        Optional<Follow> optionalFollow =
                followRepository.findByFolloweeIdAndFollowerId(request.followeeId(), authenticatedUser.getUserId());
        optionalFollow.ifPresentOrElse(followRepository::delete,
                () -> createFollow(request, authenticatedUser));

        return optionalFollow.isEmpty();
    }

    private void createFollow(FollowOperationRequest request, AuthenticatedUser user) {
        User followee = new User(request.followeeId());
        User follower = new User(user.getUserId());
        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
    }
}
