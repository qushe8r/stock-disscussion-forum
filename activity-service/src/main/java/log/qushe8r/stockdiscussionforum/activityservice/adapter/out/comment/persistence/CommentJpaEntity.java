package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "as_comments")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostJpaEntity postJpaEntity;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "comment_writer_id", nullable = false)
    private Long writerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentJpaEntity")
    private List<CommentLikeJpaEntity> commentLikeJpaEntities = new ArrayList<>();

    public CommentJpaEntity(Long postId, String content, Long writerId) {
        this.postJpaEntity = new PostJpaEntity(postId);
        this.content = content;
        this.writerId = writerId;
    }

    public CommentJpaEntity(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + id + ", content: " + content + ", writerId: " + writerId + ", commentLikeJpaEntities: " + commentLikeJpaEntities.stream().map(
				Object::toString).collect(Collectors.joining());
        }
}
