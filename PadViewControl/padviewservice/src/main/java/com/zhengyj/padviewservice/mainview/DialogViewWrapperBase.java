package com.zhengyj.padviewservice.mainview;

import android.content.Context;
import android.view.View;

/**
 * 对话框View包装类的基类
 *
 *
 */
public abstract class DialogViewWrapperBase implements IDialogViewWrapper {

    private int mViewWidth;
    private int mViewHeight;
    private String mName;

    private IMainView mMainView;

    @Override
    public View createView(Context context, IMainView mainView, String name, int width, int height, Object... args) {
        mMainView = mainView;


        mName = name;
        mViewWidth = width;
        mViewHeight = height;

        return onCreateView(context,args);
    }




    protected abstract View onCreateView(Context context, Object... args);

    protected IMainView getMainView(){
        return mMainView;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public int getViewWidth() {
        return mViewWidth;
    }

    @Override
    public int getViewHeight() {
        return mViewHeight;
    }

    @Override
    public void finish() {
        if(mMainView != null){
            mMainView.removeDialogView(getName(),null);
        }
    }
}
