package com.example.minorius.pr_9;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

import com.example.minorius.pr_9.Fragment.Internet_player;
import com.firebase.client.Firebase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ResultsFromServer extends Service {

    private DataOutputStream out;
    private DataInputStream in;

    public static final String BROADCAST_ACTION = "com.websmithing.broadcasttest.displayevent";
    private final Handler handler = new Handler();
    Intent intent;
    int counter = 0;



    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            handler.removeCallbacks(sendUpdatesToUI);
            handler.postDelayed(sendUpdatesToUI, 100);


        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 100); // 10 seconds
        }
    };


    private void DisplayLoggingInfo() {


        intent.putExtra("answer", new Get().data());
        sendBroadcast(intent);

    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class Get{

        String k;

        private List<Integer> Results;

        public String data(){

            Results = new LinkedList<Integer>();

            try {

                in = new DataInputStream(TwoPlayer_activity.socket.getInputStream());
                out = new DataOutputStream(TwoPlayer_activity.socket.getOutputStream());

            if(in.readInt() == 707){
                for(int i = 0; i < 2; i++){
                    this.Results.add(in.read());
                }
            }

                k = ""+Results.get(1);


            } catch (Exception x) {
                x.printStackTrace();
            }


            return k;
        }
    }
}
