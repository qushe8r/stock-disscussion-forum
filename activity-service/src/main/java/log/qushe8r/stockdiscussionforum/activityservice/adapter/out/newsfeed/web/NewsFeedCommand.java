package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web;

public record NewsFeedCommand(
        Long followeeId,
        ResourceType resourceType,
        ActivityType activityType,
        Long activityId,
        Long associatedId
) {
    public static NewsFeedCommand postRegister(Long requestingUserId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.POST, ActivityType.POST, postId, null);
    }

    public static NewsFeedCommand postUpdate(Long requestingUserId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.POST, ActivityType.UPDATE, postId, null);
    }

    public static NewsFeedCommand deletePost(Long requestingUserId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.POST, ActivityType.DELETE, postId, null);
    }

    public static NewsFeedCommand registerPostLike(Long requestingUserId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.POST_LIKE, ActivityType.POST, postId, null);
    }

    public static NewsFeedCommand deletePostLike(Long requestingUserId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.POST_LIKE, ActivityType.DELETE, postId, null);
    }

    public static NewsFeedCommand registerCommand(Long requestingUserId, Long commentId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.COMMENT, ActivityType.POST, commentId, postId);
    }

    public static NewsFeedCommand updateComment(Long requestingUserId, Long commentId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.COMMENT, ActivityType.UPDATE, commentId, postId);
    }

    public static NewsFeedCommand deleteComment(Long requestingUserId, Long commentId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.COMMENT, ActivityType.DELETE, commentId, postId);
    }

    public static NewsFeedCommand registerCommentLike(Long requestingUserId, Long commentId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.COMMENT_LIKE, ActivityType.POST, commentId, postId);
    }

    public static NewsFeedCommand commentLikeDelete(Long requestingUserId, Long commentId, Long postId) {
        return new NewsFeedCommand(requestingUserId, ResourceType.COMMENT_LIKE, ActivityType.DELETE, commentId, postId);
    }

}
