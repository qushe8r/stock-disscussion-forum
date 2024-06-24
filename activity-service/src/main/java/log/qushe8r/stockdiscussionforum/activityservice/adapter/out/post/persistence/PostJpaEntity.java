package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "as_posts")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "post_writer_id", nullable = false, updatable = false)
    private Long writerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postJpaEntity")
    private List<CommentJpaEntity> commentJpaEntities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postJpaEntity")
    private List<PostLikeJpaEntity> postLikeJpaEntities = new ArrayList<>();

    public PostJpaEntity(Long id) {
        this.id = id;
    }

    public PostJpaEntity(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

}
