package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.MetaStatus;
import collectiva.org.collecta.dto.FinanceiroCampanhaDTO;
import collectiva.org.collecta.service.FinanceiroCampanhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class FinanceiroCampanhaControllerTest {

  @InjectMocks
  private FinanceiroCampanhaController financeiroCampanhaController;

  @Mock
  private FinanceiroCampanhaService financeiroCampanhaService;

  FinanceiroCampanhaDTO financeiro = new FinanceiroCampanhaDTO(UUID.randomUUID(), BigDecimal.ONE,BigDecimal.valueOf(1000), MetaStatus.EM_ANDAMENTO);

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarFinanceirosCampanha() {
    List<FinanceiroCampanhaDTO> financeiros = new ArrayList<>();
    Mockito.when(financeiroCampanhaService.buscarTodosFinanceirosCampanha()).thenReturn(financeiros);

    ResponseEntity<List<FinanceiroCampanhaDTO>> response = financeiroCampanhaController.buscarFinanceirosCampanha();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarFinanceiroCampanhaPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(financeiroCampanhaService.buscarFinanceiroCampanhaPorId(id)).thenReturn(financeiro);

    ResponseEntity<FinanceiroCampanhaDTO> response = financeiroCampanhaController.buscarFinanceiroCampanhaPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(financeiro, response.getBody());
  }

  @Test
   void testCriarFinanceiroCampanha() {
    Mockito.when(financeiroCampanhaService.salvarFinanceiroCampanha(financeiro)).thenReturn(financeiro);

    ResponseEntity<FinanceiroCampanhaDTO> response = financeiroCampanhaController.criarFinanceiroCampanha(financeiro);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(financeiro, response.getBody());
  }

  @Test
   void testAtualizarFinanceiroCampanha() {
    UUID id = UUID.randomUUID();
    Mockito.when(financeiroCampanhaService.atualizarFinanceiroCampanha(id, financeiro)).thenReturn(financeiro);

    ResponseEntity<FinanceiroCampanhaDTO> response = financeiroCampanhaController.atualizarFinanceiroCampanha(id, financeiro);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(financeiro, response.getBody());
  }

  @Test
   void testDeletarFinanceiroCampanha() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = financeiroCampanhaController.deletarFinanceiroCampanha(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
