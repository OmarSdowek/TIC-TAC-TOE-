package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class computr extends AppCompatActivity {
    ImageView block1 ,block2 ,block3 ,block4 ,block5 ,block6 ,block7 , block8, block9  ;
    private int[] boxposition = {0 ,0, 0 ,0 ,0 ,0, 0 ,0, 0};
    private int playertun = 1;
    private boolean active = true;
    private int selectedboxes = 1;
    private final List<int[]> cc= new ArrayList<>();
    TextView tv , play1 , num1;
    ImageView img ;
    Button b , h;
    MediaPlayer mp , mp2 , clickSound ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView2);
        mp2 =MediaPlayer.create(getApplicationContext(),R.raw.win);



        block1 = findViewById(R.id.block1);
        block2 = findViewById(R.id.block2);
        block3 = findViewById(R.id.block3);
        block4 = findViewById(R.id.block4);
        block5 = findViewById(R.id.block5);
        block6 = findViewById(R.id.block6);
        block7 = findViewById(R.id.block7);
        block8 = findViewById(R.id.block8);
        block9 = findViewById(R.id.block9);
        b = findViewById(R.id.button);



        cc.add(new int[] {0 ,1 ,2});
        cc.add(new int[] {3, 4, 5});
        cc.add(new int[] {6, 7, 8});
        cc.add(new int[] {0, 3, 6});
        cc.add(new int[] {1, 4, 7});
        cc.add(new int[] {2, 5, 8});
        cc.add(new int[] {0, 4, 8});
        cc.add(new int[] {2, 4, 6});



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameReset(v);

            }
        });

        block1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(0) && active == true){
                    Action(  (ImageView) v , 0);



                }
            }
        });
        block2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(1) && active == true){
                    Action(  (ImageView) v , 1);

                }
            }
        });
        block3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(2 )&& active == true){
                    Action(  (ImageView) v , 2);

                }
            }
        });
        block4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(3)&& active == true){
                    Action(  (ImageView) v , 3);

                }
            }
        });
        block5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(4)&& active == true){
                    Action(  (ImageView) v , 4);

                }
            }
        });
        block6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(5)&& active == true){
                    Action(  (ImageView) v , 5);

                }
            }
        });
        block7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(6)&& active == true){
                    Action(  (ImageView) v , 6);

                }
            }
        });
        block8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(7)&& active == true){
                    Action(  (ImageView) v , 7);

                }
            }
        });
        block9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxfull(8)&& active == true){
                    Action(  (ImageView) v , 8);

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp != null){
            mp.release();
            mp = null;
        }
    }

    private void change(int selectionbox){

        playertun = selectionbox;
        if(playertun == 1){
            tv.setText(R.string.X_tap_tp_play);
        }
        else {
            tv.setText(R.string.Computer);
        }
    }

    private boolean check(){
        boolean R = false;
        for(int i =0 ; i < cc.size() ; i++){
            final int[] cc2 = cc.get(i);
            if(boxposition[cc2[0]] == playertun && boxposition[cc2[1]] == playertun
                    && boxposition[cc2[2]] == playertun){
                R = true;
            }
        }
        return  R;
    }

    private boolean isBoxfull(int box){

        boolean R = false;
        if(boxposition[box] == 0){
            return true;
        }
        return R;
    }


    @SuppressLint("SetTextI18n")
    private void Action(ImageView img , int selected){
        if(!active){return;}
        boxposition[selected] = playertun;
        img.setTranslationY(-1000f);

        if(playertun == 1){
            img.setImageResource(R.drawable.x);

            if(check()){
                tv.setText(R.string.X_is_winner);
                mp2.start();
                active = false;
            }
            else if(selectedboxes == 9){
                tv.setText(R.string.The_match_is_draw);
                active = false;
            }
            else {
                change(2);
                selectedboxes++;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                performComputerAction();
                            }
                        });
                    }
                }, new Random().nextInt(2001) + 1000);
            }

        }

        else {
            img.setImageResource(R.drawable.o);
            if (check()) {
                tv.setText(R.string.Compuer_is_win);
                mp2.start();
                active = false;
            } else if (selectedboxes == 9) {
                tv.setText(R.string.The_match_is_draw);
                active = false;
            } else {
                change(1);
                selectedboxes++;
            }
        }
        img.animate().translationYBy(1000f).setDuration(300);
    }
    private void performComputerAction() {
        if (!active) {
            return;
        }
        Random r = new Random();
        int computermove;
        do{
            computermove = r.nextInt(9);
        }while(boxposition[computermove] != 0 );
        ImageView selectableBlock = getImageViewByIndex(computermove);
        Action(selectableBlock , computermove);

    }
    private ImageView getImageViewByIndex(int index) {
        switch (index) {
            case 0:
                return block1;
            case 1:

                return block2;
            case 2:
                return block3;
            case 3:

                return block4;
            case 4:
                return block5;
            case 5:
                return block6;
            case 6:
                return block7;
            case 7:
                return block8;
            case 8:
                return block9;
            default:
                return null;
}
    }
    public void gameReset(View view) {
        playertun = 1;
        selectedboxes = 1;
        boxposition = new int[]{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0};
        active = true;

        ((ImageView) findViewById(R.id.block1)).setImageResource(0);
        ((ImageView) findViewById(R.id.block2)).setImageResource(0);
        ((ImageView) findViewById(R.id.block3)).setImageResource(0);
        ((ImageView) findViewById(R.id.block4)).setImageResource(0);
        ((ImageView) findViewById(R.id.block5)).setImageResource(0);
        ((ImageView) findViewById(R.id.block6)).setImageResource(0);
        ((ImageView) findViewById(R.id.block7)).setImageResource(0);
        ((ImageView) findViewById(R.id.block8)).setImageResource(0);
        ((ImageView) findViewById(R.id.block9)).setImageResource(0);

        tv.setText(R.string.X_tap_tp_play);
    }
}