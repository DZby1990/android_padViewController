package com.yintong.e.padviewcontrol.view;

import android.content.Context;
import android.view.View;

import com.zhengyj.padviewservice.mainview.DialogViewWrapperBase;
import com.zhengyj.padviewservice.mainview.IBusActionListener;
import com.zhengyj.padviewservice.mainview.IDialogViewWrapper;

public class MiddleViewWrapper extends DialogViewWrapperBase implements IDialogViewWrapper {
    @Override
    protected View onCreateView(Context context, Object... args) {
        return new MiddleView(context,getMainView());
    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onNotifyDialogView(IBusActionListener busActionListener, Object... args) {

    }
}
