package collectiva.org.collecta.controller;

import collectiva.org.collecta.authentication.dto.DoadorLoginDTO;
import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.dto.DoadorDTO;
import collectiva.org.collecta.service.DoadorService;
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

 class DoadorControllerTest {

  @InjectMocks
  private DoadorController doadorController;

  @Mock
  private DoadorService doadorService;
  DoadorDTO doador = new DoadorDTO(UUID.randomUUID(),"emailTest","1234","12345","Nometest","SobrenomeTest", LocalDateTime.now(),"123456");
  DoadorTokenDTO token = new DoadorTokenDTO(UUID.randomUUID(),"emailTest","1234","nomeTest","12345");
  DoadorLoginDTO loginDTO = new DoadorLoginDTO("nomeTest","1234");


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarDoadores() {
    List<DoadorDTO> doadores = new ArrayList<>();
    Mockito.when(doadorService.buscarTodosDoadores()).thenReturn(doadores);

    ResponseEntity<List<DoadorDTO>> response = doadorController.buscarDoadores();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarDoadorPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(doadorService.buscarDoadorPorId(id)).thenReturn(doador);

    ResponseEntity<DoadorDTO> response = doadorController.buscarDoadorPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(doador, response.getBody());
  }

  @Test
   void testCriarDoador() {
    Mockito.when(doadorService.salvarDoador(doador)).thenReturn(token);

    ResponseEntity<DoadorTokenDTO> response = doadorController.criarDoador(doador);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(token, response.getBody());
  }

  @Test
   void testAtualizarDoador() {
    UUID id = UUID.randomUUID();
    Mockito.when(doadorService.atualizarDoador(id, doador)).thenReturn(doador);

    ResponseEntity<DoadorDTO> response = doadorController.atualizarDoador(id, doador);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(doador, response.getBody());
  }

  @Test
   void testDeletarDoador() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = doadorController.deletarDoador(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
   void testLogin() {
    Mockito.when(doadorService.autenticar(loginDTO)).thenReturn(token);

    ResponseEntity<DoadorTokenDTO> response = doadorController.login(loginDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(token, response.getBody());
  }
}
