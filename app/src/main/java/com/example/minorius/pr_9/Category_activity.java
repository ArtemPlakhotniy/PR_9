package com.example.minorius.pr_9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Minorius on 20.02.2016.
 */
public class Category_activity extends AppCompatActivity {

    private CheckBox check_math;
    private CheckBox check_geo;
    private CheckBox check_lit;
    private CheckBox check_phis;
    private CheckBox check_astr;
    private CheckBox check_chem;

    private TextView txt_math;
    private TextView txt_geo;
    private TextView txt_lit;
    private TextView txt_phis;
    private TextView txt_astr;
    private TextView txt_chem;

    public static ViewGroup up_layout_for_pict_2;
    public static LinearLayout footer_2;
    private LinearLayout up_layout_for_pict_down_2;
    private Button btn_play_2;



    public static ArrayList<CheckBox> test;
    public static ArrayList<TextView> test2;
    public static ArrayList<TextView> al;
    public static ArrayList<TextView> al_to_next;

    private ViewGroup frame_for_chkBox;
    private Subjects subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        check_math = (CheckBox) findViewById(R.id.check_math);
        check_geo = (CheckBox) findViewById(R.id.check_geo);
        check_lit = (CheckBox) findViewById(R.id.check_lit);
        check_phis = (CheckBox) findViewById(R.id.check_phis);
        check_astr = (CheckBox) findViewById(R.id.check_astr);
        check_chem = (CheckBox) findViewById(R.id.check_chem);

        txt_math = (TextView) findViewById(R.id.txt_math);
        txt_geo = (TextView) findViewById(R.id.txt_geo);
        txt_lit = (TextView) findViewById(R.id.txt_lit);
        txt_phis = (TextView) findViewById(R.id.txt_phis);
        txt_astr = (TextView) findViewById(R.id.txt_astr);
        txt_chem = (TextView) findViewById(R.id.txt_chem);

        footer_2 = (LinearLayout) findViewById(R.id.footer_2);
        frame_for_chkBox = (LinearLayout) findViewById(R.id.frame_for_chkBox);

        up_layout_for_pict_2 = (FrameLayout) findViewById(R.id.up_layout_for_pict_2);

        btn_play_2 = (Button) findViewById(R.id.btn_play_2);

//        DisplayMetrics dm = up_layout_for_pict_2.getResources().getDisplayMetrics();
//        int w = dm.widthPixels;
//        up_layout_for_pict_down_2.setScaleX(w);

        al = new ArrayList<>();

        test = new ArrayList<>();
        for (int i = 0; i<frame_for_chkBox.getChildCount(); i++){
            test.add((CheckBox) frame_for_chkBox.getChildAt(i));
        }

        test2 = new ArrayList<>();
        for (int i = 0; i < up_layout_for_pict_2.getChildCount(); i++){
            test2.add((TextView) up_layout_for_pict_2.getChildAt(i));
        }

        check_math.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjects = new Subjects();

                subjects.getCheck(0, Category_activity.this);


                Random r = new Random();
                int random_size = Integer.valueOf(r.nextInt(30) + 10);
                txt_math.setTextSize(random_size);

                DisplayMetrics d = up_layout_for_pict_2.getResources().getDisplayMetrics();
                int w = d.widthPixels;

                txt_math.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int width_txt = findViewById(R.id.txt_math).getMeasuredWidth();

                int random_w = Integer.valueOf(r.nextInt(w - width_txt));

                txt_math.setX(random_w);

