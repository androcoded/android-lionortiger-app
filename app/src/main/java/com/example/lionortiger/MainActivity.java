package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public enum Player {
        ONE, TWO, NO
    }

    private Button btnReset;
    private GridLayout mGridLayout;
    Player currentPlayer = Player.ONE;
    Player[] playerChoices = new Player[9];
    int[][] winnerChoices = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8,}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        mGridLayout = findViewById(R.id.gridLayout);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setVisibility(View.GONE);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void isImgviewTapped(View imgView) {

        ImageView isTappedView = (ImageView) imgView;
        int index = Integer.parseInt(isTappedView.getTag().toString());


        if (playerChoices[index] == Player.NO && !gameOver) {
            isTappedView.setTranslationX(-2000);
            playerChoices[index] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                isTappedView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                isTappedView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            isTappedView.animate().translationXBy(2000).alpha(1).rotationBy(3600).setDuration(1000);

            for (int i[] : winnerChoices) {

                if (playerChoices[i[0]] == playerChoices[i[1]] && playerChoices[i[1]] == playerChoices[i[2]] && playerChoices[i[0]] != Player.NO) {

                    String winner = "";
                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    if (currentPlayer == Player.ONE) {
                        winner = "Player Two";
                        Toast.makeText(getApplicationContext(), winner + " is the winner!", Toast.LENGTH_SHORT).show();
                    } else if (currentPlayer == Player.TWO) {
                        winner = "Player One";
                        Toast.makeText(getApplicationContext(), winner + " is the winner!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }

    }

    private void resetGame() {

        for (int index = 0; index < mGridLayout.getChildCount(); index++) {

            ImageView imageView = (ImageView) mGridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0f);

            playerChoices[index] = Player.NO;


        }

        currentPlayer = Player.ONE;
        gameOver = false;
        btnReset.setVisibility(View.GONE);
    }
}
