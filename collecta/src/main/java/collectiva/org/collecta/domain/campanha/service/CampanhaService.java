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

    public void incrementarVisualizacao(Campanha campanha) {
        campanha.setVisualizacoes(campanha.getVisualizacoes() + 1);
        campanhaRepository.save(campanha);
    }

    public List<Campanha> buscarTodasCampanhas() {
        return campanhaRepository.findAll();
    }

    public List<Campanha> buscarTop3CampanhasPorTipo(TipoCampanha tipoCampanha) {
        return campanhaRepository.findTop3ByTipoCampanhaOrderByVisualizacoesDesc(tipoCampanha);
    }

    public List<Campanha> buscarCampanhasPorGenero(CategoriaCampanha categoriaCampanha) {
        return campanhaRepository.findByCategoriaCampanha(categoriaCampanha);
    }

    public Campanha buscarCampanhaPorId(UUID id) {
        return campanhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Campanha"));
    }

    public Campanha criarCampanha(Campanha campanha, Organizacao organizacao) {
        campanha.setOrganizacao(organizacao);
        return campanhaRepository.save(campanha);
    }

    public Campanha atualizarCampanha(UUID idCampanha, Campanha campanha) {
        Campanha novaCampanha = buscarCampanhaPorId(idCampanha);
        novaCampanha.setNome(campanha.getNome());
        novaCampanha.setDescricao(campanha.getDescricao());
        novaCampanha.setDataFim(campanha.getDataFim());
        novaCampanha.setCategoriaCampanha(campanha.getCategoriaCampanha());
        novaCampanha.setTipoCampanha(campanha.getTipoCampanha());
        return campanhaRepository.save(novaCampanha);
    }

    public void deletarCampanha(UUID id) {
        buscarCampanhaPorId(id);
        campanhaRepository.deleteById(id);
    }
}

