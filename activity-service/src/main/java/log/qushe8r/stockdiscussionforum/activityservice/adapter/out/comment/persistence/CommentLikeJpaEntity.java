package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "as_comment_likes")
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_like_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	private CommentJpaEntity commentJpaEntity;

	public CommentLikeJpaEntity(Long userId, CommentJpaEntity commentJpaEntity) {
		this.userId = userId;
		this.commentJpaEntity = commentJpaEntity;
	}

}
