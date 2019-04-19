package com.zhengyj.padviewservice.mainview;



public interface IMainViewContainer {


    /**
     * 开始加载
     */
    void onStartLoading(String content);

    /**
     * 停止加载
     */
    void onStopLoading();

    /**
     * 主View当前可见View的Name
     * @return
     */
    String onGetCurrVisibleViewName();

    /**
     * 添加DialogView
     * @param name
     * @param gravity
     * @param width
     * @param height
     */
    void onAddDialogView(String name, int gravity, int width, int height);

    /**
     * 销毁DialogView
     * @param name
     */
    void onRemoveDialogView(String name);


    /**
     * 主View收到通知
     * @param busActionListener
     *        调用者的回调
     * @param args
     *        调用者通知的参数
     */
    void onNotifyMainView(IBusActionListener busActionListener, Object... args);
}
