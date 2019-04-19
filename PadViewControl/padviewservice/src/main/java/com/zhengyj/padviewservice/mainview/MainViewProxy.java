package com.zhengyj.padviewservice.mainview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.widget.FrameLayout;


import com.zhengyj.padviewservice.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * IMainView的代理类
 *
 *
 */
public class MainViewProxy implements IMainView {


    private final static int Z_INDEX = 1000;

    private int mIndex = Z_INDEX;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mViewWidth;
    private int mViewHeight;




    class Holder{

        IDialogViewWrapper DialogViewWrapper;
        ViewWrapper ViewWrapper;
        int ZIndex;

        public Holder(IDialogViewWrapper dialogViewWrapper, ViewWrapper viewWrapper, int zIndex) {
            DialogViewWrapper = dialogViewWrapper;
            ViewWrapper = viewWrapper;
            ZIndex = zIndex;
        }
    }

    private List<IDialogViewListener> mDialogViewWrapperListeners = new ArrayList<>();
    private Map<String,Holder> mViewMap = new HashMap<>();
    private IMainViewContainer mMainViewContainer;
    private Context mContext;

    public MainViewProxy(Context context, int viewWidth, int viewHeight, IMainViewContainer mainViewContainer) {

        mContext = context;
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        mScreenHeight = ScreenUtils.getScreenHeight(context);

        mViewWidth = viewWidth;
        mViewHeight = viewHeight;

        mMainViewContainer = mainViewContainer;
    }


    public MainViewProxy(Context context,   IMainViewContainer mainViewContainer) {

        this(context,ScreenUtils.getScreenWidth(context),ScreenUtils.getScreenHeight(context),mainViewContainer);
    }




    public void addViewToFrameLayout(FrameLayout frameLayout,String name,int gravity, int width,int height){

        Holder holder = mViewMap.get(name);
        if(holder != null && frameLayout != null){

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width,height);
            layoutParams.gravity = gravity;
            ViewCompat.setTranslationZ(holder.ViewWrapper,holder.ZIndex);

            frameLayout.addView(holder.ViewWrapper,layoutParams);
        }
    }




    public void removeViewFromFrameLayout(FrameLayout frameLayout, String name){

        Holder holder = mViewMap.get(name);
        if(holder != null && frameLayout != null){

            frameLayout.removeView(holder.ViewWrapper);
            holder.DialogViewWrapper.destroyView();

            notifyDestroy(holder.DialogViewWrapper);

            mViewMap.remove(name);
        }
    }

    @Override
    public int getScreenWidth() {
        return mScreenWidth;
    }

    @Override
    public int getScreenHeight() {
        return mScreenHeight;
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
    public void addDialogView(DialogViewBuilder dialogViewBuilder, IDialogViewAnimation dialogViewAnimation) {
        if(mMainViewContainer != null){

            Holder holder = mViewMap.get(dialogViewBuilder.getName());
            if(holder == null){

                final IDialogViewWrapper dialogViewWrapper = dialogViewBuilder.getDialogViewWrapper();

                ViewWrapper viewWrapper = new ViewWrapper(mContext,this,dialogViewWrapper,dialogViewBuilder.getName(),
                        dialogViewBuilder.getGravity(),dialogViewBuilder.getLeft(),dialogViewBuilder.getTop(),
                        dialogViewBuilder.getWidth(),dialogViewBuilder.getHeight(),
                        dialogViewBuilder.isCancelable(),dialogViewBuilder.isOutsideTouchable(),dialogViewBuilder.getArgs());

                mIndex++;
                mViewMap.put(dialogViewBuilder.getName(), new Holder(dialogViewWrapper,viewWrapper,mIndex));

                mMainViewContainer.onAddDialogView(dialogViewBuilder.getName(),dialogViewBuilder.getParentGravity(),dialogViewBuilder.getParentWidth(),dialogViewBuilder.getParentHeight());

                if(dialogViewAnimation != null){
                    Animation animation = dialogViewAnimation.getAnimation();
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            notifyStart(dialogViewWrapper);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    viewWrapper.getContentView().startAnimation(animation);
                }else{

                    notifyStart(dialogViewWrapper);
                }
            }else{

                throw new IllegalArgumentException(String.format("Dialog ViewWrapper %s exist",dialogViewBuilder.getName()));
            }
        }
    }

    @Override
    public void removeDialogView(final String name, IDialogViewAnimation dialogViewAnimation) {

        if(mMainViewContainer != null){

            Holder holder = mViewMap.get(name);

            if(holder != null){

                if(dialogViewAnimation != null){

                    Animation animation = dialogViewAnimation.getAnimation();
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            mMainViewContainer.onRemoveDialogView(name);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    holder.ViewWrapper.getContentView().startAnimation(animation);
                }else{

                    mMainViewContainer.onRemoveDialogView(name);
                }


            }
        }
    }


    @Override
    public void addDialogViewListener(IDialogViewListener dialogViewWrapperListener) {

        if(dialogViewWrapperListener != null){
            mDialogViewWrapperListeners.add(dialogViewWrapperListener);
        }
    }

    @Override
    public void removeDialogViewListener(IDialogViewListener dialogViewWrapperListener) {
        if(dialogViewWrapperListener != null){
            mDialogViewWrapperListeners.remove(dialogViewWrapperListener);
        }
    }

    @Override
    public void startLoading(String content) {

        if(mMainViewContainer != null) {
            mMainViewContainer.onStartLoading(content);
        }


    }

    @Override
    public void stopLoading() {

        if(mMainViewContainer != null) {
            mMainViewContainer.onStopLoading();
        }
    }

    @Override
    public String getCurrentVisibleViewName() {

        if(mMainViewContainer != null) {
            return mMainViewContainer.onGetCurrVisibleViewName();
        }else
            return null;
    }

    @Override
    public void notifyMainView(IBusActionListener busActionListener, Object... args) {

        if(mMainViewContainer != null){
            mMainViewContainer.onNotifyMainView(busActionListener,args);
        }
    }

    @Override
    public void notifyDialogView(String name, IBusActionListener busActionListener, Object... args) {

        Holder holder = mViewMap.get(name);
        if(holder != null){
            holder.DialogViewWrapper.onNotifyDialogView(busActionListener,args);
        }
    }

    private void notifyStart(IDialogViewWrapper dialogViewWrapper){

        if(mDialogViewWrapperListeners != null) {
            IDialogViewListener[] dialogViewWrapperListeners = mDialogViewWrapperListeners.toArray(new IDialogViewListener[mDialogViewWrapperListeners.size()]);

            if (dialogViewWrapperListeners != null) {

                for (IDialogViewListener dialogViewWrapperListener : dialogViewWrapperListeners) {

                    dialogViewWrapperListener.onStart(dialogViewWrapper);
                }
            }
        }
    }

    private void notifyDestroy(IDialogViewWrapper dialogViewWrapper){

        if(mDialogViewWrapperListeners != null) {
            IDialogViewListener[] dialogViewWrapperListeners = mDialogViewWrapperListeners.toArray(new IDialogViewListener[mDialogViewWrapperListeners.size()]);

            if (dialogViewWrapperListeners != null) {

                for (IDialogViewListener dialogViewWrapperListener : dialogViewWrapperListeners) {

                    dialogViewWrapperListener.onDestroy(dialogViewWrapper);
                }
            }
        }
    }
}
