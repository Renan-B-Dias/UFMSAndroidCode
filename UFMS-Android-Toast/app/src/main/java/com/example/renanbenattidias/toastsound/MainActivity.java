package com.example.renanbenattidias.toastsound;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        applyLayout();
        setOnClickListeners();
    }

    private void findViews() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
    }

    private void applyLayout() {
        button1.setText(R.string.short_toast);
        button2.setText(R.string.long_toast);
        button3.setText(R.string.sound);
    }

    private void setOnClickListeners() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShortToast(Toast.LENGTH_SHORT);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShortToast(Toast.LENGTH_LONG);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMedia();
            }
        });
    }

    MediaPlayer mediaPlayer;
    private void playMedia() {
        mediaPlayer = MediaPlayer.create(this, R.raw.digitalbell);
        mediaPlayer.start();
    }

    private void showShortToast(int toastLenght) {
        if(toastLenght <= 1 && toastLenght >= 0) {
            String message = "";
            if (toastLenght == 0)
                message = "This is short a toast";
            else
                message = "This is a long toast";
            Toast.makeText(this, message, toastLenght).show();
        }
    }
}
