package com.yintong.e.padviewcontrol.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yintong.e.padviewcontrol.R;
import com.zhengyj.padviewservice.mainview.IBusActionListener;
import com.zhengyj.padviewservice.mainview.IDialogView;
import com.zhengyj.padviewservice.mainview.IMainView;

public class SampleDialogView extends FrameLayout implements IMainView.IDialogViewListener {
    private View mView;
    private IMainView mMainView;
    private String mSample;

    public SampleDialogView(@NonNull Context context, IMainView mainView, String sample) {
        super(context);
        mSample = sample;
        mMainView = mainView;
        init(null);
    }

    private void init(AttributeSet attrs) {

        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_sample_dialog, this);
        TextView showTv = findViewById(R.id.sample_tv);
        showTv.setText("" + mSample);


        Button showDialogBtn = mView.findViewById(R.id.show_dialog_btn);
        showDialogBtn.setVisibility(View.VISIBLE);
        showDialogBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainView.notifyMainView(new IBusActionListener() {
                    @Override
                    public void onFinish(boolean isSuccess, String desc, Object o) {

                    }
                },"LeftSampleDialogView","FromRightDialogView");
            }
        });
    }


    @Override
    public void onStart(IDialogView dialogView) {

    }

    @Override
    public void onDestroy(IDialogView dialogView) {

    }
}
