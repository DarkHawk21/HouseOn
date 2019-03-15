package com.dt.houseon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.dt.houseon.app.SocketApplication;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LedSettings extends AppCompatActivity {
    String habitacion;
    SocketApplication app;
    Socket appSocket;
    SeekBar seekBarBrightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_settings);
        app  = (SocketApplication) getApplication();
        appSocket = app.getSocket();;
        Intent from = getIntent();
        habitacion = from.getStringExtra("hab");

        if (habitacion.equals("Baño")) {
            appSocket.emit("getBrightness", 1);
        } else if (habitacion.equals("Recamara 1")) {
            appSocket.emit("getBrightness", 2);
        } else if (habitacion.equals("Recamara 2")) {
            appSocket.emit("getBrightness", 3);
        }

        appSocket.on("getBrightnessResponse", onGetBrightnessResponse);
        this.setTitle(habitacion);

        seekBarBrightness = findViewById(R.id.seekBarBrightness);
        seekBarBrightness.setOnSeekBarChangeListener(onSeekBarBrightnessChanged);


    }

    Emitter.Listener onGetBrightnessResponse = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            final JSONObject in = (JSONObject) args[0];

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        seekBarBrightness.setProgress((int)(in.getInt("brightness")/2.55f));
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            });

        }
    };

    SeekBar.OnSeekBarChangeListener onSeekBarBrightnessChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
            if(fromUser){
                int value = (int)(progress * 2.55f);

                if(appSocket.connected()) {
                    JSONObject out = new JSONObject();
                    try {
                        out.put("brightness", value);
                        if (habitacion.equals("Baño")) {
                            out.put("hab", 1);
                            appSocket.emit("onBrightnessChanged", out);
                        } else if (habitacion.equals("Recamara 1")) {
                            out.put("hab", 2);
                            appSocket.emit("onBrightnessChanged", out);
                        } else if (habitacion.equals("Recamara 2")) {
                            out.put("hab", 3);
                            appSocket.emit("onBrightnessChanged", out);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}

