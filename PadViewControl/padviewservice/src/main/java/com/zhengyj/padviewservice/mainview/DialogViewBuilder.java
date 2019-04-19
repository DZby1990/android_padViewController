package com.zhengyj.padviewservice.mainview;

import android.view.Gravity;

import com.zhengyj.padviewservice.utils.ClassUtils;


public class DialogViewBuilder {

    private String mName;

    private int mGravity;
    private int mLeft;
    private int mTop;
    private int mWidth;
    private int mHeight;



    private int mParentGravity;
    private int mParentLeft;
    private int mParentTop;
    private int mParentWidth;
    private int mParentHeight;

    private boolean mCancelable;
    private boolean mOutsideTouchable;

    private Object[] mArgs;


    private Class<? extends IDialogViewWrapper> mDialogViewWrapperClass;

    public String getName() {
        return mName;
    }

    public int getGravity() {
        return mGravity;
    }

    public int getLeft() {
        return mLeft;
    }

    public int getTop() {
        return mTop;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getParentGravity() {
        return mParentGravity;
    }

    public int getParentLeft() {
        return mParentLeft;
    }

    public int getParentTop() {
        return mParentTop;
    }

    public int getParentWidth() {
        return mParentWidth;
    }

    public int getParentHeight() {
        return mParentHeight;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    public boolean isOutsideTouchable() {
        return mOutsideTouchable;
    }

    public Object[] getArgs() {
        return mArgs;
    }

    private IDialogViewWrapper mDialogViewWrapper;
    public IDialogViewWrapper getDialogViewWrapper() {

        if(mDialogViewWrapper == null && mDialogViewWrapperClass != null){
            mDialogViewWrapper = ClassUtils.newInstance(mDialogViewWrapperClass);
        }
        return mDialogViewWrapper;
    }

    public DialogViewBuilder(IMainView mainView) {

        mParentGravity = Gravity.LEFT|Gravity.TOP;
        mParentWidth = mainView.getScreenWidth();
        mParentHeight = mainView.getScreenHeight();

        mGravity = Gravity.LEFT|Gravity.TOP;

        mName = String.format("DialogView:%d",System.currentTimeMillis());
    }

    /**
     * 对话框View的名字，必须是唯一，否则添加失败
     * @param name
     * @return
     */
    public DialogViewBuilder name(String name) {

        mName = name;
        return this;
    }


    /**
     * 对话框View的方位
     * @param gravity
     * @return
     */
    public DialogViewBuilder gravity(int gravity) {

        mGravity = gravity;
        return this;
    }

    /**
     * 对话框View的靠左的距离
     * @param left
     * @return
     */
    public DialogViewBuilder left(int left) {

        mLeft = left;
        return this;
    }

    /**
     * 对话框View的靠上的距离
     * @param top
     * @return
     */
    public DialogViewBuilder top(int top) {

        mTop = top;
        return this;
    }

    /**
     * 对话框View的宽度
     * @param width
     * @return
     */
    public DialogViewBuilder width(int width) {

        mWidth = width;
        return this;
    }

    /**
     * 对话框View的高度
     * @param height
     * @return
     */
    public DialogViewBuilder height(int height) {

        mHeight = height;
        return this;
    }

    /**
     * 对话框View的父View的方位
     * @param parentGravity
     * @return
     */
    public DialogViewBuilder parentGravity(int parentGravity) {

        mParentGravity = parentGravity;
        return this;
    }

    /**
     * 对话框View的父View靠左的距离
     * @param parentLeft
     * @return
     */
    public DialogViewBuilder parentLeft(int parentLeft) {

        mParentLeft = parentLeft;
        return this;
    }

    /**
     * 对话框View的父View靠上的距离
     * @param parentTop
     * @return
     */
    public DialogViewBuilder parentTop(int parentTop) {

        mParentTop = parentTop;
        return this;
    }

    /**
     * 对话框View的父View的宽度
     * @param parentWidth
     * @return
     */
    public DialogViewBuilder parentWidth(int parentWidth) {

        mParentWidth = parentWidth;
        return this;
    }

    /**
     * 对话框View的父View的高度
     * @param parentHeight
     * @return
     */
    public DialogViewBuilder parentHeight(int parentHeight) {

        mParentHeight = parentHeight;
        return this;
    }

    /**
     * 对话框View是否可以点击Outside自动消失
     * @param cancelable
     * @return
     */
    public DialogViewBuilder cancelable(boolean cancelable) {

        mCancelable = cancelable;
        if(mCancelable == true)
            mOutsideTouchable = true;
        return this;
    }

    /**
     * 对话框View的Outside区域是否可以点击
     * @param outsideTouchable
     * @return
     */
    public DialogViewBuilder outsideTouchable(boolean outsideTouchable) {

        mOutsideTouchable = outsideTouchable;
        return this;
    }

    /**
     * 对话框View的Class
     * @param dialogViewWrapperClass
     * @return
     */
    public DialogViewBuilder dialogViewWrapperClass(Class<? extends IDialogViewWrapper> dialogViewWrapperClass) {

        mDialogViewWrapperClass = dialogViewWrapperClass;
        return this;
    }

    /**
     * 传给DialogView的参数
     * @param args
     * @return
     */
    public DialogViewBuilder args(Object... args) {

        mArgs = args;
        return this;
    }

}
