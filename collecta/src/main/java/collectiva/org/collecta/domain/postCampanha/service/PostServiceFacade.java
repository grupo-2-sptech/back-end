package collectiva.org.collecta.domain.postCampanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.dto.AssociationPostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.CreatePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.ResponsePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.UpdatePostDTO;
import collectiva.org.collecta.domain.postCampanha.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceFacade {
    private final PostService postService;
    private final CampanhaService campanhaService;

    public List<ResponsePostDTO> buscarTodosPosts() {
        return postService.buscarTodosPosts().stream().map
                (PostMapper::paraDTO).toList();
    }

    public List<ResponsePostDTO> buscarPostsPorCampanha(UUID id) {
        return postService.buscarPostsPorCampanha(id).stream().map
                (PostMapper::paraDTO).toList();
    }

    public ResponsePostDTO buscarPostPorId(UUID id) {
        return PostMapper.paraDTO(postService.buscarPostPorId(id));
    }

    public AssociationPostDTO criarPost(UUID idEvento, CreatePostDTO postDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(idEvento);
        Post post = PostMapper.paraEntidade(postDTO);
        return PostMapper.paraAssociacaoDTO(postService.criarPost(post, campanha));
    }

    public AssociationPostDTO atualizarPost(UUID idPost, UpdatePostDTO postDTO) {
        Post post = PostMapper.paraEntidadeUpdate(postDTO);
        return PostMapper.paraAssociacaoDTO(postService.atualizarPost(idPost, post));
    }

    public void deletarPost(UUID id) {
        postService.deletarPost(id);
    }

}
