package com.hargenrader.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayChoice extends Activity implements OnClickListener {
	TextView roundNum, winner;
	ImageView youChose, compChose;
	Button nextRound;
	int playerChoice, compChoice;
	String winnerMessage;
	int roundCount, yourScore, compScore, tieScore;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.round);
		initialize();

		// Initialize Bundle
		Bundle results = getIntent().getExtras();

		// Get the choices from the results Bundle
		playerChoice = results.getInt("pChoice");
		compChoice = results.getInt("cChoice");
		winnerMessage = results.getString("winner");
		roundCount = results.getInt("rCount");
		yourScore = results.getInt("pScore");
		compScore = results.getInt("cScore");
		tieScore = results.getInt("tScore");

		// Display choices on screen
		roundNum.setText("Round " + roundCount);
		youChose.setImageResource(playerChoice);
		compChose.setImageResource(compChoice);
		winner.setText(winnerMessage);
		if (roundCount == 5) {
			nextRound.setText("See Final Results");
		}
	}

	private void initialize() {
		roundNum = (TextView) findViewById(R.id.tvRoundNum);
		youChose = (ImageView) findViewById(R.id.ivYouChose);
		compChose = (ImageView) findViewById(R.id.ivCompChose);
		winner = (TextView) findViewById(R.id.tvWinner);
		nextRound = (Button) findViewById(R.id.bNextRound);
		nextRound.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (roundCount == 5) {
			Bundle totalResults = new Bundle();
			totalResults.putInt("pScore", yourScore);
			totalResults.putInt("cScore", compScore);
			totalResults.putInt("tScore", tieScore);
			Intent intent2 = new Intent(DisplayChoice.this, FinalResults.class);
			intent2.putExtras(totalResults);
			startActivity(intent2);
		} else {
			finish();
		}
	}
}
