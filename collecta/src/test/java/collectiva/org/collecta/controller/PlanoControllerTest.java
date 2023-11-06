package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.enums.StatusPlano;
import collectiva.org.collecta.domain.enums.TipoPlano;
import collectiva.org.collecta.dto.PlanoDTO;
import collectiva.org.collecta.service.PlanoService;
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

public class PlanoControllerTest {

  @InjectMocks
  private PlanoController planoController;

  @Mock
  private PlanoService planoService;

  PlanoDTO plano = new PlanoDTO(UUID.randomUUID(), LocalDateTime.now(),null, TipoPlano.PLATINUM, StatusPlano.ATIVO);

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testBuscarPlanos() {
    List<PlanoDTO> planos = new ArrayList<>();
    Mockito.when(planoService.buscarTodosPlanos()).thenReturn(planos);

    ResponseEntity<List<PlanoDTO>> response = planoController.buscarPlanos();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  public void testBuscarPlanoPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(planoService.buscarPlanoPorId(id)).thenReturn(plano);

    ResponseEntity<PlanoDTO> response = planoController.buscarPlanoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(plano, response.getBody());
  }

  @Test
  public void testCriarPlano() {
    Mockito.when(planoService.salvarPlano(plano)).thenReturn(plano);

    ResponseEntity<PlanoDTO> response = planoController.criarPlano(plano);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(plano, response.getBody());
  }

  @Test
  public void testAtualizarPlano() {
    UUID id = UUID.randomUUID();
    Mockito.when(planoService.atualizarPlano(id, plano)).thenReturn(plano);

    ResponseEntity<PlanoDTO> response = planoController.atualizarPlano(id, plano);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(plano, response.getBody());
  }

  @Test
  public void testDeletarPlano() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = planoController.deletarPlano(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