                txt_math.setY(Integer.valueOf(r.nextInt(200)));



            }
        });
        check_geo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjects = new Subjects();

                subjects.getCheck(1, Category_activity.this);

                Random r = new Random();
                int random_size = Integer.valueOf(r.nextInt(30) + 10);
                txt_geo.setTextSize(random_size);

                DisplayMetrics d = up_layout_for_pict_2.getResources().getDisplayMetrics();
                int w = d.widthPixels;

                txt_geo.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int width_txt =  findViewById(R.id.txt_geo).getMeasuredWidth();

                int random_w = Integer.valueOf(r.nextInt(w-width_txt));

                txt_geo.setX(random_w);

                txt_geo.setY(Integer.valueOf(r.nextInt(200)));

            }
        });
        check_lit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjects = new Subjects();

                subjects.getCheck(2, Category_activity.this);

                Random r = new Random();
                int random_size = Integer.valueOf(r.nextInt(30) + 10);
                txt_lit.setTextSize(random_size);

                DisplayMetrics d = up_layout_for_pict_2.getResources().getDisplayMetrics();
                int w = d.widthPixels;

                txt_lit.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int width_txt =  findViewById(R.id.txt_lit).getMeasuredWidth();

                int random_w = Integer.valueOf(r.nextInt(w-width_txt));

                txt_lit.setX(random_w);

                txt_lit.setY(Integer.valueOf(r.nextInt(200)));

            }
        });
        check_phis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjects = new Subjects();

                subjects.getCheck(3, Category_activity.this);

                Random r = new Random();

                int random_size = Integer.valueOf(r.nextInt(30) + 10);
                txt_phis.setTextSize(random_size);

                DisplayMetrics d = up_layout_for_pict_2.getResources().getDisplayMetrics();
                int w = d.widthPixels;

                txt_phis.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int width_txt =  findViewById(R.id.txt_phis).getMeasuredWidth();

                int random_w = Integer.valueOf(r.nextInt(w-width_txt));

                txt_phis.setX(random_w);

                txt_phis.setY(Integer.valueOf(r.nextInt(200)));

            }
        });
        check_astr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjects = new Subjects();
                subjects.getCheck(4, Category_activity.this);

                Random r = new Random();

                int random_size = Integer.valueOf(r.nextInt(30) + 10);
                txt_astr.setTextSize(random_size);

                DisplayMetrics d = up_layout_for_pict_2.getResources().getDisplayMetrics();
                int w = d.widthPixels;

                txt_astr.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int width_txt =  findViewById(R.id.txt_astr).getMeasuredWidth();

                int random_w = Integer.valueOf(r.nextInt(w-width_txt));

                txt_astr.setX(random_w);

                txt_astr.setY(Integer.valueOf(r.nextInt(200)));

            }
        });
        check_chem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjects = new Subjects();
                subjects.getCheck(5, Category_activity.this);

                Random r = new Random();
                int random_size = Integer.valueOf(r.nextInt(30) + 10);
                txt_chem.setTextSize(random_size);

                DisplayMetrics d = up_layout_for_pict_2.getResources().getDisplayMetrics();
                int w = d.widthPixels;

                txt_chem.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int width_txt =  findViewById(R.id.txt_chem).getMeasuredWidth();

                int random_w = Integer.valueOf(r.nextInt(w-width_txt));

                txt_chem.setX(random_w);
                txt_chem.setY(Integer.valueOf(r.nextInt(200)));

            }
        });



        btn_play_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                al_to_next = new ArrayList<TextView>(al);


                Animation a = new TranslateAnimation(0.0f, 0.0f, 0.0f, -300.0f);
                a.setDuration(1400);
                Animation b = new TranslateAnimation(0.0f, 0.0f, 0.0f, -300.0f);
                b.setDuration(1000);
                Animation c = new TranslateAnimation(0.0f, 0.0f, 0.0f, -300.0f);
                c.setDuration(900);
                Animation d = new TranslateAnimation(0.0f, 0.0f, 0.0f, -300.0f);
                d.setDuration(800);
                Animation e = new TranslateAnimation(0.0f, 0.0f, 0.0f, -300.0f);
                e.setDuration(300);
                Animation f = new TranslateAnimation(0.0f, 0.0f, 0.0f, -300.0f);
                f.setDuration(200);

                switch (al.size()) {
                    case 1:
                        al.get(0).startAnimation(a);
                        break;
                    case 2:
                        al.get(0).startAnimation(a);
                        al.get(1).startAnimation(b);
                        break;
                    case 3:
                        al.get(0).startAnimation(a);
                        al.get(1).startAnimation(b);
                        al.get(2).startAnimation(c);

                        break;
                    case 4:
                        al.get(0).startAnimation(a);
                        al.get(1).startAnimation(b);
                        al.get(2).startAnimation(c);
                        al.get(3).startAnimation(d);

                        break;
                    case 5:
                        al.get(0).startAnimation(a);
                        al.get(1).startAnimation(b);
                        al.get(2).startAnimation(c);
                        al.get(3).startAnimation(d);
                        al.get(4).startAnimation(e);
                        break;
                    case 6:
                        al.get(0).startAnimation(a);
                        al.get(1).startAnimation(b);
                        al.get(2).startAnimation(c);
                        al.get(3).startAnimation(d);
                        al.get(4).startAnimation(e);
                        al.get(5).startAnimation(f);
                        break;
                }

                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        for(int i = 0; i<al.size(); i++){
                            al.get(i).setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent i = new Intent(getApplicationContext(), OnePlayer_activity.class);
                        check_math.setChecked(false);
                        check_phis.setChecked(false);
                        check_geo.setChecked(false);
                        check_astr.setChecked(false);
                        check_lit.setChecked(false);
                        check_chem.setChecked(false);
                        startActivity(i);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    }
}
