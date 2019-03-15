package com.dt.houseon;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.dt.houseon.app.SocketApplication;

import io.socket.client.Socket;

public class LedRGB extends AppCompatActivity {

    private SocketApplication app;
    float r, g ,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_rgb);
        String fromcolor = "";
        Intent from = getIntent();
        if(from.hasExtra("color")){
            fromcolor = from.getStringExtra("color");
        }


        if (!fromcolor.isEmpty()) {

            int[][] states = new int[][]{
                    new int[]{android.R.attr.state_pressed},
                    new int[]{android.R.attr.state_enabled}
            };
            int[] colors = new int[]{
                    Color.parseColor(from.getStringExtra("color")),
                    Color.parseColor(from.getStringExtra("color"))
            };

            String r = "";
            String g = "";
            String b = "";

            for (char c : fromcolor.toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    if (r.length() < 2) {
                        r = r + c;
                    } else if (g.length() < 2) {
                        g = g + c;
                    } else if (b.length() < 2) {
                        b = b + c;
                    }
                }
            }
            Log.d("r ", r);
            Log.d("g ", g);
            Log.d("b ", b);
            int red = (int) (Long.parseLong(r, 16) / 2.55);
            int green = (int) (Long.parseLong(g, 16) / 2.55);
            int blue = (int) (Long.parseLong(b, 16) / 2.55);

            //Log.d("R VAlue", String.valueOf(red));
            //Integer.decode(g);
            //Integer.decode(b);


            final ColorStateList colorStateList = new ColorStateList(states, colors);

            ImageView RGBColor = findViewById(R.id.imageViewColor);
            RGBColor.setImageTintList(colorStateList);

            app = (SocketApplication) getApplication();

            SeekBar seekBarRed = findViewById(R.id.seekBarR);
            seekBarRed.setOnSeekBarChangeListener(onSeekBarRedChangeListener);
            seekBarRed.setProgress(red);

            SeekBar seekBarGreen = findViewById(R.id.seekBarG);
            seekBarGreen.setOnSeekBarChangeListener(onSeekBarGreenChangeListener);
            seekBarGreen.setProgress(green);

            SeekBar seekBarBlue = findViewById(R.id.seekBarB);
            seekBarBlue.setOnSeekBarChangeListener(onSeekBarBlueChangeListener);
            seekBarBlue.setProgress(blue);

            SeekBar seekBarIntensity = findViewById(R.id.seekBarIntensity);
            seekBarIntensity.setOnSeekBarChangeListener(onSeekBarIntensityChangeListener);
            seekBarIntensity.setProgress(50);
        }
    }

    SeekBar.OnSeekBarChangeListener onSeekBarIntensityChangeListener = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser){
            app.getSocket().emit("onIntensityChanged", progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };

    SeekBar.OnSeekBarChangeListener onSeekBarRedChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float value = progress * 2.55f;
            Log.d("progress", String.valueOf(value));
            r = value;
            onColorChanged();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener onSeekBarGreenChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float value = progress * 2.55f;
            Log.d("progress", String.valueOf(value));
            g = value;
            onColorChanged();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener onSeekBarBlueChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float value = progress * 2.55f;
            Log.d("progress", String.valueOf(value));
            b = value;
            onColorChanged();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    void onColorChanged(){

        Socket appSocket = app.getSocket();

        String rints = Integer.toHexString(Math.round(this.r));
        if(rints.length() == 1){
            rints = "0"+rints;
        }

        Log.d("color red", rints);


        String gints = Integer.toHexString(Math.round(this.g));

        if(gints.length() == 1){
            gints = "0"+gints;
        }
        Log.d("color green", gints);

        String bints = Integer.toHexString(Math.round(this.b));

        if(bints.length() == 1){
            bints = "0"+bints;
        }
        Log.d("color blue", bints);

        final String hexcolorints = rints + gints + bints;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_pressed },
                        new int[] { android.R.attr.state_enabled }
                };
                int[] colors = new int[] {
                        Color.parseColor("#"+hexcolorints),
                        Color.parseColor("#"+hexcolorints)
                };

                final ColorStateList colorStateList = new ColorStateList(states, colors);

                ImageView RGBColor = findViewById(R.id.imageViewColor);
                RGBColor.setImageTintList(colorStateList);
            }
        });
        if(appSocket.connected()) {
            appSocket.emit("onColorChanged", hexcolorints);
        }
        Log.d("color ints", hexcolorints);
    }
}
