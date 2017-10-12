package com.example.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int player_score=0;
    private int computer_score=0;
    private boolean userplays=true; // true = user, false = computer
    private int temp_score=0;
    private int turn=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void roll(View view) throws InterruptedException {
        TextView user_score = (TextView)findViewById(R.id.player_score);
        TextView comp_score = (TextView)findViewById(R.id.computer_score);
        ImageView dice = (ImageView)findViewById(R.id.dice);
        Button roll = (Button)findViewById(R.id.roll);
        Button reset = (Button)findViewById(R.id.reset);
        TextView current = (TextView)findViewById(R.id.current_score);

        Random r = new Random();
        turn = r.nextInt(6)+1;

        switch(turn){
            case 1:
                dice.setImageResource(R.drawable.dice1);
                break;

            case 2:
                dice.setImageResource(R.drawable.dice2);
                break;

            case 3:
                dice.setImageResource(R.drawable.dice3);
                break;

            case 4:
                dice.setImageResource(R.drawable.dice4);
                break;

            case 5:
                dice.setImageResource(R.drawable.dice5);
                break;

            case 6:
                dice.setImageResource(R.drawable.dice6);
                break;

        }

        if(userplays){
            if(player_score>=100){
                TextView win = (TextView)findViewById(R.id.winner);
                win.setText("YOU WIN!!! :)");
                roll.setEnabled(false);
                reset.setEnabled(false);
            }
            if(turn==1){
                hold(user_score);
            }
            else{
                temp_score += turn;
                current.setText("Your turn score : " + Integer.toString(temp_score));
                player_score += temp_score;
                user_score.setText(Integer.toString(player_score));
            }
        }
        else{
            if(computer_score>=100){
                TextView win = (TextView)findViewById(R.id.winner);
                win.setText("YOU LOSE!!! :(");
                roll.setEnabled(false);
                reset.setEnabled(false);
            }
            int c_hold = new Random().nextInt(10)+5;
            if(c_hold==8){
                hold(comp_score);
            }
            else if(turn==1){
                hold(comp_score);
            }
            else{
                temp_score += turn;
                current.setText("Computer's turn score : " + Integer.toString(temp_score));
                computer_score += temp_score;
                comp_score.setText(Integer.toString(computer_score));
                Thread.sleep(1000);
                roll(comp_score);
            }
        }

    }

    public void hold(View view) throws InterruptedException {
        TextView user_score = (TextView)findViewById(R.id.player_score);
        TextView comp_score = (TextView)findViewById(R.id.computer_score);
        Button user_roll = (Button)findViewById(R.id.roll);

        if(userplays){
            user_roll.setEnabled(false);
            if(turn==1){
                player_score -= temp_score;
            }
            temp_score = 0;
            user_score.setText(Integer.toString(player_score));
            userplays = false;
            try {
                roll(comp_score);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            user_roll.setEnabled(true);
            if(turn==1){
                computer_score -= temp_score;
            }
            temp_score = 0;
            comp_score.setText(Integer.toString(computer_score));
            userplays = true;
        }


    }

    public void reset(View view){
        TextView user_score = (TextView)findViewById(R.id.player_score);
        TextView comp_score = (TextView)findViewById(R.id.computer_score);
        user_score.setText(Integer.toString(0));
        comp_score.setText(Integer.toString(0));
    }
}
