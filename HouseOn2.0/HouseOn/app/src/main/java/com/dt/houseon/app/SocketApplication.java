package com.dt.houseon.app;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketApplication extends Application {
    String server = "http://192.168.43.51:3000";

    private Socket appSocket;{
        try{
            appSocket = IO.socket(this.server);
        } catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    public void setServer(String in){
        StringBuilder serverBuilder = new StringBuilder();
        serverBuilder.append("http://");
        serverBuilder.append(in);
        serverBuilder.append(":3000");
        this.server = serverBuilder.toString();

        try {
            appSocket = IO.socket(this.server);
        } catch(URISyntaxException e){
            throw new RuntimeException(e);
        }

    }

    public String getServer(){
        return this.server;
    }
    public Socket getSocket(){ return this.appSocket; }
}
