package collectiva.org.collecta.domain.postLike.repository;

import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.postLike.PostLike;
import collectiva.org.collecta.domain.postCampanha.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, UUID> {
    void deleteByPostAndAndDoador(Doador doador, Post post);
    int countByPost(Post post);
    List<PostLike> findByDoador(Doador doador);
    List<PostLike> findByPost(Post post);
    
}
