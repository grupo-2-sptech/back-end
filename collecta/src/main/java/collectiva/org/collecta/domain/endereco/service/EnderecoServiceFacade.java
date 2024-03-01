package collectiva.org.collecta.domain.endereco.service;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.dto.AssociationEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.CreateEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.ResponseEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.UpdateEnderecoDTO;
import collectiva.org.collecta.domain.endereco.mapper.EnderecoMapper;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoServiceFacade {
    private final EnderecoService enderecoService;
    private final EventoCampanhaService eventoCampanhaService;

    public List<ResponseEnderecoDTO> buscarTodosEnderecos() {
        return enderecoService.buscarTodosEnderecos().stream().map
                (EnderecoMapper::paraDTO).toList();
    }

    public ResponseEnderecoDTO buscarEnderecoPorId(UUID id) {
        return EnderecoMapper.paraDTO(enderecoService.buscarEnderecoPorId(id));
    }

    public AssociationEnderecoDTO criarEndereco(UUID idEventoCampanha, CreateEnderecoDTO enderecoDTO) {
        EventoCampanha eventoCampanha = eventoCampanhaService.buscarEventoCampanhaPorId(idEventoCampanha);
        Endereco endereco = EnderecoMapper.paraEntidade(enderecoDTO);
        return EnderecoMapper.paraAssociacaoDTO(enderecoService.criarEndereco(endereco, eventoCampanha));
    }

    public AssociationEnderecoDTO atualizarEndereco(UUID idEndereco, UpdateEnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.paraEntidadeUpdate(enderecoDTO);
        return EnderecoMapper.paraAssociacaoDTO(enderecoService.atualizarEndereco(idEndereco, endereco));
    }

    public void deletarEndereco(UUID id) {
        enderecoService.deletarEndereco(id);
    }

}
