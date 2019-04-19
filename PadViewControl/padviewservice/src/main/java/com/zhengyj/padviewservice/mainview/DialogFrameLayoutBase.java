package com.zhengyj.padviewservice.mainview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;


/**
 *
 * DialogView的一个FrameLayout的基类实现
 *
 */
public abstract class DialogFrameLayoutBase extends FrameLayout {

    private IMainView mMainView;
    private IDialogViewWrapper mDialogViewWrapper;
    public DialogFrameLayoutBase(@NonNull Context context,IMainView mainView,IDialogViewWrapper dialogViewWrapper) {
        super(context);
        init(null);

        mMainView = mainView;
        mDialogViewWrapper = dialogViewWrapper;
    }

    public DialogFrameLayoutBase(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DialogFrameLayoutBase(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs ) {

        LayoutInflater.from(getContext()).inflate(getLayoutResource(), this);

        initView(attrs);
    }

    protected abstract int getLayoutResource();

    protected abstract void initView(AttributeSet attrs );

    public IMainView getMainView() {
        return mMainView;
    }

    public String getName(){
        return mDialogViewWrapper != null ? mDialogViewWrapper.getName() : null;
    }

    public void finish(){

        if(mDialogViewWrapper != null){
            mDialogViewWrapper.finish();
        }
    }
}
