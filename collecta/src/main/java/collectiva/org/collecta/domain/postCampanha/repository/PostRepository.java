package collectiva.org.collecta.domain.postCampanha.repository;

import collectiva.org.collecta.domain.postCampanha.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
