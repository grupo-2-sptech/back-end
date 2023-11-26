package collectiva.org.collecta.domain.campanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.repository.CampanhaRepository;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampanhaService {
    private final CampanhaRepository campanhaRepository;

    public Campanha salvarCampanha(Campanha campanha, Organizacao organizacao) {
        campanha.setOrganizacao(organizacao);
        return campanhaRepository.save(campanha);
    }

    public List<Campanha> buscarTodasCampanhas() {
        return campanhaRepository.findAll();
    }

    public Campanha buscarCampanhaPorId(UUID id) {
        return campanhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Campanha"));
    }

    public Campanha atualizarCampanha(UUID id, Campanha campanha) {
        buscarCampanhaPorId(id);
        campanha.setId(id);
        return campanhaRepository.save(campanha);
    }

    public void deletarCampanha(UUID id) {
        if (!campanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Campanha");
        }
        campanhaRepository.deleteById(id);
    }
    public void incrementarVisualizacao(Campanha campanha) {
        campanha.setVisualizacoes(campanha.getVisualizacoes() + 1);
        campanhaRepository.save(campanha);
    }

    public List<Campanha> buscarTop3CampanhasPorTipo(TipoCampanha tipoCampanha){
        return campanhaRepository.findTop3ByTipoCampanhaOrderByVisualizacoesDesc(tipoCampanha);
    }
    public List<Campanha> buscarCampanhasPorGenero(CategoriaCampanha categoriaCampanha){
        return campanhaRepository.findByCategoriaCampanha(categoriaCampanha);
    }

}

