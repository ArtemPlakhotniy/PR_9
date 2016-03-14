package com.example.minorius.pr_9;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    static public int serverPort = 1701;
    static public String address = "134.249.176.168";
    Socket socket;
    Internet_player internet_player;
    static public GS gs;

    OutputStream sout;
    InputStream sin;
    DataOutputStream out;
    DataInputStream in;

    Firebase fb;
    public static String PATH = "https://minorius.firebaseio.com";
    static ArrayList<Integer> arrayList;
    static ArrayList<Integer> arrayList_temp;
    int flag_start = 202;
    int flag_finish = 303;
    public static String CATEGORY_I;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        final Connecting c = new Connecting();
        c.execute("start");

//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.buffer_fragment_i, single_player).commit();


        Firebase.setAndroidContext(getApplicationContext());
        fb = new Firebase(PATH);

        arrayList = new ArrayList<>();
        arrayList.add(flag_start);

        arrayList_temp = new ArrayList<>();

        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    arrayList.add(((int) ds.getChildrenCount()));
                }

                try {
                    sout = socket.getOutputStream();
                    out = new DataOutputStream(sout);


                    for (int i = 0; i < arrayList.size(); i++) {
                        out.writeInt(arrayList.get(i));
                    }

                    out.writeInt(flag_finish);
                    out.flush();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    int temp_c = c.get().get(0);
                    int temp_q = c.get().get(1);

                    Toast.makeText(getApplicationContext(), ""+temp_c+" "+temp_q, Toast.LENGTH_LONG).show();

                    switch (temp_c){
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

                    Toast.makeText(getApplicationContext(), ""+CATEGORY_I, Toast.LENGTH_LONG).show();

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


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public void fragmentCallBack(String a) {

        internet_player = new Internet_player();
        if(a.equals(gs.getAnswer())){
            try {
                sout = socket.getOutputStream();
                out = new DataOutputStream(sout);
                out.writeInt(1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            try {
                sout = socket.getOutputStream();
                out = new DataOutputStream(sout);
                out.writeInt(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Connecting extends AsyncTask<String, Void, ArrayList<Integer>>{

        @Override
        protected ArrayList doInBackground(String... params) {

            ArrayList<Integer> temp;
            temp = new ArrayList<>();

            try {
                InetAddress ipAddress = InetAddress.getByName(address);
                socket = new Socket(ipAddress, serverPort);

                sin = socket.getInputStream();
                in = new DataInputStream(sin);

                for(int i = 0; i<2; i++){
                    temp.add(in.readInt());
                }

            } catch (Exception x) {
                x.printStackTrace();
            }

            return temp;
        }

        @Override
        protected void onPostExecute(ArrayList s) {
            arrayList_temp = s;
            super.onPostExecute(s);

        }
    }

    @Override
    public void onBackPressed() {

        try{
            sout = socket.getOutputStream();
            out = new DataOutputStream(sout);
            out.writeInt(888);
            out.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onBackPressed();
        if (socket.isClosed()){
           // Toast.makeText(getApplicationContext(), "Socket closed", Toast.LENGTH_LONG).show();
        }
    }
}
