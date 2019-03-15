package com.dt.houseon;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.dt.houseon.app.SocketApplication;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.EngineIOException;

public class MainActivity extends AppCompatActivity {

    private Socket appSocket;
    private SocketApplication app;
    EditText editTextServerAddress;
    String color = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (SocketApplication) getApplication();
        appSocket = app.getSocket();
        //editTextServerAddress = findViewById(R.id.editTextServerAddress);

        appSocket.on(Socket.EVENT_CONNECT, onConnectListener);
        appSocket.on(Socket.EVENT_CONNECT_ERROR, onErrorListener);
        appSocket.on(Socket.EVENT_DISCONNECT, onDisconnectListener);
        appSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onErrorListener);
        appSocket.on("onConnection", onBoardConnectionListener);
        appSocket.on("toggledRGB", onToggledRGBListener);
        appSocket.on("toggledBaño", onToggledBañoListener);
        appSocket.on("toggledRecamara1", onToggledRecamara1Listener);
        appSocket.on("toggledRecamara2", onToggledRecamara2Listener);
        appSocket.on("newColor", onNewColorListener);
        appSocket.connect();

        /*Button btnStartSocket = findViewById(R.id.buttonStartSocket);
        btnStartSocket.setOnClickListener(onClickConnectListener);*/

        Button btnToggleRGB = findViewById(R.id.buttonToggleRGB);
        btnToggleRGB.setOnClickListener(onClickToggleRGBListener);

        Button btnChangeRGBColor = findViewById(R.id.buttonColorRGB);
        btnChangeRGBColor.setOnClickListener(onClickChangeRGBColor);

        Button btnToggleBaño = findViewById(R.id.buttonToggleBaño);
        btnToggleBaño.setOnClickListener(onClickToggleBañoListener);

        Button btnSettingsBaño = findViewById(R.id.buttonSettingsBaño);
        btnSettingsBaño.setOnClickListener(onClickSettingsBañoListener);

        Button btnToggleRecamara1 = findViewById(R.id.buttonToggleRecamara1);
        btnToggleRecamara1.setOnClickListener(onClickToggleRecamara1Listener);

        Button btnSettingsRecamara1 = findViewById(R.id.buttonSettingsRecamara1);
        btnSettingsRecamara1.setOnClickListener(onClickSettingsRecamara1Listener);

        Button btnToggleRecamara2 = findViewById(R.id.buttonToggleRecamara2);
        btnToggleRecamara2.setOnClickListener(onClickToggleRecamara2Listener);

        Button btnSettingsRecamara2 = findViewById(R.id.buttonSettingsRecamara2);
        btnSettingsRecamara2.setOnClickListener(onClickSettingsRecamara2Listener);

        Button btnToggleVentilacion = findViewById(R.id.buttonToggleVentilacion);
        btnToggleVentilacion.setOnClickListener(onClickToggleVentilacionListener);


    }

    View.OnClickListener onClickToggleVentilacionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(appSocket.connected()) {
                appSocket.emit("onToggleVentilacion");
            }
        }
    };

    View.OnClickListener onClickSettingsBañoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent SettingsActivity = new Intent(getApplicationContext(), LedSettings.class);
            SettingsActivity.putExtra("hab", "Baño");
            startActivity(SettingsActivity);
        }
    };

    View.OnClickListener onClickSettingsRecamara1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent SettingsActivity = new Intent(getApplicationContext(), LedSettings.class);
            SettingsActivity.putExtra("hab", "Recamara 1");
            startActivity(SettingsActivity);
        }
    };

    View.OnClickListener onClickSettingsRecamara2Listener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent SettingsActivity = new Intent(getApplicationContext(), LedSettings.class);
            SettingsActivity.putExtra("hab", "Recamara 2");
            startActivity(SettingsActivity);
        }
    };

    View.OnClickListener onClickToggleBañoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(appSocket.connected()) {
                appSocket.emit("onToggleBaño");
            }
        }
    };

    View.OnClickListener onClickToggleRecamara1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(appSocket.connected()) {
                appSocket.emit("onToggleRecamara1");
            }
        }
    };

    View.OnClickListener onClickToggleRecamara2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(appSocket.connected()) {
                appSocket.emit("onToggleRecamara2");
            }
        }
    };

    View.OnClickListener onClickChangeRGBColor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ledRGBActivity = new Intent(getApplicationContext(), LedRGB.class);
            if(!color.isEmpty()) {
                ledRGBActivity.putExtra("color", color);
            }
            startActivity(ledRGBActivity);
        }
    };

    View.OnClickListener onClickConnectListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            //app.setServer(editTextServerAddress.getText().toString().trim());
            //appSocket = app.getSocket();

            appSocket.on(Socket.EVENT_CONNECT, onConnectListener);
            appSocket.on(Socket.EVENT_CONNECT_ERROR, onErrorListener);
            appSocket.on(Socket.EVENT_DISCONNECT, onDisconnectListener);
            appSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onErrorListener);
            appSocket.on("onConnection", onBoardConnectionListener);
            appSocket.on("toggledRGB", onToggledRGBListener);
            appSocket.on("toggledBaño", onToggledBañoListener);
            appSocket.on("toggledRecamara1", onToggledRecamara1Listener);
            appSocket.on("toggledRecamara2", onToggledRecamara2Listener);
            appSocket.on("newColor", onNewColorListener);
            appSocket.connect();
        }
    };

    View.OnClickListener onClickToggleRGBListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(appSocket.connected()) {
                appSocket.emit("onToggleRGB");
            }
        }
    };

    Emitter.Listener onToggledRecamara2Listener = new Emitter.Listener() {
        @Override
        public void call(Object... args){
            JSONObject in = (JSONObject) args[0];
            try{
                boolean bisRecamaraOn = in.getBoolean("isRecamaraOn");
                if(bisRecamaraOn){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRecamara = findViewById(R.id.buttonToggleRecamara2);
                            btnRecamara.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLED)));
                            btnRecamara.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRecamara = findViewById(R.id.buttonToggleRecamara2);
                            btnRecamara.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRecamara.setText("off");
                        }
                    });
                }
            } catch(JSONException e){

            }
        }
    };

    Emitter.Listener onToggledRecamara1Listener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject in = (JSONObject) args[0];
            try{
                boolean bisRecamaraOn = in.getBoolean("isRecamaraOn");
                if(bisRecamaraOn){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRecamara = findViewById(R.id.buttonToggleRecamara1);
                            btnRecamara.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLED)));
                            btnRecamara.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRecamara = findViewById(R.id.buttonToggleRecamara1);
                            btnRecamara.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRecamara.setText("off");
                        }
                    });
                }
            } catch(JSONException e){

            }
        }
    };

    Emitter.Listener onToggledBañoListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject in = (JSONObject) args[0];
            try{
                boolean bisBañoOn = in.getBoolean("isBañoOn");
                if(bisBañoOn){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnBaño = findViewById(R.id.buttonToggleBaño);
                            btnBaño.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLED)));
                            btnBaño.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRGB = findViewById(R.id.buttonToggleBaño);
                            btnRGB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRGB.setText("off");
                        }
                    });
                }
            } catch(JSONException e){

            }
        }
    };

    Emitter.Listener onBoardConnectionListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject in = (JSONObject) args[0];
            try{
                boolean bisBañoOn = in.getBoolean("isBañoOn");
                if(bisBañoOn){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnBaño = findViewById(R.id.buttonToggleBaño);
                            btnBaño.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLED)));
                            btnBaño.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRGB = findViewById(R.id.buttonToggleBaño);
                            btnRGB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRGB.setText("off");
                        }
                    });
                }
                boolean bisRec1On = in.getBoolean("isRec1On");
                if(bisRec1On){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRec1 = findViewById(R.id.buttonToggleRecamara1);
                            btnRec1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLED)));
                            btnRec1.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRec1 = findViewById(R.id.buttonToggleRecamara1);
                            btnRec1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRec1.setText("off");
                        }
                    });
                }
                boolean bisRec2On = in.getBoolean("isRec2On");
                if(bisRec2On){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRec2 = findViewById(R.id.buttonToggleRecamara2);
                            btnRec2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLED)));
                            btnRec2.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRec2 = findViewById(R.id.buttonToggleRecamara2);
                            btnRec2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRec2.setText("off");
                        }
                    });
                }
                boolean bisRGBOn = in.getBoolean("isRGBOn");
                color = "#"+in.getString("color");
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_pressed },
                        new int[] { android.R.attr.state_enabled }
                };
                int[] colors = new int[] {
                        Color.parseColor(color),
                        Color.parseColor(color)
                };

                final ColorStateList colorStateList = new ColorStateList(states, colors);
                if(bisRGBOn){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRGB = findViewById(R.id.buttonToggleRGB);
                            btnRGB.setBackgroundTintList(colorStateList);
                        }
                    });
                }
            } catch(JSONException e){
                Log.d("isRGBOn Exception", e.toString());
            }
        }
    };

    Emitter.Listener onToggledRGBListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject in = (JSONObject) args[0];
            try{
                boolean bisRGBOn = in.getBoolean("isRGBOn");
                color = "#"+in.getString("color");
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_pressed },
                        new int[] { android.R.attr.state_enabled }
                };
                int[] colors = new int[] {
                        Color.parseColor(color),
                        Color.parseColor(color)
                };

                final ColorStateList colorStateList = new ColorStateList(states, colors);
                if(bisRGBOn){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRGB = findViewById(R.id.buttonToggleRGB);
                            btnRGB.setBackgroundTintList(colorStateList);
                            btnRGB.setText("");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button btnRGB = findViewById(R.id.buttonToggleRGB);
                            btnRGB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIntensity)));
                            btnRGB.setText("off");
                        }
                    });
                }
            } catch(JSONException e){

            }
        }
    };

    Emitter.Listener onNewColorListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject in = (JSONObject) args[0];
            try{

                color = "#"+in.getString("color");
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_pressed },
                        new int[] { android.R.attr.state_enabled }
                };
                int[] colors = new int[] {
                        Color.parseColor(color),
                        Color.parseColor(color)
                };

                final ColorStateList colorStateList = new ColorStateList(states, colors);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Button btnRGB = findViewById(R.id.buttonToggleRGB);
                        btnRGB.setBackgroundTintList(colorStateList);
                        btnRGB.setText("");
                    }
                });
            } catch (JSONException e){
                Log.d("JSONException", "call: me");
            }
        }
    };

    Emitter.Listener onErrorListener = new Emitter.Listener(){
        String error;
        @Override
        public void call(Object... args){
            appSocket.disconnect();
            EngineIOException inError = (EngineIOException) args[0];
            error = inError.toString();

            Log.d("EngineIOException", error + app.getServer());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    Emitter.Listener onConnectListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Conectado", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    Emitter.Listener onDisconnectListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Desconectado", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    @Override
    public void onDestroy(){
        if(appSocket.connected()) {
            appSocket.off(Socket.EVENT_CONNECT, onConnectListener);
            appSocket.off(Socket.EVENT_DISCONNECT, onDisconnectListener);
            appSocket.off(Socket.EVENT_CONNECT_ERROR, onErrorListener);
            appSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onErrorListener);
            appSocket.disconnect();
        }

        super.onDestroy();
    }
}


