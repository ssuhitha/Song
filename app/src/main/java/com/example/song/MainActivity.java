package com.example.song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView playButton, likeButton, shareIcon;
    private boolean isPlaying = false;
    private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        playButton = findViewById(R.id.play_button);
        likeButton = findViewById(R.id.like_button);
        shareIcon = findViewById(R.id.share_icon);
        SeekBar seekBar = findViewById(R.id.song_seekbar);

        // Set up click listeners
        setPlayButtonListener();
        setLikeButtonListener();
        setShareButtonListener();
    }

    private void setPlayButtonListener() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Switch back to the play icon
                    playButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.play));
                    Toast.makeText(MainActivity.this, "Paused", Toast.LENGTH_SHORT).show();
                } else {
                    // Change to the pause icon
                    playButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pause));
                    Toast.makeText(MainActivity.this, "Playing", Toast.LENGTH_SHORT).show();
                }
                isPlaying = !isPlaying; // Toggle state
            }
        });
    }

    private void setLikeButtonListener() {
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    likeButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.fav));
                    Toast.makeText(MainActivity.this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    likeButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.hp));
                    Toast.makeText(MainActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                }
                isLiked = !isLiked; // Toggle state
            }
        });
    }

    private void setShareButtonListener() {
        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sharing...", Toast.LENGTH_SHORT).show();
                // Intent to share the song details
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this song: We don't Talk anymore by Charlie Puth!");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
}
