package com.yintong.e.padviewcontrol;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.yintong.e.padviewcontrol.view.LeftSampleDialogViewWrapper;
import com.yintong.e.padviewcontrol.view.LeftView;
import com.yintong.e.padviewcontrol.view.MiddleView;
import com.yintong.e.padviewcontrol.view.RightView;
import com.yintong.e.padviewcontrol.view.SampleDialogViewWrapper;
import com.zhengyj.padviewservice.utils.AnimationUtils;
import com.zhengyj.padviewservice.mainview.DialogViewBuilder;
import com.zhengyj.padviewservice.mainview.IBusActionListener;
import com.zhengyj.padviewservice.mainview.IMainView;
import com.zhengyj.padviewservice.mainview.IMainViewContainer;
import com.zhengyj.padviewservice.mainview.MainViewProxy;
import com.zhengyj.padviewservice.utils.ScreenUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SamplePadActivity extends AppCompatActivity implements IMainViewContainer {

    private boolean mIsStart = true;
    private FrameLayout mRootView;
    private RelativeLayout mLeftView, mMiddleView, mRightView;
    private String mMainName = IMainView.MAIN_CONTENT_VIEW;
    private MainViewProxy mMainViewProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SampleViewFactory.getInstance().setLeftViewClass(LeftView.class);
        SampleViewFactory.getInstance().setRightViewClass(RightView.class);
        SampleViewFactory.getInstance().setMiddleViewClass(MiddleView.class);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_sample_pad);
        mLeftView = findViewById(R.id.left_view);
        mMiddleView = findViewById(R.id.middle_view);
        mRightView = findViewById(R.id.right_view);
        mRootView = findViewById(R.id.root_view);

        mMainViewProxy = new MainViewProxy(this, this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mIsStart) {
            getViewFromClass(SampleViewFactory.getInstance().getLeftViewClass(), mLeftView);
            getViewFromClass(SampleViewFactory.getInstance().getMiddleViewClass(), mMiddleView);
            getViewFromClass(SampleViewFactory.getInstance().getRightViewClass(), mRightView);
            mIsStart = false;
        }
    }


    @Override
    public void onStartLoading(String content) {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    public String onGetCurrVisibleViewName() {
        return mMainName;
    }

    @Override
    public void onAddDialogView(String name, int gravity, int width, int height) {

        mMainViewProxy.addViewToFrameLayout(mRootView, name, gravity, width, height);
    }

    @Override
    public void onRemoveDialogView(String name) {

        mMainViewProxy.removeViewFromFrameLayout(mRootView, name);
    }

    @Override
    public void onNotifyMainView(IBusActionListener busActionListener, Object... args) {
        if (((String) args[0]).equals("SampleDialogView")) {
            DialogViewBuilder dialogViewBuilder = new DialogViewBuilder(mMainViewProxy)
                    .name("SampleDialogView")
                    .width((int) (ScreenUtils.getScreenWidth(this)))
                    .height(ScreenUtils.getScreenHeight(this) - ScreenUtils.getStatusHeight(this))
                    .dialogViewWrapperClass(SampleDialogViewWrapper.class)
                    .args(args[1])
                    .cancelable(true)
                    .outsideTouchable(true)
                    .gravity(Gravity.RIGHT)
                    .parentGravity(Gravity.RIGHT);
            mMainViewProxy.addDialogView(dialogViewBuilder, AnimationUtils.rightToLeft(dialogViewBuilder));
        }else if (((String) args[0]).equals("LeftSampleDialogView")) {
            DialogViewBuilder dialogViewBuilder = new DialogViewBuilder(mMainViewProxy)
                    .name("LeftSampleDialogView")
                    .width((int) (ScreenUtils.getScreenWidth(this)))
                    .height(ScreenUtils.getScreenHeight(this) - ScreenUtils.getStatusHeight(this))
                    .dialogViewWrapperClass(LeftSampleDialogViewWrapper.class)
                    .args(args[1])
                    .cancelable(true)
                    .outsideTouchable(true)
                    .gravity(Gravity.LEFT)
                    .parentGravity(Gravity.LEFT);
            mMainViewProxy.addDialogView(dialogViewBuilder, AnimationUtils.leftToRight(dialogViewBuilder));
        }
    }

    private void getViewFromClass(Class viewClass, ViewGroup viewGroup) {
        viewGroup.removeAllViews();
        if (viewClass == null)
            return;
        try {
            Constructor constructor = viewClass.getConstructor(Context.class, IMainView.class);
            try {
                viewGroup.addView((View) constructor.newInstance(this, mMainViewProxy));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return;
    }
}
