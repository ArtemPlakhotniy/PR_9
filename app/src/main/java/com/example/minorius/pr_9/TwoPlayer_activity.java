package com.example.minorius.pr_9;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.pr_9.Fragment.Internet_player;
import com.example.minorius.pr_9.Fragment.Single_player;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

public class TwoPlayer_activity extends AppCompatActivity implements Communicator {

    private TextView fade_txt;

    static final int serverPort = 1701;
    static final String address = "134.249.176.164";
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    int flag_start = 202;
    int flag_finish = 303;

    private Internet_player internet_player;
    public static GS gs;

    private Firebase fb;
    public static final String PATH = "https://minorius.firebaseio.com";
    private String CATEGORY_I;
    private ArrayList<Integer> arrayList;

    static int[][] list_from_server;
    static int question_in_time = 0;
    static Connecting c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        fade_txt = (TextView) findViewById(R.id.fade_txt);

        Firebase.setAndroidContext(getApplicationContext());
        fb = new Firebase(PATH);

        arrayList = new ArrayList<>();
        arrayList.add(flag_start);

        list_from_server = new int[60][2];



        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    arrayList.add(((int) ds.getChildrenCount()));
                    Toast.makeText(getApplicationContext(), ""+arrayList, Toast.LENGTH_LONG).show();
                }

                c = new Connecting();
                c.execute(arrayList);


                try {

                    int input_counter = 0;
                    for(int i = 0; i<60; i++){
                        list_from_server[i][0]=c.get().get(input_counter);
                        list_from_server[i][1]=c.get().get(input_counter+1);
                        input_counter = input_counter + 2;
                    }



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
        }else{
            fade_txt.setTextColor(getResources().getColor(R.color.red));
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

                for (int i = 0; i < arrayList.size(); i++) {
                    out.writeInt(arrayList.get(i));
                }

                out.writeInt(flag_finish);
                out.flush();

                for(int i = 0; i<120; i++){
                    temp.add(in.readInt());
                }

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

        question_in_time = 0;

        try{
            out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(888);
            out.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    protected void onStop() {

        question_in_time = 0;

        try{
            out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(888);
            out.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    protected void onPause() {

        question_in_time = 0;

        try{
            out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(888);
            out.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        question_in_time = 0;

        if(socket.isConnected()){
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
        //Toast.makeText(getApplicationContext(), ""+arrayList, Toast.LENGTH_LONG).show();
    }
}
