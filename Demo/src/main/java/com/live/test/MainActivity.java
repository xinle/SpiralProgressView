package com.live.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.live.spiralprogressview.SpiralProgressView;

public class MainActivity extends AppCompatActivity {

    private SpiralProgressView mProgressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressView = (SpiralProgressView) findViewById(R.id.spiral_progress);
        mProgressView.setProgress(20);
        SpiralProgressView.ProgressAttrs attrs = new SpiralProgressView.ProgressAttrs(this);
        attrs.haveAnimation = true;
        mProgressView.setProgressAttrs(attrs);
    }
}
