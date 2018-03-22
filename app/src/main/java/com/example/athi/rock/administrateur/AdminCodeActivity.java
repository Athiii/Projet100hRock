package com.example.athi.rock.administrateur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.athi.rock.R;

public class AdminCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_code);
        Button btnValider =(Button) findViewById(R.id.btn_modeAdmin);
        final String mdp = "rock2018";
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText motdepasse =(EditText) findViewById(R.id.id_mdp);
                String mot = motdepasse.getText().toString();
                Toast.makeText(getApplicationContext(),"mot de passe tapé: "+ mot,Toast.LENGTH_SHORT).show();
                if(mot.equals(mdp)) {
                    Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), AdministrateurActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Faux",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
