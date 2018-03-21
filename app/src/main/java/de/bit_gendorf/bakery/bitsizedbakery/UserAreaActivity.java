package de.bit_gendorf.bakery.bitsizedbakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;


public class UserAreaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userarea);

        final EditText tNachname = (EditText) findViewById(R.id.tNachname);
        final EditText eEmail = (EditText) findViewById(R.id.eEmail);
        final TextView welcome = (TextView) findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        String nachname = intent.getStringExtra("nachname");
        String email = intent.getStringExtra("email");

        String message = nachname + ", Wilkommen im Kundenbereich!";
        welcome.setText(message);
        eEmail.setText(email);
        tNachname.setText(nachname);


        //bGast = (Button) findViewById(R.id.bGast);
        //bGast2 = (Button) findViewById(R.id.bGast2);
        //helloWorld = (TextView) findViewById(R.id.tLogin);
    }

    /* public void changeText()
    {
        helloWorld.setText("Goodbye World");
    }
    public void changeColor()
    {
        helloWorld.setTextColor(Color.parseColor("#9d92e8"));
    }
    public void changeSize()
    {
        helloWorld.setTextSize(50);
    }

    public void increaseClicked(View v)
    {
        int currentNumber = Integer.parseInt(helloWorld.getText().toString());
        helloWorld.setText(currentNumber + 1 + "");
    }
    public void decreaseClicked(View v)
    {
        int currentNumber = Integer.parseInt(helloWorld.getText().toString());
        helloWorld.setText(currentNumber - 1 + "");
    } */
}
