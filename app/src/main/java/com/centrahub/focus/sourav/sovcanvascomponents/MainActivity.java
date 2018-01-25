package com.centrahub.focus.sourav.sovcanvascomponents;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.centrahub.focus.sourav.sovcanvascomponents.sovrounddetailsitem.SovRoundedDetailsItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView=new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(getMainlinearLayou());
        setContentView(scrollView);
    }
    public LinearLayout getMainlinearLayou() {
        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.LTGRAY);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        SovRoundedDetailsItem clCalendarView = new SovRoundedDetailsItem(this);
        clCalendarView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        clCalendarView.setOnItemSelectListener(this);


        linearLayout.addView(clCalendarView);
        return linearLayout;
    }


}
