package com.zhengyj.padviewservice.mainview;

import  android.content.Context;
import android.view.View;


/**
 * 对话框View的包装类
 *
 *
 */
public interface IDialogViewWrapper extends IDialogView {

    /**
     * 创建View
     * @param context
     * @param mainView
     * @param name
     * @param width
     * @param height
     * @param args
     * @return
     */
    View createView(Context context, IMainView mainView, String name, int width, int height, Object... args);

    /**
     * 销毁View
     */
    void destroyView();

    /**
     * 其他DialogView通知
     * @param busActionListener
     *        其他DialogView的回调
     * @param args
     *        其他DialogView通知过来的参数
     */
    void onNotifyDialogView(IBusActionListener busActionListener, Object... args);

    /**
     * 关闭自己
     */
    void finish();
}
