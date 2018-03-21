package de.bit_gendorf.bakery.bitsizedbakery;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schueler on 19.03.2018.
 */

public class RegisterRequest extends StringRequest {
    //Back-IP 192.168.2.128
    //Meisterling-IP 192.168.2.137
    private static final String REGISTER_REQUEST_URL = "http://192.168.2.137/dani/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String nachname, String email, String passwort, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nachname", nachname);
        params.put("email", email);
        params.put("passwort", passwort);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
