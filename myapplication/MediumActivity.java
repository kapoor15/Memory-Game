package com.example.ak.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MediumActivity extends AppCompatActivity {

    ArrayList<String> selected;
    boolean isFirstCard;
    Button firstButton;
    Button secondButton;
    int matchesLeft;
    int numMoves;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);


        //initialize variables
        selected = new ArrayList<String>();
        isFirstCard = true;
        firstButton = null;
        secondButton = null;
        matchesLeft = 6;
        numMoves = 0;
        handler = new Handler();

        //update everything for top screen
        ((TextView) findViewById(R.id.matches_text)).setText("Matches Remaining : " + matchesLeft);
        ((TextView) findViewById(R.id.moves_text)).setText("Moves : " + numMoves);

        //create all cards
        String[] number = new String [13];
        String[] suit = new String[4];

        number[0] = "1";
        number[1] = "2";
        number[2] = "3";
        number[3] = "4";
        number[4] = "5";
        number[5] = "6";
        number[6] = "7";
        number[7] = "8";
        number[8] = "9";
        number[9] = "10";
        number[10] = "J";
        number[11] = "Q";
        number[12] = "K";

        suit[0] = "Spades";
        suit[1] = "Clubs";
        suit[2] = "Diamonds";
        suit[3] = "Hearts";

        //put cards in the list
        ArrayList<String> cards = new ArrayList<String>();

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(number[i] + " of " + suit[j]);
            }
        }

        //shuffle cards and add to selected list
        Collections.shuffle(cards);

        selected.add(cards.get(0));
        selected.add(cards.get(0));
        selected.add(cards.get(1));
        selected.add(cards.get(1));
        selected.add(cards.get(2));
        selected.add(cards.get(2));
        selected.add(cards.get(3));
        selected.add(cards.get(3));
        selected.add(cards.get(4));
        selected.add(cards.get(4));
        selected.add(cards.get(5));
        selected.add(cards.get(5));

        Collections.shuffle(selected);

        //create the 6 buttons
        Button bt0 = (Button)findViewById(R.id.bt0);
        Button bt1 = (Button)findViewById(R.id.bt1);
        Button bt2 = (Button)findViewById(R.id.bt2);
        Button bt3 = (Button)findViewById(R.id.bt3);
        Button bt4 = (Button)findViewById(R.id.bt4);
        Button bt5 = (Button)findViewById(R.id.bt5);
        Button bt6 = (Button)findViewById(R.id.bt6);
        Button bt7 = (Button)findViewById(R.id.bt7);
        Button bt8 = (Button)findViewById(R.id.bt8);
        Button bt9 = (Button)findViewById(R.id.bt9);
        Button bt10 = (Button)findViewById(R.id.bt10);
        Button bt11 = (Button)findViewById(R.id.bt11);

        //set identifiers for the buttons
        bt0.setTag((int) 0);
        bt1.setTag((int) 1);
        bt2.setTag((int) 2);
        bt3.setTag((int) 3);
        bt4.setTag((int) 4);
        bt5.setTag((int) 5);
        bt6.setTag((int) 6);
        bt7.setTag((int) 7);
        bt8.setTag((int) 8);
        bt9.setTag((int) 9);
        bt10.setTag((int) 10);
        bt11.setTag((int) 11);

        //make color better
        bt0.setBackgroundResource(android.R.drawable.btn_default);
        bt1.setBackgroundResource(android.R.drawable.btn_default);
        bt2.setBackgroundResource(android.R.drawable.btn_default);
        bt3.setBackgroundResource(android.R.drawable.btn_default);
        bt4.setBackgroundResource(android.R.drawable.btn_default);
        bt5.setBackgroundResource(android.R.drawable.btn_default);
        bt6.setBackgroundResource(android.R.drawable.btn_default);
        bt7.setBackgroundResource(android.R.drawable.btn_default);
        bt8.setBackgroundResource(android.R.drawable.btn_default);
        bt9.setBackgroundResource(android.R.drawable.btn_default);
        bt10.setBackgroundResource(android.R.drawable.btn_default);
        bt11.setBackgroundResource(android.R.drawable.btn_default);

    }

    public void cardFlipped(View v) {

        Button b = (Button) v;
        int id = Integer.parseInt(b.getTag().toString());
        b.setText(selected.get(id));
        if (isFirstCard) {
            isFirstCard = false;
            firstButton = b;
            b.setClickable(false);
        }
        else {
            secondButton = b;
            //check for match
            int firstId = Integer.parseInt(firstButton.getTag().toString());
            //when there is a match
            if (selected.get(id).equals(selected.get(firstId))) {
                //set colors to green
                firstButton.setBackgroundColor( -16711936);
                b.setBackgroundColor(-16711936);
                //set unclickable
                firstButton.setClickable(false);
                b.setClickable(false);
                //update matches
                matchesLeft--;
                //update moves
                numMoves++;

                //set tag to 99
                firstButton.setTag((int) 99);
                b.setTag((int) 99);

                makeAllUnclickable();
                this.handler.postDelayed(new checkAgainClass(), 1200);

            }
            // no match
            else {
                //set red colors
                firstButton.setBackgroundColor(-65536);
                b.setBackgroundColor(-65536);
                //update moves
                numMoves++;
                //set unclickable
                firstButton.setClickable(false);
                b.setClickable(false);

                makeAllUnclickable();
                this.handler.postDelayed(new checkAgainClass(), 1200);
            }
        }
    }

    class checkAgainClass implements Runnable {
        checkAgainClass() {
        }

        public void run() {
            MediumActivity.this.checkAgain();
        }
    }

    public void checkAgain() {
        //check if game is over
        if (matchesLeft == 0) {
            Toast.makeText(getApplicationContext(), "You just won! Back to menu", Toast.LENGTH_SHORT).show();
            this.handler.postDelayed(new exitClass(), 2500);
        }
        //when game is not over
        else {
            makeValidClickable();
            if (Integer.parseInt(firstButton.getTag().toString()) != 99) {
                firstButton.setText("");
                secondButton.setText("");
                firstButton.setBackgroundResource(android.R.drawable.btn_default);
                secondButton.setBackgroundResource(android.R.drawable.btn_default);
            }
            else {
                firstButton.setBackgroundResource(android.R.drawable.btn_default);
                secondButton.setBackgroundResource(android.R.drawable.btn_default);
            }
        }
        //update everything for top screen
        ((TextView) findViewById(R.id.matches_text)).setText("Matches Remaining : " + matchesLeft);
        ((TextView) findViewById(R.id.moves_text)).setText("Moves : " + numMoves);

        firstButton = null;
        secondButton = null;
        isFirstCard = true;
    }

    class exitClass implements Runnable {
        exitClass() {
        }

        public void run() {
            MediumActivity.this.exit();
        }
    }

    public void exit() {
        finish();
    }

    private void makeAllUnclickable() {
        ((Button) findViewById(R.id.bt0)).setClickable(false);
        ((Button) findViewById(R.id.bt1)).setClickable(false);
        ((Button) findViewById(R.id.bt2)).setClickable(false);
        ((Button) findViewById(R.id.bt3)).setClickable(false);
        ((Button) findViewById(R.id.bt4)).setClickable(false);
        ((Button) findViewById(R.id.bt5)).setClickable(false);
        ((Button) findViewById(R.id.bt6)).setClickable(false);
        ((Button) findViewById(R.id.bt7)).setClickable(false);
        ((Button) findViewById(R.id.bt8)).setClickable(false);
        ((Button) findViewById(R.id.bt9)).setClickable(false);
        ((Button) findViewById(R.id.bt10)).setClickable(false);
        ((Button) findViewById(R.id.bt11)).setClickable(false);
    }

    private void makeValidClickable() {
        Button b = (Button) findViewById(R.id.bt0);
        int checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt1);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt2);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt3);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt4);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt5);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt6);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt7);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt8);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt9);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt10);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }

        b = (Button) findViewById(R.id.bt11);
        checker = Integer.parseInt(b.getTag().toString());
        if (checker != 99) {
            b.setClickable(true);
        }
    }

}
