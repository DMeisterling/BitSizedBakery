package de.bit_gendorf.bakery.bitsizedbakery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText eEmail = (EditText) findViewById(R.id.eEmail);
        final EditText pPasswort = (EditText) findViewById(R.id.pPasswort);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView tRegistrierenKlein = (TextView) findViewById(R.id.tRegistrierenKlein);

        tRegistrierenKlein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegistrierenKleinIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(RegistrierenKleinIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = eEmail.getText().toString();
                final String passwort = pPasswort.getText().toString();
                /* AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Click registriert")
                        .create()
                        .show(); */



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String nachname = jsonResponse.getString("nachname");
                                String mail = jsonResponse.getString("email");

                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("nachname",nachname);
                                intent.putExtra("email", mail);
                                LoginActivity.this.startActivity(intent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login erfolgreich")
                                        .create()
                                        .show();


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login fehlgeschlagen")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                /* Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(500); */
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(500);
                            AlertDialog.Builder builder8 = new AlertDialog.Builder(LoginActivity.this);
                            builder8.setMessage(e.toString())
                                    .create()
                                    .show();
                        }

                    }

                };
                LoginRequest loginRequest = new LoginRequest(email,passwort, responseListener );
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
