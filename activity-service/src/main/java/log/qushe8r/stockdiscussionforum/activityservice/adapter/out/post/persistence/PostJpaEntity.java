package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PostJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long writerId;

    @OneToMany(mappedBy = "postJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentJpaEntity> commentJpaEntities = new ArrayList<>();

    public PostJpaEntity(Long id) {
        this.id = id;
    }

    public PostJpaEntity(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

}
