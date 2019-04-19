package com.yintong.e.padviewcontrol.view;

import android.content.Context;
import android.view.View;

import com.zhengyj.padviewservice.mainview.DialogViewWrapperBase;
import com.zhengyj.padviewservice.mainview.IBusActionListener;
import com.zhengyj.padviewservice.mainview.IDialogViewWrapper;

public class SampleDialogViewWrapper extends DialogViewWrapperBase implements IDialogViewWrapper {
    @Override
    protected View onCreateView(Context context, Object... args) {
        return new SampleDialogView(context, getMainView(), (String) args[0]);
    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onNotifyDialogView(IBusActionListener busActionListener, Object... args) {

    }
}
