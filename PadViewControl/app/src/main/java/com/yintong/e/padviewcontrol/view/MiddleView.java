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
import com.zhengyj.padviewservice.mainview.IMainView;

public class MiddleView  extends FrameLayout {

    private IMainView mMainView;

    public MiddleView(@NonNull Context context, IMainView mainView) {
        super(context);
        mMainView  = mainView;
        init(null);
    }

    private void init(AttributeSet attrs) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_sample_main, this);
        TextView showTv = view.findViewById(R.id.sample_tv);
        showTv.setText("MiddleView");

        Button showDialogBtn = view.findViewById(R.id.show_dialog_btn);
        showDialogBtn.setVisibility(View.VISIBLE);
        showDialogBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainView.notifyMainView(new IBusActionListener() {
                    @Override
                    public void onFinish(boolean isSuccess, String desc, Object o) {

                    }
                },"SampleDialogView","FromMiddleView");
            }
        });
    }
}
