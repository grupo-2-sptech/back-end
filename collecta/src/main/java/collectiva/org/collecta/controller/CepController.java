package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.EnderecoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/endereco")
public class CepController {
  private static final String cepUrl = "https://viacep.com.br/ws/";

  @GetMapping("/{cep}")
  public EnderecoDTO buscaCep(@PathVariable String cep) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(cepUrl + cep + "/json/", EnderecoDTO.class);
  }
}

