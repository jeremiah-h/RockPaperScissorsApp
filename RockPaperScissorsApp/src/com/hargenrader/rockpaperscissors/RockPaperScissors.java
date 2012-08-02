package com.hargenrader.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class RockPaperScissors extends Activity implements OnClickListener {
	TextView choice;
	ImageButton rock, paper, scissors;
	int playerCount = 0, compCount = 0, tieCount = 0, roundCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
	}

	private void initialize() {
		choice = (TextView) findViewById(R.id.tvChoicePrompt);
		rock = (ImageButton) findViewById(R.id.ibRock);
		paper = (ImageButton) findViewById(R.id.ibPaper);
		scissors = (ImageButton) findViewById(R.id.ibScissors);
		rock.setOnClickListener(this);
		paper.setOnClickListener(this);
		scissors.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		/**
		 * Called when the user selects the Rock, Paper, or Scissors button as a
		 * choice
		 */
		int playerChoice = 0;
		switch (v.getId()) {
		case R.id.ibRock:
			playerChoice = getResources().getIdentifier("rock", "drawable", getPackageName());
			break;
		case R.id.ibPaper:
			playerChoice = getResources().getIdentifier("paper", "drawable", getPackageName());
			break;
		case R.id.ibScissors:
			playerChoice = getResources().getIdentifier("scissors", "drawable", getPackageName());
			break;
		}
		roundCount++;
		Bundle results = new Bundle();
		results.putInt("pChoice", playerChoice);
		int compChoice = getCompChoice();
		results.putInt("cChoice", compChoice);
		String winner = getWinner(playerChoice, compChoice);
		results.putString("winner", winner);
		results.putInt("rCount", roundCount);
		results.putInt("pScore", playerCount);
		results.putInt("cScore", compCount);
		results.putInt("tScore", tieCount);
		Intent intent = new Intent(RockPaperScissors.this, DisplayChoice.class);
		intent.putExtras(results);
		startActivity(intent);
	}

	public int getCompChoice() {
		int randomNum = 1 + (int) (Math.random() * 3);
		int id = 0;
		switch (randomNum) {
		case 1:
			id = getResources().getIdentifier("rock", "drawable", getPackageName());
			break;
		case 2:
			id = getResources().getIdentifier("paper", "drawable", getPackageName());
			break;
		case 3:
			id = getResources().getIdentifier("scissors", "drawable", getPackageName());
			break;
		}
		return (id);
	}

	public String getWinner(int playerChoice, int compChoice) {
		String winner = "";
		if (playerChoice == getResources().getIdentifier("rock", "drawable", getPackageName())) {
			if (compChoice == getResources().getIdentifier("rock", "drawable", getPackageName())) {
				winner = "It's a tie!!";
				tieCount++;
			} else if (compChoice == getResources().getIdentifier("paper", "drawable", getPackageName())) {
				winner = "The computer wins!";
				compCount++;
			} else {
				winner = "You Win!";
				playerCount++;
			}
		} else if (playerChoice == getResources().getIdentifier("paper", "drawable", getPackageName())) {
			if (compChoice  == getResources().getIdentifier("rock", "drawable", getPackageName())) {
				winner = "You Win!";
				playerCount++;
			} else if (compChoice == getResources().getIdentifier("paper", "drawable", getPackageName())) {
				winner = "It's a tie!";
				tieCount++;
			} else {
				winner = "The computer wins!";
				compCount++;
			}
		} else {
			if (compChoice == getResources().getIdentifier("rock", "drawable", getPackageName())) {
				winner = "The computer wins!";
				compCount++;
			} else if (compChoice == getResources().getIdentifier("paper", "drawable", getPackageName())) {
				winner = "You Win!";
				playerCount++;
			} else {
				winner = "It's a tie!";
				tieCount++;
			}
		}
		return (winner);
	}
}