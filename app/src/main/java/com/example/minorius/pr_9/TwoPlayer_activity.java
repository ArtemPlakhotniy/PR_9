package com.example.minorius.pr_9;

import android.app.FragmentTransaction;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.pr_9.Fragment.Internet_player;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TwoPlayer_activity extends AppCompatActivity implements Communicator {

    private TextView fade_txt;
    private TextView wait_txt;
    private TextView answer_for_inet_game;

    static final int serverPort = 1701;
    static final String address = "192.168.3.12";
    static Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Integer timer = 60;
    int flag_start = 202;
    int flag_finish = 303;

    private Internet_player internet_player;
    public static GS gs;

    private Firebase fb;
    public static final String PATH = "https://minorius.firebaseio.com";
    private String CATEGORY_I;
    private ArrayList<Integer> data_from_firebase;

    static int[][] list_from_server;
    static int question_in_time = 0;
    static Connecting c;


    Intent intent;
    public static String l ="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        fade_txt = (TextView) findViewById(R.id.fade_txt);
        wait_txt = (TextView) findViewById(R.id.wait_txt);
        answer_for_inet_game = (TextView) findViewById(R.id.answer_for_inet_game);

        wait_txt.setVisibility(View.VISIBLE);

        Firebase.setAndroidContext(getApplicationContext());
        fb = new Firebase(PATH);

        data_from_firebase = new ArrayList<>();
        data_from_firebase.add(flag_start);

        intent = new Intent(this, ResultsFromServer.class);


        list_from_server = new int[60][2];

        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    data_from_firebase.add(((int) ds.getChildrenCount()));

                }

                c = new Connecting();
                c.execute(data_from_firebase);

                try {
                    if(c.get().contains(505)){
                        c.get().remove(120);

                        int input_counter = 0;
                        for (int i = 0; i < 60; i++) {
                            list_from_server[i][0] = c.get().get(input_counter);
                            list_from_server[i][1] = c.get().get(input_counter + 1);
                            input_counter = input_counter + 2;
                        }
                    }
                    c.get().clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                int temp_c = list_from_server[question_in_time][0];
                int temp_q = list_from_server[question_in_time][1];

                question_in_time++;

                switch (temp_c) {
                    case 0:
                        CATEGORY_I = "/Lit/";
                        break;
                    case 1:
                        CATEGORY_I = "/astr/";
                        break;
                    case 2:
                        CATEGORY_I = "/chem/";
                        break;
                    case 3:
                        CATEGORY_I = "/geo/";
                        break;
                    case 4:
                        CATEGORY_I = "/math/";
                        break;
                    case 5:
                        CATEGORY_I = "/phis/";
                        break;
                }

                internet_player = new Internet_player();

                Firebase.setAndroidContext(getApplicationContext());
                fb = new Firebase(PATH + CATEGORY_I + temp_q);

                fb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        gs = new GS();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            switch (ds.getKey()) {
                                case "a":
                                    gs.setA(ds.getValue().toString());
                                    break;
                                case "b":
                                    gs.setB(ds.getValue().toString());
                                    break;
                                case "c":
                                    gs.setC(ds.getValue().toString());
                                    break;
                                case "d":
                                    gs.setD(ds.getValue().toString());
                                    break;
                                case "answer":
                                    gs.setAnswer(ds.getValue().toString());
                                    break;
                                case "question":
                                    gs.setQuestion(ds.getValue().toString());
                                    break;
                            }
                        }

                        wait_txt.setVisibility(View.GONE);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.add(R.id.buffer_fragment_i, internet_player).commit();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    @Override
    public void fragmentCallBack(String a) {



        internet_player = new Internet_player();

        fade_txt.setText(gs.getAnswer());



        if(a.equals(gs.getAnswer())){
            fade_txt.setTextColor(getResources().getColor(R.color.green));
            startService(new Intent(getApplicationContext(), Controller_ans.class).putExtra("ans", 1));


        }else{
            fade_txt.setTextColor(getResources().getColor(R.color.red));
            startService(new Intent(getApplicationContext(), Controller_ans.class).putExtra("ans", 0));

        }

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_txt);
        fade_txt.startAnimation(anim);

        int temp_c = list_from_server[question_in_time][0];
        int temp_q = list_from_server[question_in_time][1];

        question_in_time++;

        switch (temp_c) {
            case 0:
                CATEGORY_I = "/Lit/";
                break;
            case 1:
                CATEGORY_I = "/astr/";
                break;
            case 2:
                CATEGORY_I = "/chem/";
                break;
            case 3:
                CATEGORY_I = "/geo/";
                break;
            case 4:
                CATEGORY_I = "/math/";
                break;
            case 5:
                CATEGORY_I = "/phis/";
                break;
        }

        Firebase.setAndroidContext(getApplicationContext());
        fb = new Firebase(PATH + CATEGORY_I + temp_q);

        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gs = new GS();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    switch (ds.getKey()) {
                        case "a":
                            gs.setA(ds.getValue().toString());
                            break;
                        case "b":
                            gs.setB(ds.getValue().toString());
                            break;
                        case "c":
                            gs.setC(ds.getValue().toString());
                            break;
                        case "d":
                            gs.setD(ds.getValue().toString());
                            break;
                        case "answer":
                            gs.setAnswer(ds.getValue().toString());
                            break;
                        case "question":
                            gs.setQuestion(ds.getValue().toString());
                            break;
                    }
                }

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.buffer_fragment_i, internet_player).commit();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }

    class Connecting extends AsyncTask<ArrayList<Integer>, Void, ArrayList<Integer>>{

        @Override
        protected ArrayList doInBackground(ArrayList... params) {

            ArrayList<Integer> temp;
            temp = new ArrayList<>();

            try {
                InetAddress ipAddress = InetAddress.getByName(address);
                socket = new Socket(ipAddress, serverPort);

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                for (int i = 0; i < data_from_firebase.size(); i++) {
                    out.writeInt(data_from_firebase.get(i));
                }

                out.writeInt(flag_finish);
                out.flush();

                do{
                    temp.add(in.readInt());
                }
                while (!temp.contains(505));

            } catch (Exception x) {
                x.printStackTrace();
            }

            return temp;
        }

        @Override
        protected void onPostExecute(ArrayList s) {
            super.onPostExecute(s);
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(getApplicationContext(), Controller_ans.class));
        question_in_time = 0;

        if(question_in_time > 0){

            try{
                out = new DataOutputStream(socket.getOutputStream());
                out.writeInt(888);
                out.flush();
                socket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        stopService(new Intent(getApplicationContext(), Controller_ans.class));
        question_in_time = 0;

        if(question_in_time > 0){

            try{
                out = new DataOutputStream(socket.getOutputStream());
                out.writeInt(888);
                out.flush();
                socket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

 //       LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    protected void onPause() {

        stopService(new Intent(getApplicationContext(), Controller_ans.class));
        question_in_time = 0;

        if(question_in_time > 0){

            try{
                out = new DataOutputStream(socket.getOutputStream());
                out.writeInt(888);
                out.flush();
                socket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        unregisterReceiver(broadcastReceiver);
        stopService(intent);
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        stopService(new Intent(getApplicationContext(), Controller_ans.class));
        question_in_time = 0;

        if(question_in_time > 0){

            try{
                out = new DataOutputStream(socket.getOutputStream());
                out.writeInt(888);
                out.flush();
                socket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onBackPressed();

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        registerReceiver(broadcastReceiver, new IntentFilter(ResultsFromServer.BROADCAST_ACTION));
    }


    private void updateUI(Intent intent) {

        String time = intent.getStringExtra("answer");
        answer_for_inet_game = (TextView) findViewById(R.id.answer_for_inet_game);
        answer_for_inet_game.setText(time);
    }


}