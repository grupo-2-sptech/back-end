package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Organizacao;
import collectiva.org.collecta.dto.OrganizacaoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.OrganizacaoMapper;
import collectiva.org.collecta.repository.OrganizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizacaoService {
    private final OrganizacaoRepository organizacaoRepository;

    public OrganizacaoDTO salvarOrganizacao(OrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = OrganizacaoMapper.paraEntidade(organizacaoDTO);
        organizacaoRepository.save(organizacao);
        return OrganizacaoMapper.paraDTO(organizacao);
    }

    public List<OrganizacaoDTO> buscarTodasOrganizacoes() {
        List<Organizacao> organizacoes = organizacaoRepository.findAll();
        return organizacoes.stream().map(OrganizacaoMapper::paraDTO).collect(Collectors.toList());
    }

    public OrganizacaoDTO buscarOrganizacaoPorId(UUID id) {
        return OrganizacaoMapper.paraDTO(organizacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Organizacao")));
    }

    public OrganizacaoDTO atualizarOrganizacao(UUID id, OrganizacaoDTO organizacaoDTO) {
        buscarOrganizacaoPorId(id);
        Organizacao organizacaoNova = OrganizacaoMapper.paraEntidade(organizacaoDTO);
        organizacaoNova.setId(id);
        organizacaoRepository.save(organizacaoNova);
        return OrganizacaoMapper.paraDTO(organizacaoNova);
    }

    public void deletarOrganizacao(UUID id) {
        if (!organizacaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Organizacao");
        }
        organizacaoRepository.deleteById(id);
    }
}

