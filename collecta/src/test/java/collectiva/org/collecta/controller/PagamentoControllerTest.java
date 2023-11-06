package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.PagamentoDTO;
import collectiva.org.collecta.service.PagamentoService;
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

 class PagamentoControllerTest {

  @InjectMocks
  private PagamentoController pagamentoController;

  @Mock
  private PagamentoService pagamentoService;

  PagamentoDTO pagamento = new PagamentoDTO(UUID.randomUUID(),"NomeTest","1234","12345","29/04","123456","Elo");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarPagamentos() {
    List<PagamentoDTO> pagamentos = new ArrayList<>();
    Mockito.when(pagamentoService.buscarTodosPagamentos()).thenReturn(pagamentos);

    ResponseEntity<List<PagamentoDTO>> response = pagamentoController.buscarPagamentos();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarPagamentoPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(pagamentoService.buscarPagamentoPorId(id)).thenReturn(pagamento);

    ResponseEntity<PagamentoDTO> response = pagamentoController.buscarPagamentoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(pagamento, response.getBody());
  }

  @Test
   void testCriarPagamento() {
    Mockito.when(pagamentoService.salvarPagamento(pagamento)).thenReturn(pagamento);

    ResponseEntity<PagamentoDTO> response = pagamentoController.criarPagamento(pagamento);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(pagamento, response.getBody());
  }

  @Test
   void testAtualizarPagamento() {
    UUID id = UUID.randomUUID();
    Mockito.when(pagamentoService.atualizarPagamento(id, pagamento)).thenReturn(pagamento);

    ResponseEntity<PagamentoDTO> response = pagamentoController.atualizarPagamento(id, pagamento);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(pagamento, response.getBody());
  }

  @Test
   void testDeletarPagamento() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = pagamentoController.deletarPagamento(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
