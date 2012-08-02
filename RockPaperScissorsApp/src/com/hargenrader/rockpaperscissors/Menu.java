package com.hargenrader.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Menu extends Activity implements OnClickListener {
	ImageView playButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		playButton = (ImageView) findViewById(R.id.ivPlay);
		playButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		startActivity(new Intent(Menu.this, RockPaperScissors.class));
	}
}