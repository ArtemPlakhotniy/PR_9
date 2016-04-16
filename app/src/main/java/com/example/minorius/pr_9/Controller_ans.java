package com.example.minorius.pr_9;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.minorius.pr_9.Fragment.Internet_player;
import com.firebase.client.Firebase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Controller_ans extends Service {

    private DataOutputStream out;
    private DataInputStream in;

    private ArrayList<Integer> arrayList_ans;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int a = intent.getIntExtra("ans", 454);

        arrayList_ans = new ArrayList<>();
        arrayList_ans.add(203);
        arrayList_ans.add(a);

        try {

            in = new DataInputStream(TwoPlayer_activity.socket.getInputStream());
            out = new DataOutputStream(TwoPlayer_activity.socket.getOutputStream());

            for (int i = 0; i < arrayList_ans.size(); i++) {
                out.writeInt(arrayList_ans.get(i));
            }

            out.writeInt(303);
            out.flush();

            arrayList_ans.clear();


        } catch (Exception x) {
            x.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
