package com.example.leonardo.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrimeiraActivity extends AppCompatActivity {

    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira);

        bt1 = (Button) findViewById(R.id.btIniciar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarSegundaActivity();
            }
        });
    }

    void chamarSegundaActivity(){
        Intent intent = new Intent();
        intent.setClass(PrimeiraActivity.this, SegundaActivity.class);
        startActivity(intent);
        finish();
    }
}
