package log.qushe8r.stockdiscussionforum.newfeedservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Follow {

    private Long followerId;
    private Long followeeId;
    private Integer score;

    public static Follow of(Long followerId, Long followeeId, Integer score) {
        return new Follow(followerId, followeeId, score);
    }

    public boolean isSelfFollow() {
        return !this.followerId.equals(this.followeeId);
    }

}
