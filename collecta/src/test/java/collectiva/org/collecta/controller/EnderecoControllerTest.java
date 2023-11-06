package collectiva.org.collecta.controller;

import collectiva.org.collecta.controller.EnderecoController;
import collectiva.org.collecta.dto.EnderecoDTO;
import collectiva.org.collecta.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class EnderecoControllerTest {

  @InjectMocks
  private EnderecoController enderecoController;

  @Mock
  private EnderecoService enderecoService;

  @Mock
  private RestTemplate restTemplate;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarEnderecos() {
    List<EnderecoDTO> enderecos = new ArrayList<>();
    Mockito.when(enderecoService.buscarTodosEnderecos()).thenReturn(enderecos);

    ResponseEntity<List<EnderecoDTO>> response = enderecoController.buscarEnderecos();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarEnderecoPorId() {
    UUID id = UUID.randomUUID();
    EnderecoDTO endereco = new EnderecoDTO();
    Mockito.when(enderecoService.buscarEnderecoPorId(id)).thenReturn(endereco);

    ResponseEntity<EnderecoDTO> response = enderecoController.buscarEnderecoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(endereco, response.getBody());
  }

  @Test
   void testCriarEndereco() {
    EnderecoDTO endereco = new EnderecoDTO();
    Mockito.when(enderecoService.salvarEndereco(endereco)).thenReturn(endereco);

    ResponseEntity<EnderecoDTO> response = enderecoController.criarEndereco(endereco);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(endereco, response.getBody());
  }

  @Test
   void testAtualizarEndereco() {
    UUID id = UUID.randomUUID();
    EnderecoDTO endereco = new EnderecoDTO();
    Mockito.when(enderecoService.atualizarEndereco(id, endereco)).thenReturn(endereco);

    ResponseEntity<EnderecoDTO> response = enderecoController.atualizarEndereco(id, endereco);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(endereco, response.getBody());
  }

  @Test
   void testDeletarEndereco() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = enderecoController.deletarEndereco(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
   void testBuscaCep() {
    String cep = "12345678";
    EnderecoDTO endereco = new EnderecoDTO();
    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(EnderecoDTO.class))).thenReturn(endereco);

    EnderecoDTO response = enderecoController.buscaCep(cep);

    assertEquals(endereco, response);
  }
}
