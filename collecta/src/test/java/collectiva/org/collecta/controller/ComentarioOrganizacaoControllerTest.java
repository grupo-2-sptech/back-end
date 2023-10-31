package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.TipoConta;
import collectiva.org.collecta.dto.ComentarioOrganizacaoDTO;
import collectiva.org.collecta.service.ComentarioOrganizacaoService;
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

public class ComentarioOrganizacaoControllerTest {

  @InjectMocks
  private ComentarioOrganizacaoController comentarioOrganizacaoController;

  @Mock
  private ComentarioOrganizacaoService comentarioOrganizacaoService;

  ComentarioOrganizacaoDTO comentario = new ComentarioOrganizacaoDTO(UUID.randomUUID(),"ComentarioTeste", LocalDateTime.now(), TipoConta.ORGANIZACAO);


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testBuscarComentarios() {
    List<ComentarioOrganizacaoDTO> comentarios = new ArrayList<>();
    Mockito.when(comentarioOrganizacaoService.buscarTodosComentarios()).thenReturn(comentarios);

    ResponseEntity<List<ComentarioOrganizacaoDTO>> response = comentarioOrganizacaoController.buscarComentarios();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  public void testBuscarComentarioPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(comentarioOrganizacaoService.buscarComentarioPorId(id)).thenReturn(comentario);

    ResponseEntity<ComentarioOrganizacaoDTO> response = comentarioOrganizacaoController.buscarComentarioPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(comentario, response.getBody());
  }

  @Test
  public void testCriarComentario() {
    Mockito.when(comentarioOrganizacaoService.salvarComentario(comentario)).thenReturn(comentario);

    ResponseEntity<ComentarioOrganizacaoDTO> response = comentarioOrganizacaoController.criarComentario(comentario);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(comentario, response.getBody());
  }

  @Test
  public void testAtualizarComentario() {
    UUID id = UUID.randomUUID();
    Mockito.when(comentarioOrganizacaoService.atualizarComentario(id, comentario)).thenReturn(comentario);

    ResponseEntity<ComentarioOrganizacaoDTO> response = comentarioOrganizacaoController.atualizarComentario(id, comentario);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(comentario, response.getBody());
  }

  @Test
  public void testDeletarComentario() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = comentarioOrganizacaoController.deletarComentario(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}

