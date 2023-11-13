package collectiva.org.collecta.domain.relatorio.controller;

import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.RelatorioDTO;
import collectiva.org.collecta.domain.relatorio.mapper.RelatorioMapper;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import collectiva.org.collecta.utils.ListaObj;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<RelatorioDTO>> buscarRelatorios() {
        List<RelatorioDTO> listaDTO = relatorioService.buscarTodosRelatorios().stream()
                .map(RelatorioMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(RelatorioMapper.paraDTO(relatorioService.buscarRelatorioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<RelatorioDTO> criarRelatorio(@RequestBody @Valid RelatorioDTO relatorioDTO) {
        Relatorio relatorio = relatorioService.salvarRelatorio(RelatorioMapper.paraEntidade(relatorioDTO));
        return ResponseEntity.status(201).body(RelatorioMapper.paraDTO(relatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioDTO> atualizarRelatorio(@PathVariable UUID id, @RequestBody @Valid RelatorioDTO relatorioDTO) {
        Relatorio relatorio = relatorioService.atualizarRelatorio(id, RelatorioMapper.paraEntidade(relatorioDTO));
        return ResponseEntity.ok(RelatorioMapper.paraDTO(relatorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable UUID id) {
        relatorioService.deletarRelatorio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCsv() {
        ListaObj<RelatorioDTO> lista = new ListaObj<>(relatorioService.buscarTodosRelatorios().size());  // Exemplo: Crie uma lista de RelatorioDTO
        List<RelatorioDTO> allList = relatorioService.buscarTodosRelatorios().stream()
                .map(RelatorioMapper::paraDTO).toList();
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