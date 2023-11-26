package collectiva.org.collecta.domain.relatorio.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.CreateRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseRelatorioDTO>> buscarRelatorios() {
        List<ResponseRelatorioDTO> listaDTO = relatorioService.buscarTodosRelatorios().stream()
                .map(RelatorioMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/gerar/{id}")
    public ResponseEntity<ResponseRelatorioDTO> gerarRelatorioPorCampanha(@PathVariable UUID id, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim) {
        return ResponseEntity.ok(RelatorioMapper.paraDTO(relatorioService.gerarRelatorioPorCampanha(id, inicio, fim)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRelatorioDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(RelatorioMapper.paraDTO(relatorioService.buscarRelatorioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseRelatorioDTO> criarRelatorio(@RequestBody @Valid CreateRelatorioDTO relatorioDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(relatorioDTO.getIdCampanha());
        Relatorio relatorio = relatorioService.salvarRelatorio(RelatorioMapper.paraEntidade(relatorioDTO), campanha);
        return ResponseEntity.status(201).body(RelatorioMapper.paraDTO(relatorio));
    }


    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCsv() {
        ListaObj<ResponseRelatorioDTO> lista = new ListaObj<>(relatorioService.buscarTodosRelatorios().size());  // Exemplo: Crie uma lista de CreateRelatorioDTO
        List<ResponseRelatorioDTO> allList = relatorioService.buscarTodosRelatorios().stream()
                .map(RelatorioMapper::paraDTO).toList();
        for (ResponseRelatorioDTO relatorioDTO : allList) {
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
