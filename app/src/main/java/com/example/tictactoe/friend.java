package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class friend extends AppCompatActivity {
EditText e1 , e2 ;
Button b1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        e1 = findViewById(R.id.player11);
        e2 = findViewById(R.id.player2);

        b1 = findViewById(R.id.button2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pl = e1.getText().toString();
                String pl2 = e2.getText().toString();
               if(pl.isEmpty() ){
                   Toast.makeText(friend.this, "please enter the first names", Toast.LENGTH_SHORT).show();
               }
               else if (pl2.isEmpty()){
                   Toast.makeText(friend.this, "please enter the second names", Toast.LENGTH_SHORT).show();
               }
               else {
                   Intent i = new Intent(friend.this , MainActivity.class);
                   i.putExtra("playone" , pl);
                   i.putExtra("playtwo" , pl2);
                   startActivity(i);
               }
            }
        });


    }
}