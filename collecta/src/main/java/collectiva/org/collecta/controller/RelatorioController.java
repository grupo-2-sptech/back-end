package collectiva.org.collecta.controller;

import collectiva.org.collecta.controller.utils.ListaObj;
import collectiva.org.collecta.dto.RelatorioDTO;
import collectiva.org.collecta.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<RelatorioDTO>> buscarRelatorios() {
        List<RelatorioDTO> lista = relatorioService.buscarTodosRelatorios();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(relatorioService.buscarRelatorioPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioDTO> criarRelatorio(@RequestBody @Valid RelatorioDTO relatorioDTO) {
        return ResponseEntity.status(201).body(relatorioService.salvarRelatorio(relatorioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioDTO> atualizarRelatorio(@PathVariable UUID id, @RequestBody @Valid RelatorioDTO relatorioDTO) {
        return ResponseEntity.ok(relatorioService.atualizarRelatorio(id, relatorioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable UUID id) {
        relatorioService.deletarRelatorio(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCsv() {
        ListaObj<RelatorioDTO> lista = new ListaObj<>(relatorioService.buscarTodosRelatorios().size());  // Exemplo: Crie uma lista de RelatorioDTO
        List<RelatorioDTO> allList = relatorioService.buscarTodosRelatorios();
      for (RelatorioDTO relatorioDTO : allList) {
        lista.adiciona(relatorioDTO);
      }

        String nomeArquivo = "relatorios";

        ListaObj.gravaArquivoCsv(lista, nomeArquivo);

        File file = new File(nomeArquivo + ".csv");

        try {
            InputStream fileInputStream = new FileInputStream(file);

            return ResponseEntity.status(200)
                .header("Content-Disposition", "attachment; filename=" + nomeArquivo + ".csv")
                .body(fileInputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao baixar o arquivo.".getBytes());
        }
    }
}
