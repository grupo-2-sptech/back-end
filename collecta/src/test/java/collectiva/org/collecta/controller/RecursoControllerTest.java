package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.TipoRecurso;
import collectiva.org.collecta.dto.RecursoDTO;
import collectiva.org.collecta.service.RecursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class RecursoControllerTest {

  @InjectMocks
  private RecursoController recursoController;

  @Mock
  private RecursoService recursoService;

   RecursoDTO recurso = new RecursoDTO(UUID.randomUUID(),"NomeTest","DescTest",1000,25000, TipoRecurso.ARTIGOS);

   @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarRecursos() {
    List<RecursoDTO> recursos = new ArrayList<>();
    Mockito.when(recursoService.buscarTodosRecursos()).thenReturn(recursos);

    ResponseEntity<List<RecursoDTO>> response = recursoController.buscarRecursos();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarRecursoPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(recursoService.buscarRecursoPorId(id)).thenReturn(recurso);

    ResponseEntity<RecursoDTO> response = recursoController.buscarRecursoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(recurso, response.getBody());
  }

  @Test
   void testCriarRecurso() {
    Mockito.when(recursoService.salvarRecurso(recurso)).thenReturn(recurso);

    ResponseEntity<RecursoDTO> response = recursoController.criarRecurso(recurso);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(recurso, response.getBody());
  }

  @Test
   void testAtualizarRecurso() {
    UUID id = UUID.randomUUID();
    Mockito.when(recursoService.atualizarRecurso(id, recurso)).thenReturn(recurso);

    ResponseEntity<RecursoDTO> response = recursoController.atualizarRecurso(id, recurso);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(recurso, response.getBody());
  }

  @Test
   void testDeletarRecurso() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = recursoController.deletarRecurso(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
