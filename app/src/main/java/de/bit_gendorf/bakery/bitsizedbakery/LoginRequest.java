package de.bit_gendorf.bakery.bitsizedbakery;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schueler on 20.03.2018.
 */

public class LoginRequest extends StringRequest {

    //Back-IP 192.168.2.128
    //Meisterling-IP 192.168.2.137
    private static final String LOGIN_REQUEST_URL = "http://192.168.2.137/dani/Login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String passwort, Response.Listener<String> listener)
    {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("passwort", passwort);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }

}
