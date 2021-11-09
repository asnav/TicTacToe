package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOne, playerOneScore, playerTwo, playerTwoScore, announcement;
    private Button[] gameBoard= new Button[9];
    private Button clearButton;

    private int playerOneScoreCount = 0, playerTwoScoreCount = 0, turnCount = 0, currentPlayer = 1;
    int [] boardState = {0,0,0,0,0,0,0,0,0};
    int [][] winningSets = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = (TextView) findViewById(R.id.playerOne);
        playerOne.setTypeface(null, Typeface.BOLD);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        announcement = (TextView) findViewById(R.id.announcement);
        clearButton = (Button) findViewById(R.id.clearButton);
        //clearButton.setOnClickListener(this);

        gameBoard[0] = (Button) findViewById(R.id.topLeftSpot);
        gameBoard[1] = (Button) findViewById(R.id.topCenterSpot);
        gameBoard[2] = (Button) findViewById(R.id.topRightSpot);
        gameBoard[3] = (Button) findViewById(R.id.centerLeftSpot);
        gameBoard[4] = (Button) findViewById(R.id.centerCenterSpot);
        gameBoard[5] = (Button) findViewById(R.id.centerRightSpot);
        gameBoard[6] = (Button) findViewById(R.id.bottomLeftSpot);
        gameBoard[7] = (Button) findViewById(R.id.bottomCenterSpot);
        gameBoard[8] = (Button) findViewById(R.id.bottomRightSpot);

        for(int i = 0 ; i < gameBoard.length ; i++)
            gameBoard[i].setOnClickListener(this);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                announcement.setText("");
                for(Button spot : gameBoard) {
                    spot.setEnabled(true);
                    spot.setText("");
                    spot.setBackgroundColor(0xFFAAB5B5);
                }
                boardState = new int[] {0,0,0,0,0,0,0,0,0};
                turnCount = 0;
                currentPlayer = 1;
                playerOne.setTypeface(null, Typeface.BOLD);
                playerTwo.setTypeface(null, Typeface.NORMAL);
            }
        });
    }

    @Override
    public void onClick(View v) {

        ((Button)v).setEnabled(false);

        String ButtonID = v.getResources().getResourceEntryName(v.getId());
        switch(ButtonID) {
            case "topLeftSpot":
                boardState[0] = currentPlayer;
                break;
            case "topCenterSpot":
                boardState[1] = currentPlayer;
                break;
            case "topRightSpot":
                boardState[2] = currentPlayer;
                break;
            case "centerLeftSpot":
                boardState[3] = currentPlayer;
                break;
            case "centerCenterSpot":
                boardState[4] = currentPlayer;
                break;
            case "centerRightSpot":
                boardState[5] = currentPlayer;
                break;
            case "bottomLeftSpot":
                boardState[6] = currentPlayer;
                break;
            case "bottomCenterSpot":
                boardState[7] = currentPlayer;
                break;
            case "bottomRightSpot":
                boardState[8] = currentPlayer;
                break;
        }

        if(currentPlayer == 1) {
            ((Button) v).setText("X");
            currentPlayer = 2;
            playerOne.setTypeface(null, Typeface.NORMAL);
            playerTwo.setTypeface(null, Typeface.BOLD);
        }
        else {
            ((Button) v).setText("O");
            currentPlayer = 1;
            playerOne.setTypeface(null, Typeface.BOLD);
            playerTwo.setTypeface(null, Typeface.NORMAL);
        }
        turnCount++;
        if(turnCount == 9) {
            announcement.setText("Its a Draw");
            playerOne.setTypeface(null, Typeface.BOLD);
            playerTwo.setTypeface(null, Typeface.BOLD);
        }
        if(gameOver()) {
            for (Button spot : gameBoard)
                spot.setEnabled(false);
            announceWinner();
        }
    }

    private boolean gameOver() {

        for(int[] winningSet : winningSets){
            if(boardState[winningSet[0]] == boardState[winningSet[1]]
                    && boardState[winningSet[1]] == boardState[winningSet[2]]
                    && boardState[winningSet[0]] != 0) {
                for(int i : winningSet)
                    gameBoard[i].setBackgroundColor(0xFF33EE66);
                return true;
            }
        }
        return false;
    }

    private void announceWinner() {
        if(currentPlayer == 2) {
            announcement.setText("Player One Won");
            playerOneScoreCount++;
            playerOneScore.setText(Integer.toString(playerOneScoreCount));
        }
        else {
            announcement.setText("Player Two Won");
            playerTwoScoreCount++;
            playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
        }
        playerOne.setTypeface(null, Typeface.BOLD);
        playerTwo.setTypeface(null, Typeface.BOLD);
    }

}