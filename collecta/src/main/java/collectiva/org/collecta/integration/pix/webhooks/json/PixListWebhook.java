package collectiva.org.collecta.integration.pix.webhooks.json;

import java.util.HashMap;

import org.json.JSONObject;

import collectiva.org.collecta.integration.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixListWebhook {
    public static void main(String[] args) {

        Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

    	HashMap<String, String> params = new HashMap<String, String>();
		params.put("inicio", "2021-01-01T16:01:35Z");
		params.put("fim", "2022-06-30T16:01:35Z");

		try {
			EfiPay efi= new EfiPay(options);
			JSONObject response = efi.call("pixListWebhook", params, new JSONObject());
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
