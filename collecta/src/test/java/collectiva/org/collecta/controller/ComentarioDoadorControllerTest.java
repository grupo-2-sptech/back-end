package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.TipoConta;
import collectiva.org.collecta.dto.ComentarioDoadorDTO;
import collectiva.org.collecta.service.ComentarioDoadorService;
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

public class ComentarioDoadorControllerTest {

  @InjectMocks
  private ComentarioDoadorController comentarioDoadorController;

  @Mock
  private ComentarioDoadorService comentarioDoadorService;

  ComentarioDoadorDTO comentario = new ComentarioDoadorDTO(UUID.randomUUID(),"ComentárioTeste", LocalDateTime.now(), TipoConta.DOADOR);


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testBuscarComentarios() {
    List<ComentarioDoadorDTO> comentarios = new ArrayList<>();
    Mockito.when(comentarioDoadorService.buscarTodosComentarios()).thenReturn(comentarios);

    ResponseEntity<List<ComentarioDoadorDTO>> response = comentarioDoadorController.buscarComentarios();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  public void testBuscarComentarioPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(comentarioDoadorService.buscarComentarioPorId(id)).thenReturn(comentario);

    ResponseEntity<ComentarioDoadorDTO> response = comentarioDoadorController.buscarComentarioPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(comentario, response.getBody());
  }

  @Test
  public void testCriarComentario() {
    Mockito.when(comentarioDoadorService.salvarComentario(comentario)).thenReturn(comentario);

    ResponseEntity<ComentarioDoadorDTO> response = comentarioDoadorController.criarComentario(comentario);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(comentario, response.getBody());
  }

  @Test
  public void testAtualizarComentario() {
    UUID id = UUID.randomUUID();
    Mockito.when(comentarioDoadorService.atualizarComentario(id, comentario)).thenReturn(comentario);

    ResponseEntity<ComentarioDoadorDTO> response = comentarioDoadorController.atualizarComentario(id, comentario);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(comentario, response.getBody());
  }

  @Test
  public void testDeletarComentario() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = comentarioDoadorController.deletarComentario(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
