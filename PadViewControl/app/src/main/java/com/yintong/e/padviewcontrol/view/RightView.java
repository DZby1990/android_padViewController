package com.yintong.e.padviewcontrol.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yintong.e.padviewcontrol.R;
import com.zhengyj.padviewservice.mainview.IMainView;

public class RightView extends FrameLayout {
    public RightView(@NonNull Context context, IMainView mainView) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_sample_main, this);
        TextView showTv = view.findViewById(R.id.sample_tv);
        showTv.setText("RightView");
    }
}
