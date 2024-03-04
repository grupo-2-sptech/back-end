package collectiva.org.collecta.domain.conta.doador.service;


import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.ResponseDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.UpdateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.mapper.DoadorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoadorServiceFacade {
    private final DoadorService doadorService;

    public List<ResponseDoadorDTO> buscarTodosDoadores() {
        return doadorService.buscarTodosDoadores().stream().map
                (DoadorMapper::paraDTO).toList();
    }

    public ResponseDoadorDTO buscarDoadorPorId(UUID id) {
        return DoadorMapper.paraDTO(doadorService.buscarDoadorPorId(id));
    }

    public AssociationDoadorDTO atualizarDoador(UUID idDoador, UpdateDoadorDTO doadorDTO) {
        Doador doador = DoadorMapper.paraEntidadeUpdate(doadorDTO);
        return DoadorMapper.paraAssociacaoDTO(doadorService.atualizarDoador(idDoador, doador));
    }

    public void deletarDoador(UUID id) {
        doadorService.deletarDoador(id);
    }

}
