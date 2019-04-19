package com.zhengyj.padviewservice.mainview;


public interface IDialogView {


    /**
     * DialogView的名称,通过Name查找View
     * @return
     */
    String getName();


    int getViewWidth();

    int getViewHeight();
}
