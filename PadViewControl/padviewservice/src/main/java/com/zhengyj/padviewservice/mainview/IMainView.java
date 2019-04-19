package com.zhengyj.padviewservice.mainview;


import android.view.animation.Animation;


/**
 *
 * 主View
 *
 *
 */
public interface IMainView {


    final String MAIN_CONTENT_VIEW = "MAIN_CONTENT_VIEW";

    /**
     * DialogView的动画
     */
    public interface IDialogViewAnimation{

        Animation getAnimation();
    }


    /**
     * DialogView生命周期的回调
     */
    public interface IDialogViewListener {

        void onStart(IDialogView dialogView);

        void onDestroy(IDialogView dialogView);
    }

    int getScreenWidth();

    int getScreenHeight();

    int getViewWidth();

    int getViewHeight();

    /**
     * 主View开始加载
     */
    void startLoading(String content);

    /**
     * 主View停止加载
     */
    void stopLoading();


    /**
     * 获取主View当前可见View的Name
     * @return
     */
    String getCurrentVisibleViewName();


    /**
     * 主View添加DialogView
     * @param dialogViewBuilder
     * @param dialogViewAnimation
     */
    void addDialogView(DialogViewBuilder dialogViewBuilder, IDialogViewAnimation dialogViewAnimation);

    /**
     * 主View销毁DialogView
     * @param name
     * @param dialogViewAnimation
     */
    void removeDialogView(String name, IDialogViewAnimation dialogViewAnimation);

    /**
     * 添加DialogView生命周期监听器
     * @param dialogViewListener
     */
    void addDialogViewListener(IDialogViewListener dialogViewListener);

    /**
     * 删除DialogView生命周期监听器
     * @param dialogViewListener
     */
    void removeDialogViewListener(IDialogViewListener dialogViewListener);

    /**
     * 通知主View
     * @param busActionListener
     *        调用者的回调
     * @param args
     *        通知的参数
     */
    void notifyMainView(IBusActionListener busActionListener, Object... args);

    /**
     * 通知其他DialogView
     * @param name
     *        其他DialogView的Name
     * @param busActionListener
     *        调用者的回调
     * @param args
     *        通知的参数
     */
    void notifyDialogView(String name, IBusActionListener busActionListener, Object... args);
}
