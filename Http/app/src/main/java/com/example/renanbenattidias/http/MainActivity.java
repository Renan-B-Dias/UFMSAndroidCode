package com.example.renanbenattidias.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView avatarImageView;
    Button changeImageButton;
//    Bitmap imageBitmap;
    ProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpClick();
        applyLayout();
    }

    private void setUpViews() {
        avatarImageView = findViewById(R.id.avatarImageView);
        changeImageButton = findViewById(R.id.changeImageButton);
        circleProgressBar = findViewById(R.id.progressBar);
    }

    private void setUpClick() {
        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                downloadImage();
                new ImageDownloader().execute();
            }
        });
    }

    private void applyLayout() {
        circleProgressBar.setVisibility(View.INVISIBLE);
        changeImageButton.setText(R.string.changeImageButton);
    }

    private class ImageDownloader extends AsyncTask<Void, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            circleProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            String urlString = "https://api.adorable.io/avatars/285/renanbenattidias";
            String randomString = urlString.concat(String.valueOf(new Random().nextInt()));
            String pngString = randomString.concat(".png");
            try {
                URL url = new URL(pngString);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
                httpConnection.connect();
                InputStream stream = httpConnection.getInputStream();

                Bitmap imageBitmap = BitmapFactory.decodeStream(stream);

                httpConnection.disconnect();

                return imageBitmap;
            } catch(MalformedURLException error) {
                Log.e("MalformedURLException", "Error on url");
            } catch(IOException error) {
                Log.e("IOException", "Error on connection");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if(result != null) {
                circleProgressBar.setVisibility(View.INVISIBLE);
                avatarImageView.setImageBitmap(result);
            }
        }
    }

//    private void downloadImage() {
////        https://api.adorable.io/avatars/285/renanbenattidias.png
//        circleProgressBar.setVisibility(View.VISIBLE);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String urlString = "https://api.adorable.io/avatars/285/renanbenattidias";
//                String randomString = urlString.concat(String.valueOf(new Random().nextInt()));
//                String pngString = randomString.concat(".png");
//                try {
//                    URL url = new URL(pngString);
//                    URLConnection urlConnection = url.openConnection();
//                    HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
//                    httpConnection.connect();
//                    InputStream stream = httpConnection.getInputStream();
//
//                    imageBitmap = BitmapFactory.decodeStream(stream);
//
//                    httpConnection.disconnect();
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            circleProgressBar.setVisibility(View.INVISIBLE);
//                            avatarImageView.setImageBitmap(imageBitmap);
//                        }
//                    });
//                } catch(MalformedURLException error) {
//                    Log.e("MalformedURLException", "Error on url");
//                } catch(IOException error) {
//                    Log.e("IOException", "Error on connection");
//                }
//            }
//        }).start();
//    }
}
