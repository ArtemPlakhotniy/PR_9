package com.example.minorius.pr_9;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.pr_9.Fragment.Single_player;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class OnePlayer_activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, Communicator{

    static public GS gs;
    private Single_player sp;
    private Firebase fb;
    private Category_activity ca;

    public static String PATH = "https://minorius.firebaseio.com";
    public static String CATEGORY;

    private TextView txt_true;
    private TextView txt_false;
    private int true_answer  = 0;
    private int false_answer = 0;

    private TextView txt_loading;
    private TextView txt_timer;
    public long count;
    public int num;
    public Integer timer = 60;

    private Map<String, Long> number_of_question;

    Bundle b;
    Loader<String> ls;
    public static final int LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);

        txt_true = (TextView) findViewById(R.id.txt_true);
        txt_false = (TextView) findViewById(R.id.txt_false);
        txt_loading = (TextView) findViewById(R.id.txt_loading);
        txt_timer = (TextView) findViewById(R.id.txt_timer);

        Random r = new Random();
        int random_subjects = r.nextInt((ca.al_to_next.size()));

        switch (""+ca.al_to_next.get(random_subjects).getText()){
            case "Математика":
                CATEGORY = "/math/";
                break;
            case "Хімія":
                CATEGORY = "/chem/";
                break;
            case "Географія":
                CATEGORY = "/geo/";
                break;
            case "Література":
                CATEGORY = "/Lit/";
                break;
            case "Фізика":
                CATEGORY = "/phis/";
                break;
            case "Астрономія":
                CATEGORY = "/astr/";
                break;
        }

        number_of_question = new LinkedHashMap<>();


        Firebase.setAndroidContext(getApplicationContext());
        fb = new Firebase(PATH);

        fb.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                //Добавляем колличество вопросов с бд
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    number_of_question.put(ds.getKey(), ds.getChildrenCount());
                }

                //Проверяем какая категория выбрана
                for (String key: number_of_question.keySet()){
                    if(("/" + key +"/").equals(CATEGORY)){
                        count = number_of_question.get(key);
                    }
                }

                num = (int) count;

                Random r = new Random();
                sp = new Single_player();

                Firebase.setAndroidContext(getApplicationContext());
                fb = new Firebase(PATH + CATEGORY + r.nextInt(num));

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

                        txt_loading.setVisibility(View.GONE);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.add(R.id.buffer_fragment, sp).commit();

                        new CountDownTimer(60000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                timer--;
                                txt_timer.setText("" + timer);
                            }

                            @Override
                            public void onFinish() {
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(sp).commit();

                                txt_timer.setVisibility(View.INVISIBLE);
                                txt_loading.setText("Ваш результат " + true_answer);
                                txt_loading.setVisibility(View.VISIBLE);

                            }
                        }.start();
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
        b = new Bundle();
        ls = getSupportLoaderManager().initLoader(LOADER_ID, b, this);


    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        ls = new Loader_fb(this, args);
        return ls;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void fragmentCallBack(String a) {

        Random r = new Random();
        sp = new Single_player();

        if(a.equals(gs.getAnswer())){
            true_answer++;
            txt_true.setText(""+true_answer);
        }else{
            false_answer++;
            txt_false.setText(""+false_answer);
        }

        Random random = new Random();
        int random_subjects = random.nextInt((ca.al_to_next.size()));

        switch (""+ca.al_to_next.get(random_subjects).getText()){
            case "Математика":
                CATEGORY = "/math/";
                break;
            case "Хімія":
                CATEGORY = "/chem/";
                break;
            case "Географія":
                CATEGORY = "/geo/";
                break;
            case "Література":
                CATEGORY = "/Lit/";
                break;
            case "Фізика":
                CATEGORY = "/phis/";
                break;
            case "Астрономія":
                CATEGORY = "/astr/";
                break;
        }

        for (String key: number_of_question.keySet()){
            if(("/" + key +"/").equals(CATEGORY)){
                count = number_of_question.get(key);
            }
        }

        num = (int) count;

        fb = new Firebase(PATH+CATEGORY+r.nextInt(num));
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
                ft.replace(R.id.buffer_fragment, sp).commit();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

}



