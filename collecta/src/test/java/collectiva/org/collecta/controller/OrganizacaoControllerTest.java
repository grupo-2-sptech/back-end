package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.OrganizacaoDTO;
import collectiva.org.collecta.service.OrganizacaoService;
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

 class OrganizacaoControllerTest {

  @InjectMocks
  private OrganizacaoController organizacaoController;

  @Mock
  private OrganizacaoService organizacaoService;

  OrganizacaoDTO organizacao = new OrganizacaoDTO(UUID.randomUUID(),"EmailTest","1234","12345","NomeSocialTest","NomeFantasiaTest", LocalDateTime.of(2022,4,16,14,55,20),"123456");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarOrganizacoes() {
    List<OrganizacaoDTO> organizacoes = new ArrayList<>();
    Mockito.when(organizacaoService.buscarTodasOrganizacoes()).thenReturn(organizacoes);

    ResponseEntity<List<OrganizacaoDTO>> response = organizacaoController.buscarOrganizacoes();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarOrganizacaoPorId() {
    UUID id = UUID.randomUUID();
    Mockito.when(organizacaoService.buscarOrganizacaoPorId(id)).thenReturn(organizacao);

    ResponseEntity<OrganizacaoDTO> response = organizacaoController.buscarOrganizacaoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(organizacao, response.getBody());
  }

  @Test
   void testCriarOrganizacao() {
    Mockito.when(organizacaoService.salvarOrganizacao(organizacao)).thenReturn(organizacao);

    ResponseEntity<OrganizacaoDTO> response = organizacaoController.criarOrganizacao(organizacao);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(organizacao, response.getBody());
  }

  @Test
   void testAtualizarOrganizacao() {
    UUID id = UUID.randomUUID();
    Mockito.when(organizacaoService.atualizarOrganizacao(id, organizacao)).thenReturn(organizacao);

    ResponseEntity<OrganizacaoDTO> response = organizacaoController.atualizarOrganizacao(id, organizacao);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(organizacao, response.getBody());
  }

  @Test
   void testDeletarOrganizacao() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = organizacaoController.deletarOrganizacao(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
