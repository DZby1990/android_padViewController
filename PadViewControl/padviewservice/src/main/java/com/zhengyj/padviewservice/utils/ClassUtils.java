package com.zhengyj.padviewservice.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ClassUtils {

    //region 静态变量

    public static <T> T newInstance(Class<T> clazz){

        T instance = null;
        Constructor<?>[] cs = clazz.getDeclaredConstructors();
        try {

            instance = (T) cs[0].newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return instance;
    }
    //endregion


    //region 成员变量
    //endregion


    //region 构造函数
    private ClassUtils() {
    }
    //endregion


    //region 重载函数
    //endregion


    //region 自定义方法
    //endregion
}
