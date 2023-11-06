package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.EventoCampanhaDTO;
import collectiva.org.collecta.service.EventoCampanhaService;
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

 class EventoCampanhaControllerTest {

  @InjectMocks
  private EventoCampanhaController eventoCampanhaController;

  @Mock
  private EventoCampanhaService eventoCampanhaService;

  EventoCampanhaDTO evento = new EventoCampanhaDTO(UUID.randomUUID(),"NomeTest","DescTeste", LocalDateTime.of(2023,12,23,14,00,00));

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarEventosCampanha() {
    List<EventoCampanhaDTO> eventos = new ArrayList<>();
    Mockito.when(eventoCampanhaService.buscarTodosEventosCampanha()).thenReturn(eventos);

    ResponseEntity<List<EventoCampanhaDTO>> response = eventoCampanhaController.buscarEventosCampanha();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarEventoCampanhaPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(eventoCampanhaService.buscarEventoCampanhaPorId(id)).thenReturn(evento);

    ResponseEntity<EventoCampanhaDTO> response = eventoCampanhaController.buscarEventoCampanhaPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(evento, response.getBody());
  }

  @Test
   void testCriarEventoCampanha() {
    Mockito.when(eventoCampanhaService.salvarEventoCampanha(evento)).thenReturn(evento);

    ResponseEntity<EventoCampanhaDTO> response = eventoCampanhaController.criarEventoCampanha(evento);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(evento, response.getBody());
  }

  @Test
   void testAtualizarEventoCampanha() {
    UUID id = UUID.randomUUID();
    Mockito.when(eventoCampanhaService.atualizarEventoCampanha(id, evento)).thenReturn(evento);

    ResponseEntity<EventoCampanhaDTO> response = eventoCampanhaController.atualizarEventoCampanha(id, evento);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(evento, response.getBody());
  }

  @Test
   void testDeletarEventoCampanha() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = eventoCampanhaController.deletarEventoCampanha(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
