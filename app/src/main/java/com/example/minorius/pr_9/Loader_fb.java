package com.example.minorius.pr_9;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.LinkedHashMap;
import java.util.Map;

public class Loader_fb extends AsyncTaskLoader<String> {

    public Loader_fb(Context context, Bundle args) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        return null;
    }

    @Override
    public void forceLoad() {

        super.forceLoad();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();

    }

    @Override
    public void deliverResult(String data) {

        super.deliverResult(data);

    }
}