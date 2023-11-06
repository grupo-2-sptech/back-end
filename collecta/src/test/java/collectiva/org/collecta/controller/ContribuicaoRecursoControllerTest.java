package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import collectiva.org.collecta.domain.enums.TipoRecurso;
import collectiva.org.collecta.dto.ContribuicaoRecursoDTO;
import collectiva.org.collecta.service.ContribuicaoRecursoService;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContribuicaoRecursoControllerTest {

  @InjectMocks
  private ContribuicaoRecursoController contribuicaoRecursoController;

  @Mock
  private ContribuicaoRecursoService contribuicaoRecursoService;
  ContribuicaoRecursoDTO contribuicao = new ContribuicaoRecursoDTO(UUID.randomUUID(),"NomeTest","DescTest", LocalDateTime.now(),1, TipoRecurso.ALIMENTOS, StatusContribuicao.FINALIZADA);


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testBuscarContribuicoesRecursos() {
    List<ContribuicaoRecursoDTO> contribuicoes = new ArrayList<>();
    Mockito.when(contribuicaoRecursoService.buscarTodasContribuicoesRecursos()).thenReturn(contribuicoes);

    ResponseEntity<List<ContribuicaoRecursoDTO>> response = contribuicaoRecursoController.buscarContribuicoesRecursos();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  void testBuscarContribuicaoRecursoPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(contribuicaoRecursoService.buscarContribuicaoRecursoPorId(id)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoRecursoDTO> response = contribuicaoRecursoController.buscarContribuicaoRecursoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
  void testCriarContribuicaoRecurso() {
    Mockito.when(contribuicaoRecursoService.salvarContribuicaoRecurso(contribuicao)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoRecursoDTO> response = contribuicaoRecursoController.criarContribuicaoRecurso(contribuicao);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
  void testAtualizarContribuicaoRecurso() {
    UUID id = UUID.randomUUID();
    Mockito.when(contribuicaoRecursoService.atualizarContribuicaoRecurso(id, contribuicao)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoRecursoDTO> response = contribuicaoRecursoController.atualizarContribuicaoRecurso(id, contribuicao);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
  void testDeletarContribuicaoRecurso() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = contribuicaoRecursoController.deletarContribuicaoRecurso(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}

