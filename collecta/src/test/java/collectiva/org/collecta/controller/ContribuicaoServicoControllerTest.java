package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import collectiva.org.collecta.dto.ContribuicaoServicoDTO;
import collectiva.org.collecta.service.ContribuicaoServicoService;
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

 class ContribuicaoServicoControllerTest {

  @InjectMocks
  private ContribuicaoServicoController contribuicaoServicoController;

  @Mock
  private ContribuicaoServicoService contribuicaoServicoService;
  ContribuicaoServicoDTO contribuicao = new ContribuicaoServicoDTO(UUID.randomUUID(),"NomeTest","DescTest", LocalDateTime.now(),LocalDateTime.now(),null,10, StatusContribuicao.FINALIZADA);


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarContribuicoesServicos() {
    List<ContribuicaoServicoDTO> contribuicoes = new ArrayList<>();
    Mockito.when(contribuicaoServicoService.buscarTodasContribuicoesServicos()).thenReturn(contribuicoes);

    ResponseEntity<List<ContribuicaoServicoDTO>> response = contribuicaoServicoController.buscarContribuicoesServicos();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarContribuicaoServicoPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(contribuicaoServicoService.buscarContribuicaoServicoPorId(id)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoServicoDTO> response = contribuicaoServicoController.buscarContribuicaoServicoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
   void testCriarContribuicaoServico() {
    Mockito.when(contribuicaoServicoService.salvarContribuicaoServico(contribuicao)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoServicoDTO> response = contribuicaoServicoController.criarContribuicaoServico(contribuicao);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
   void testAtualizarContribuicaoServico() {
    UUID id = UUID.randomUUID();
    Mockito.when(contribuicaoServicoService.atualizarContribuicaoServico(id, contribuicao)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoServicoDTO> response = contribuicaoServicoController.atualizarContribuicaoServico(id, contribuicao);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
   void testDeletarContribuicaoServico() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = contribuicaoServicoController.deletarContribuicaoServico(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
