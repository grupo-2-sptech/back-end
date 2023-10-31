package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.StatusContribuicao;
import collectiva.org.collecta.dto.ContribuicaoMonetariaDTO;
import collectiva.org.collecta.service.ContribuicaoMonetariaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContribuicaoMonetariaControllerTest {

  @InjectMocks
  private ContribuicaoMonetariaController contribuicaoMonetariaController;

  @Mock
  private ContribuicaoMonetariaService contribuicaoMonetariaService;

  ContribuicaoMonetariaDTO contribuicao = new ContribuicaoMonetariaDTO(UUID.randomUUID(),"nomeTeste","DescTeste", LocalDateTime.now(),new BigDecimal(1000),0, FormaPagamento.CARTAO_CREDITO, StatusContribuicao.FINALIZADA);



  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testBuscarContribuicoesMonetarias() {
    List<ContribuicaoMonetariaDTO> contribuicoes = new ArrayList<>();
    Mockito.when(contribuicaoMonetariaService.buscarTodasContribuicoesMonetarias()).thenReturn(contribuicoes);

    ResponseEntity<List<ContribuicaoMonetariaDTO>> response = contribuicaoMonetariaController.buscarContribuicoesMonetarias();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  public void testBuscarContribuicaoMonetariaPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(id)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoMonetariaDTO> response = contribuicaoMonetariaController.buscarContribuicaoMonetariaPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
  public void testCriarContribuicaoMonetaria() {
    Mockito.when(contribuicaoMonetariaService.salvarContribuicaoMonetaria(contribuicao)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoMonetariaDTO> response = contribuicaoMonetariaController.criarContribuicaoMonetaria(contribuicao);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
  public void testAtualizarContribuicaoMonetaria() {
    UUID id = UUID.randomUUID();
    Mockito.when(contribuicaoMonetariaService.atualizarContribuicaoMonetaria(id, contribuicao)).thenReturn(contribuicao);

    ResponseEntity<ContribuicaoMonetariaDTO> response = contribuicaoMonetariaController.atualizarContribuicaoMonetaria(id, contribuicao);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(contribuicao, response.getBody());
  }

  @Test
  public void testDeletarContribuicaoMonetaria() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = contribuicaoMonetariaController.deletarContribuicaoMonetaria(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}

