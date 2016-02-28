package com.example.minorius.pr_9;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Minorius on 22.02.2016.
 */
public class Animate {

    Category_activity ca;
    int count;

    public void getCheck(int n){

        for(int i = 0; i < ca.test.size(); i++){
            if(Category_activity.test.get(i).isChecked()&& i == n){
                ca.al.add(ca.test2.get(i));
                ca.al.get(ca.al.size()-1).setVisibility(View.VISIBLE);

                ca.footer_2.setVisibility(View.VISIBLE);
                break;
            }
            else if(i == n && !(Category_activity.test.get(i).isChecked())){
                ca.al.size();
                ca.al.get(ca.al.size()-1).setVisibility(View.GONE);
                ca.al.remove( ca.al.size()-1);
            }
            else if(ca.al.size() == 0){
                ca.footer_2.setVisibility(View.INVISIBLE);
            }
        }
    }
}
