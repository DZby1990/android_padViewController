package com.zhengyj.padviewservice.mainview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


public class ViewWrapper extends FrameLayout {

    public ViewWrapper(@NonNull Context context) {
        super(context);
    }

    public ViewWrapper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewWrapper(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private View mContentView;

    public ViewWrapper(@NonNull Context context, final IMainView mainView, IDialogViewWrapper dialogView,final String name , int gravity, int left, int top, int width,int height,final boolean cancelable, boolean outsideTouchable,Object... args){
        super(context);

        if(cancelable == true)
            outsideTouchable = true;

       if(outsideTouchable == true) {

           View bg = new View(context);
           LayoutParams bgLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

           bg.setBackgroundColor(Color.GRAY);
           bg.setAlpha(0.45f);


           bg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(cancelable == true){

                        mainView.removeDialogView(name,null);
                    }
                }
            });
           addView(bg,bgLayoutParams);
        }

        LayoutParams layoutParams = new LayoutParams(width,height);
        layoutParams.gravity = gravity;
        layoutParams.leftMargin = left;
        layoutParams.topMargin = top;

        mContentView = dialogView.createView(context,mainView,name,width,height,args);
        addView(mContentView,layoutParams);

    }

    public View getContentView() {
        return mContentView;
    }
}
