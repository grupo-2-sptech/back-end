package collectiva.org.collecta.controller;

import collectiva.org.collecta.controller.PostController;
import collectiva.org.collecta.dto.PostDTO;
import collectiva.org.collecta.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class PostControllerTest {

  @InjectMocks
  private PostController postController;

  @Mock
  private PostService postService;
   PostDTO post = new PostDTO(UUID.randomUUID(),"TituloTest","ConteudoTest", LocalDateTime.now());


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarPosts() {
    List<PostDTO> posts = new ArrayList<>();
    Mockito.when(postService.buscarTodosPosts()).thenReturn(posts);

    ResponseEntity<List<PostDTO>> response = postController.buscarPosts();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarPostPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(postService.buscarPostPorId(id)).thenReturn(post);

    ResponseEntity<PostDTO> response = postController.buscarPostPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(post, response.getBody());
  }

  @Test
   void testCriarPost() {
    Mockito.when(postService.salvarPost(post)).thenReturn(post);

    ResponseEntity<PostDTO> response = postController.criarPost(post);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(post, response.getBody());
  }

  @Test
   void testAtualizarPost() {
    UUID id = UUID.randomUUID();
    Mockito.when(postService.atualizarPost(id, post)).thenReturn(post);

    ResponseEntity<PostDTO> response = postController.atualizarPost(id, post);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(post, response.getBody());
  }

  @Test
   void testDeletarPost() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = postController.deletarPost(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
