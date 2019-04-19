package com.yintong.e.padviewcontrol;

/**
 * Created by dell on 2018/4/26.
 */


public class SampleViewFactory {
    private Class leftViewClass;
    private Class rightViewClass;
    private Class middleViewClass;

    private static SampleViewFactory instance;

    public static SampleViewFactory getInstance() {
        if (instance == null) {
            instance = new SampleViewFactory();
        }
        return instance;
    }

    public Class getLeftViewClass() {
        return leftViewClass;
    }

    public void setLeftViewClass(Class leftViewClass) {
        this.leftViewClass = leftViewClass;
    }

    public Class getRightViewClass() {
        return rightViewClass;
    }

    public void setRightViewClass(Class rightViewClass) {
        this.rightViewClass = rightViewClass;
    }

    public Class getMiddleViewClass() {
        return middleViewClass;
    }

    public void setMiddleViewClass(Class middleViewClass) {
        this.middleViewClass = middleViewClass;
    }
}
