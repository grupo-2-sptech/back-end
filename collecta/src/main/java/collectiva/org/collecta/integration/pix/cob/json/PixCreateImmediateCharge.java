package collectiva.org.collecta.integration.pix.cob.json;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import collectiva.org.collecta.integration.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateImmediateCharge {
    public static List<String> CobrancaRapida(String nomeDoador, String cpfDoador, String valorDoacao) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

    
        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 36000));
        body.put("devedor", new JSONObject().put("cpf", cpfDoador).put("nome", nomeDoador));
        body.put("valor", new JSONObject().put("original", valorDoacao));
        body.put("chave", "782c3d51-827b-4d58-aaa8-22c074adfc2e");
        body.put("solicitacaoPagador", "Serviço realizado.");

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        body.put("infoAdicionais", infoAdicionais);

        List<String> dados = new ArrayList<>();

        try {
          EfiPay efi = new EfiPay(options);
          JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);

          JSONObject locObj = response.getJSONObject("loc");
          Object id = locObj.get("id");
          Object txid = response.get("txid");

          dados.add(id.toString());
          dados.add(txid.toString());

          return dados;


        }catch (EfiPayException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
          return dados;
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
          return dados;
        }
	}
}
