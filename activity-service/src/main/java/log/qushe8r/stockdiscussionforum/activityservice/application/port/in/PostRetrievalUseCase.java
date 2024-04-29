package log.qushe8r.stockdiscussionforum.activityservice.application.port.in;

import java.util.List;

public interface PostRetrievalUseCase {

    PostDetailsResponse findById(Long postId);

    List<PostResponse> retrievePostsByWriterId(Long writerId);
}
