package collectiva.org.collecta.integration.pix.cob.json;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import collectiva.org.collecta.integration.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixUpdateCharge {
    public static void main(String[] args) throws IOException {
      Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

        JSONObject body = new JSONObject();
        body.put("valor", new JSONObject().put("original", "5.00"));

        try {
          EfiPay efi = new EfiPay(options);
          JSONObject response = efi.call("pixUpdateCharge", params, body);
          System.out.println(response);
        }catch (EfiPayException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
        }
    }
}