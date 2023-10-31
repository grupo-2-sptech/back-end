package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.service.CampanhaService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
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

public class CampanhaControllerTest {

  @InjectMocks
  private CampanhaController campanhaController;

  @Mock
  private CampanhaService campanhaService;

  private Validator validator;
  CampanhaDTO campanha = new CampanhaDTO(UUID.randomUUID(),"CampanhaTeste","DescTeste", LocalDateTime.now(),null,"ATIVO");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
   void testBuscarCampanhas() {
    List<CampanhaDTO> campanhas = new ArrayList<>();
    Mockito.when(campanhaService.buscarTodasCampanhas()).thenReturn(campanhas);

    ResponseEntity<List<CampanhaDTO>> response = campanhaController.buscarCampanhas();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarCampanhaPorId() {
    UUID id = UUID.randomUUID();

    Mockito.when(campanhaService.buscarCampanhaPorId(id)).thenReturn(campanha);

    ResponseEntity<CampanhaDTO> response = campanhaController.buscarCampanhaPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(campanha, response.getBody());
  }

  @Test
   void testCriarCampanha() {
    Mockito.when(campanhaService.salvarCampanha(campanha)).thenReturn(campanha);

    ResponseEntity<CampanhaDTO> response = campanhaController.criarCampanha(campanha);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(campanha, response.getBody());
  }

  @Test
   void testAtualizarCampanha() {
    UUID id = UUID.randomUUID();
    Mockito.when(campanhaService.atualizarCampanha(id, campanha)).thenReturn(campanha);

    ResponseEntity<CampanhaDTO> response = campanhaController.atualizarCampanha(id, campanha);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(campanha, response.getBody());
  }

  @Test
   void testDeletarCampanha() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = campanhaController.deletarCampanha(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
