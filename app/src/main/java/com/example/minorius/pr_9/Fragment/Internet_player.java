package com.example.minorius.pr_9.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minorius.pr_9.Communicator;
import com.example.minorius.pr_9.R;
import com.example.minorius.pr_9.TwoPlayer_activity;

public class Internet_player extends Fragment{

    private TextView txt_question;
    private TextView txt_answer_a;
    private TextView txt_answer_b;
    private TextView txt_answer_c;
    private TextView txt_answer_d;

    private ImageView btn_answer_a;
    private ImageView btn_answer_b;
    private ImageView btn_answer_c;
    private ImageView btn_answer_d;

    private ImageView back_1_true;
    private ImageView back_2_true;
    private ImageView back_3_true;
    private ImageView back_4_true;

    Communicator comm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.prototype, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comm = (Communicator) getActivity();

        btn_answer_a = (ImageView) getActivity().findViewById(R.id.btn_answer_a);
        btn_answer_b = (ImageView) getActivity().findViewById(R.id.btn_answer_b);
        btn_answer_c = (ImageView) getActivity().findViewById(R.id.btn_answer_c);
        btn_answer_d = (ImageView) getActivity().findViewById(R.id.btn_answer_d);

        back_1_true = (ImageView) getActivity().findViewById(R.id.back_1_true);
        back_2_true = (ImageView) getActivity().findViewById(R.id.back_2_true);
        back_3_true = (ImageView) getActivity().findViewById(R.id.back_3_true);
        back_4_true = (ImageView) getActivity().findViewById(R.id.back_4_true);

        txt_question = (TextView) getActivity().findViewById(R.id.txt_question);
        txt_answer_a = (TextView) getActivity().findViewById(R.id.txt_answer_a);
        txt_answer_b = (TextView) getActivity().findViewById(R.id.txt_answer_b);
        txt_answer_c = (TextView) getActivity().findViewById(R.id.txt_answer_c);
        txt_answer_d = (TextView) getActivity().findViewById(R.id.txt_answer_d);

        
        //задаєем текст вюшкам(питання, відповіді)
        txt_question.setText(TwoPlayer_activity.gs.getQuestion());
        txt_answer_a.setText(TwoPlayer_activity.gs.getA());
        txt_answer_b.setText(TwoPlayer_activity.gs.getB());
        txt_answer_c.setText(TwoPlayer_activity.gs.getC());
        txt_answer_d.setText(TwoPlayer_activity.gs.getD());


        btn_answer_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getA())) {
                    back_1_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getB())) {
                    back_2_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getC())) {
                    back_3_true.setVisibility(View.VISIBLE);
                } else {
                    back_4_true.setVisibility(View.VISIBLE);
                }

                comm.fragmentCallBack(TwoPlayer_activity.gs.getA());
            }
        });

        btn_answer_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getA())) {
                    back_1_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getB())) {
                    back_2_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getC())) {
                    back_3_true.setVisibility(View.VISIBLE);
                } else {
                    back_4_true.setVisibility(View.VISIBLE);
                }

                comm.fragmentCallBack(TwoPlayer_activity.gs.getB());
            }
        });

        btn_answer_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getA())) {
                    back_1_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getB())) {
                    back_2_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getC())) {
                    back_3_true.setVisibility(View.VISIBLE);
                } else {
                    back_4_true.setVisibility(View.VISIBLE);
                }

                comm.fragmentCallBack(TwoPlayer_activity.gs.getC());
            }
        });

        btn_answer_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getA())) {
                    back_1_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getB())) {
                    back_2_true.setVisibility(View.VISIBLE);
                } else if (TwoPlayer_activity.gs.getAnswer().equals(TwoPlayer_activity.gs.getC())) {
                    back_3_true.setVisibility(View.VISIBLE);
                } else {
                    back_4_true.setVisibility(View.VISIBLE);
                }

                comm.fragmentCallBack(TwoPlayer_activity.gs.getD());
            }
        });
    }

}
