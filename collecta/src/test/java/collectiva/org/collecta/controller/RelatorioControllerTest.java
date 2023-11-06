package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.RelatorioDTO;
import collectiva.org.collecta.service.RelatorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class RelatorioControllerTest {

  @InjectMocks
  private RelatorioController relatorioController;

  @Mock
  private RelatorioService relatorioService;

  RelatorioDTO relatorio = new RelatorioDTO(UUID.randomUUID(), LocalDateTime.now(), BigDecimal.valueOf(20000),10);

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarRelatorios() {
    List<RelatorioDTO> relatorios = new ArrayList<>();
    Mockito.when(relatorioService.buscarTodosRelatorios()).thenReturn(relatorios);

    ResponseEntity<List<RelatorioDTO>> response = relatorioController.buscarRelatorios();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarRelatorioPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(relatorioService.buscarRelatorioPorId(id)).thenReturn(relatorio);

    ResponseEntity<RelatorioDTO> response = relatorioController.buscarRelatorioPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(relatorio, response.getBody());
  }

  @Test
   void testCriarRelatorio() {
    Mockito.when(relatorioService.salvarRelatorio(relatorio)).thenReturn(relatorio);

    ResponseEntity<RelatorioDTO> response = relatorioController.criarRelatorio(relatorio);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(relatorio, response.getBody());
  }

  @Test
   void testAtualizarRelatorio() {
    UUID id = UUID.randomUUID();
    Mockito.when(relatorioService.atualizarRelatorio(id, relatorio)).thenReturn(relatorio);

    ResponseEntity<RelatorioDTO> response = relatorioController.atualizarRelatorio(id, relatorio);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(relatorio, response.getBody());
  }

  @Test
   void testDeletarRelatorio() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = relatorioController.deletarRelatorio(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
   void testDownloadCsv() {
    List<RelatorioDTO> relatorios = new ArrayList<>();
    Mockito.when(relatorioService.buscarTodosRelatorios()).thenReturn(relatorios);

    ResponseEntity<byte[]> response = relatorioController.downloadCsv();

    assertEquals(HttpStatus.OK, response.getStatusCode());

    File file = new File("relatorios.csv");
    assertNotNull(response.getBody());
  }
}
