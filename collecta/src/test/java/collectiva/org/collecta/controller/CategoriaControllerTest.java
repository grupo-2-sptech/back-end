package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CategoriaDTO;
import collectiva.org.collecta.service.CategoriaService;
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

 class CategoriaControllerTest {

  @InjectMocks
  private CategoriaController categoriaController;

  @Mock
  private CategoriaService categoriaService;

  CategoriaDTO categoria = new CategoriaDTO(UUID.randomUUID(),"nomeTeste","DescTeste");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
   void testBuscarCategorias() {
    List<CategoriaDTO> categorias = new ArrayList<>();
    Mockito.when(categoriaService.buscarTodasCategorias()).thenReturn(categorias);

    ResponseEntity<List<CategoriaDTO>> response = categoriaController.buscarCategorias();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
   void testBuscarCategoriaPorId() {
    UUID id = UUID.randomUUID();

    Mockito.when(categoriaService.buscarCategoriaPorId(id)).thenReturn(categoria);

    ResponseEntity<CategoriaDTO> response = categoriaController.buscarCategoriaPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(categoria, response.getBody());
  }

  @Test
   void testCriarCategoria() {
    Mockito.when(categoriaService.salvarCategoria(categoria)).thenReturn(categoria);

    ResponseEntity<CategoriaDTO> response = categoriaController.criarCategoria(categoria);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(categoria, response.getBody());
  }

  @Test
   void testAtualizarCategoria() {
    UUID id = UUID.randomUUID();
    Mockito.when(categoriaService.atualizarCategoria(id, categoria)).thenReturn(categoria);

    ResponseEntity<CategoriaDTO> response = categoriaController.atualizarCategoria(id, categoria);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(categoria, response.getBody());
  }

  @Test
   void testDeletarCategoria() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = categoriaController.deletarCategoria(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
