package com.hargenrader.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FinalResults extends Activity implements OnClickListener {
	TextView tvFinalWinner, yourScore, compScore, tieScore;
	Button playAgain;
	int yScore, cScore, tScore;
	String winner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		initialize();
		
		// Initialize Bundle
		Bundle totalResults = getIntent().getExtras();
		
		// Get the scores from the totalResults Bundle
		yScore = totalResults.getInt("pScore");
		cScore = totalResults.getInt("cScore");
		tScore = totalResults.getInt("tScore");
		
		// Get Final Winner
		winner = getFinalWinner(yScore, cScore, tScore);
		
		// Display scores on screen
		tvFinalWinner.setText(winner);
		yourScore.setText("You: " + yScore);
		compScore.setText("Computer: " + cScore);
		tieScore.setText("Tie: " + tScore);
	}
	
	
	private String getFinalWinner(int you, int computer, int tie) {
		String finalWinner = "";
		if (you >= computer) {
			if (you >= tie) {
				finalWinner = "You win!";
			} else {
				finalWinner = "It's a tie!";
			}
		} else {
			if (computer >= tie) {
				finalWinner = "The computer wins!";
			} else {
				finalWinner = "It's a tie!";
			}
		}
		return finalWinner;		
	}

	private void initialize() {
		tvFinalWinner = (TextView) findViewById(R.id.tvFinalWinner);
		yourScore = (TextView) findViewById(R.id.tvYourScore);
		compScore = (TextView) findViewById(R.id.tvCompScore);
		tieScore = (TextView) findViewById(R.id.tvTieScore);
		playAgain = (Button) findViewById(R.id.bPlayAgain);
		playAgain.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, RockPaperScissors.class));
	}
}
