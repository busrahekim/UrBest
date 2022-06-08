package com.example.urbest;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgressAnimation extends Animation {

    private EntranceFragment context;
    ProgressBar progressBar;
    float from, to;

    public ProgressAnimation(EntranceFragment context, ProgressBar progressBar, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float val = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) val);

        if(val == to){
            context.startActivity(new Intent(context.getContext(),LoginActivity.class));
        }
    }
}
