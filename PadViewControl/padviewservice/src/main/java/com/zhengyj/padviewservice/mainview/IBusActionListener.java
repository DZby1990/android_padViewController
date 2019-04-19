package com.zhengyj.padviewservice.mainview;

import java.io.Serializable;


public interface IBusActionListener<TParam> extends Serializable {

    void onFinish(boolean isSuccess, String desc, TParam param);
}
