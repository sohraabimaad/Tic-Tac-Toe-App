package com.example.deepak.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //set of positions in the board that are already used
    static HashSet<Integer> positions = new HashSet<Integer>();
    //boolean variable to determine if the game has to be continued
    boolean isContinue = true;
    //boolean variable to check if a player won
    boolean isWin = false;
    FirstRunnable f = new FirstRunnable("First");
    SecondRunnable s = new SecondRunnable("Second");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.start);
        f.start();
        s.start();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clearing the board and the game settings if the game is restarted in between
                Button b0 = (Button) findViewById(R.id.button0);
                Button b1 = (Button) findViewById(R.id.button1);
                Button b2 = (Button) findViewById(R.id.button2);
                Button b3 = (Button) findViewById(R.id.button3);
                Button b4 = (Button) findViewById(R.id.button4);
                Button b5 = (Button) findViewById(R.id.button5);
                Button b6 = (Button) findViewById(R.id.button6);
                Button b7 = (Button) findViewById(R.id.button7);
                Button b8 = (Button) findViewById(R.id.button8);
                b0.setText("");
                b0.setEnabled(true);
                b1.setText("");
                b1.setEnabled(true);
                b2.setText("");
                b2.setEnabled(true);
                b3.setText("");
                b3.setEnabled(true);
                b4.setText("");
                b4.setEnabled(true);
                b5.setText("");
                b5.setEnabled(true);
                b6.setText("");
                b6.setEnabled(true);
                b7.setText("");
                b7.setEnabled(true);
                b8.setText("");
                b8.setEnabled(true);
                positions.clear();
                isContinue = true;
                isWin = false;
                /*clearing the handler queue so that if the game is restarted in between
                  the messages which were sent earlier are not discarded.*/
                f.fHandler.removeCallbacksAndMessages(null);
                s.sHandler.removeCallbacksAndMessages(null);
                Message msg = new Message();
                //send a message to first worker thread to make its move
                f.fHandler.sendMessageDelayed(msg, 750);
            }
        });
    }

    public void isEndGame() {

        String s0, s1, s2, s3, s4, s5, s6, s7, s8;
        Button b0 = (Button) findViewById(R.id.button0);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);

        s0 = b0.getText().toString();
        s1 = b1.getText().toString();
        s2 = b2.getText().toString();

        s3 = b3.getText().toString();
        s4 = b4.getText().toString();
        s5 = b5.getText().toString();

        s6 = b6.getText().toString();
        s7 = b7.getText().toString();
        s8 = b8.getText().toString();

        //checking if either of the player has won the game
        if (s0.equals(s1) && s1.equals(s2) && s0.equals("X")) {
            isContinue = false;
        } else if (s0.equals(s4) && s4.equals(s8) && s0.equals("X")) {
            isContinue = false;
        } else if (s0.equals(s3) && s3.equals(s6) && s0.equals("X")) {
            isContinue = false;
        } else if (s1.equals(s4) && s4.equals(s7) && s1.equals("X")) {
            isContinue = false;
        } else if (s2.equals(s5) && s5.equals(s8) && s2.equals("X")) {
            isContinue = false;
        } else if (s3.equals(s4) && s4.equals(s5) && s3.equals("X")) {
            isContinue = false;
        } else if (s6.equals(s7) && s7.equals(s8) && s6.equals("X")) {
            isContinue = false;
        } else if (s2.equals(s4) && s4.equals(s6) && s2.equals("X")) {
            isContinue = false;
        } else if (s0.equals(s1) && s1.equals(s2) && s0.equals("O")) {
            isContinue = false;
        } else if (s0.equals(s4) && s4.equals(s8) && s0.equals("O")) {
            isContinue = false;
        } else if (s0.equals(s3) && s3.equals(s6) && s0.equals("O")) {
            isContinue = false;
        } else if (s1.equals(s4) && s4.equals(s7) && s1.equals("O")) {
            isContinue = false;
        } else if (s2.equals(s5) && s5.equals(s8) && s2.equals("O")) {
            isContinue = false;
        } else if (s3.equals(s4) && s4.equals(s5) && s3.equals("O")) {
            isContinue = false;
        } else if (s6.equals(s7) && s7.equals(s8) && s6.equals("O")) {
            isContinue = false;
        } else if (s2.equals(s4) && s4.equals(s6) && s2.equals("O")) {
            isContinue = false;
        } else {
            isContinue = true;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            int pos = msg.arg1;
            Button b = getButtonAt(pos);
            displayMessage(what, b);
        }
    };

    private Button getButtonAt(int position) {
        Button b = null;
            //determine the position in the board where the move has to be made.
            switch (pos) {
                case 0:
                    b = (Button) findViewById(R.id.button0);
                    break;
                case 1:
                    b = (Button) findViewById(R.id.button1);
                    break;
                case 2:
                    b = (Button) findViewById(R.id.button2);
                    break;
                case 3:
                    b = (Button) findViewById(R.id.button3);
                    break;
                case 4:
                    b = (Button) findViewById(R.id.button4);
                    break;
                case 5:
                    b = (Button) findViewById(R.id.button5);
                    break;
                case 6:
                    b = (Button) findViewById(R.id.button6);
                    break;
                case 7:
                    b = (Button) findViewById(R.id.button7);
                    break;
                case 8:
                    b = (Button) findViewById(R.id.button8);
                    break;
            }
        return b;
    }

    private void displayMessage(int w, Button b) {
        switch (what) {
            case 1:
                //Player-X has made its move, so "X" has to be marked in the corresponding position
                b.setText("X");
                b.setTextColor(Color.parseColor("Red"));
                b.setEnabled(false);
                isEndGame();
                // Display toast message if Player-X won the game
                if (!isContinue) {
                    if (!isWin) {
                        isWin = true;
                        Toast.makeText(MainActivity.this, "Player-X wins", Toast.LENGTH_LONG).show();
                    }
                }
                //Display game over toast message when the game ends.
                if (positions.size() == 9) {
                    isContinue=false;
                    Toast.makeText(MainActivity.this, "Game Over!!!", Toast.LENGTH_LONG).show();
                }
                //Notify Player-O to make its move.
                if (isContinue) {
                    Message msg1 = new Message();
                    s.sHandler.sendMessageDelayed(msg1, 1500);
                }
                break;
            case 2:
                //Player-O has made its move, so "O" has to be marked in the corresponding position
                b.setText("O");
                b.setTextColor(Color.parseColor("Blue"));
                b.setEnabled(false);
                isEndGame();
                // Display toast message if Player-O won the game
                if (!isContinue) {
                    if (!isWin) {
                        isWin = true;
                        Toast.makeText(MainActivity.this, "Player-O wins", Toast.LENGTH_LONG).show();
                    }
                }
                //Display game over toast message when the game ends
                if (positions.size() == 9) {
                    isContinue=false;
                    Toast.makeText(MainActivity.this, "Game Over!!!", Toast.LENGTH_LONG).show();
                }
                //Notify Player-X to make its move
                if (isContinue) {
                    Message msg1 = new Message();
                    f.fHandler.sendMessageDelayed(msg1, 1500);
                }
                break;
        }
    }
    public class FirstRunnable extends HandlerThread {

        Handler fHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                /* Generating a random number between 0-8 to decide a position on the
                 on which a move can be made by player-X */
                Random rand = new Random();
                int r = rand.nextInt(9);
                // if this position is already used, find a new unused position
                if (positions.contains(r)) {
                    while (positions.contains(r)) {
                        r = rand.nextInt(9);
                    }
                }
                positions.add(r);
                // notify UI thread to update the game board
                Message msg1 = new Message();
                msg1.what = 1;
                msg1.arg1 = r;
                mHandler.sendMessage(msg1);
            }
        };

        public FirstRunnable(String name) {
            super(name);
        }

        public void run() {

        }
    }

    public class SecondRunnable extends HandlerThread {

        Handler sHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                /* Generating a random number between 0-8 to decide a position on the
                 on which a move can be made by player-O */
                Random rand = new Random();
                int r = rand.nextInt(9);
                // if this position is already used, find a new unused position
                if (positions.contains(r)) {
                    while (positions.contains(r)) {
                        r = rand.nextInt(9);
                    }
                }
                positions.add(r);
                // notify UI thread to update the game board
                Message msg1 = new Message();
                msg1.what = 2;
                msg1.arg1 = r;
                mHandler.sendMessage(msg1);
            }
        };

        public SecondRunnable(String name) {
            super(name);
        }

        public void run() {

        }
    }
}