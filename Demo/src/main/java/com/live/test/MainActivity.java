package com.live.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.live.spiralprogressview.SpiralProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpiralProgressView progressView = (SpiralProgressView) findViewById(R.id.spiral_progress);
        progressView.setProgress(20);
        SpiralProgressView.ProgressAttrs attrs = new SpiralProgressView.ProgressAttrs(this);
        attrs.haveAnimation = true;
        progressView.setProgressAttrs(attrs);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        SpiralProgressView progressView1 = new SpiralProgressView(this);
        progressView1.setProgress(30);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
        params.topMargin = 30;
        progressView1.setLayoutParams(params);
        SpiralProgressView.ProgressAttrs attrs1 = new SpiralProgressView.ProgressAttrs(this);
        attrs1.haveAnimation = false;
        attrs1.mBackgroudColor = Color.GREEN;
        attrs1.mLineColor = Color.GRAY;
        attrs1.mDegrees = 30;
        attrs1.mWLine = SpiralProgressView.ProgressAttrs.dp2px(this ,20);
        attrs1.mDisLine = SpiralProgressView.ProgressAttrs.dp2px(this ,20);
        attrs1.mRound = 40;
        progressView1.setProgressAttrs(attrs1);

        linearLayout.addView(progressView1);

    }
}
