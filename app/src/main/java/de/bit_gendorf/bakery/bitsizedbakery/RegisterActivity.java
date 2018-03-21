package de.bit_gendorf.bakery.bitsizedbakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText tNachname = (EditText) findViewById(R.id.tNachname);
        final EditText eEmail = (EditText) findViewById(R.id.eEmail);
        final EditText pPasswort = (EditText) findViewById(R.id.pPasswort);
        final Button bRegistrieren = (Button) findViewById(R.id.bRegistrieren);

        bRegistrieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nachname = tNachname.getText().toString();
                final String eMail = eEmail.getText().toString();
                final String passwort = pPasswort.getText().toString();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage("Click registriert")
                        .create()
                        .show(); */

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            boolean success = response.contains("true");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registrierung erfolgreich")
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registrierung fehlgeschlagen")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Fehler JSON")
                                    .create()
                                    .show();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(nachname, eMail, passwort, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
