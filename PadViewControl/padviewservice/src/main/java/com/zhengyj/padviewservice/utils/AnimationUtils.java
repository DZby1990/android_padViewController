package com.zhengyj.padviewservice.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import com.zhengyj.padviewservice.mainview.DialogViewBuilder;
import com.zhengyj.padviewservice.mainview.IMainView;


public class AnimationUtils {


    public static IMainView.IDialogViewAnimation leftToRight(DialogViewBuilder dialogViewWrapper){

        return new LeftToRightDialogViewAnimation(dialogViewWrapper);
    }

    public static IMainView.IDialogViewAnimation rightToLeft(DialogViewBuilder dialogViewWrapper){

        return new RightToLeftDialogViewAnimation(dialogViewWrapper);
    }

    public static IMainView.IDialogViewAnimation bottomToTop(DialogViewBuilder dialogViewWrapper){

        return new BottomToTopDialogViewAnimation(dialogViewWrapper);
    }
    public static IMainView.IDialogViewAnimation topToBottom(DialogViewBuilder dialogViewWrapper){

        return new TopToBottomDialogViewAnimation(dialogViewWrapper);
    }


    public static class LeftToRightDialogViewAnimation implements IMainView.IDialogViewAnimation{

        private AnimationSet mAnimationSet;

        public LeftToRightDialogViewAnimation(DialogViewBuilder dialogViewBuilder) {


            //位移动画
            TranslateAnimation ta = new TranslateAnimation(
                    -dialogViewBuilder.getParentWidth(),//起始x坐标
                    0,//结束x坐标
                    0,//起始y坐标
                    0);//结束y坐标（正数向下移动）
            ta.setDuration(300);

            //渐变动画
            AlphaAnimation al = new AlphaAnimation(0.6f, 1f);
            al.setDuration(300);

            mAnimationSet = new AnimationSet(true);
            //动画完成后不回到原位
            mAnimationSet.setFillAfter(true);
            mAnimationSet.addAnimation(ta);
            mAnimationSet.addAnimation(al);
        }

        @Override
        public Animation getAnimation() {
            return mAnimationSet;
        }
    }


    public static class RightToLeftDialogViewAnimation implements IMainView.IDialogViewAnimation{

        private AnimationSet mAnimationSet;

        public RightToLeftDialogViewAnimation(DialogViewBuilder dialogViewBuilder) {


            //位移动画
            TranslateAnimation ta = new TranslateAnimation(
                    dialogViewBuilder.getParentWidth(),//起始x坐标
                    0,//结束x坐标
                    0,//起始y坐标
                    0);//结束y坐标（正数向下移动）
            ta.setDuration(300);

            //渐变动画
            AlphaAnimation al = new AlphaAnimation(0.6f, 1f);
            al.setDuration(300);

            mAnimationSet = new AnimationSet(true);
            //动画完成后不回到原位
            mAnimationSet.setFillAfter(true);
            mAnimationSet.addAnimation(ta);
            mAnimationSet.addAnimation(al);
        }

        @Override
        public Animation getAnimation() {
            return mAnimationSet;
        }
    }


    public static class BottomToTopDialogViewAnimation implements IMainView.IDialogViewAnimation{

        private AnimationSet mAnimationSet;

        public BottomToTopDialogViewAnimation(DialogViewBuilder dialogViewBuilder) {


            //位移动画
            TranslateAnimation ta = new TranslateAnimation(
                    0,//起始x坐标
                    0,//结束x坐标
                    dialogViewBuilder.getParentHeight(),//起始y坐标
                    0);//结束y坐标（正数向下移动）
            ta.setDuration(300);

            //渐变动画
            AlphaAnimation al = new AlphaAnimation(0.6f, 1f);
            al.setDuration(300);

            mAnimationSet = new AnimationSet(true);
            //动画完成后不回到原位
            mAnimationSet.setFillAfter(true);
            mAnimationSet.addAnimation(ta);
            mAnimationSet.addAnimation(al);
        }

        @Override
        public Animation getAnimation() {
            return mAnimationSet;
        }
    }


    public static class TopToBottomDialogViewAnimation implements IMainView.IDialogViewAnimation{

        private AnimationSet mAnimationSet;

        public TopToBottomDialogViewAnimation(DialogViewBuilder dialogViewBuilder) {


            //位移动画
            TranslateAnimation ta = new TranslateAnimation(
                    0,//起始x坐标
                    0,//结束x坐标
                    -dialogViewBuilder.getParentHeight(),//起始y坐标
                    0);//结束y坐标（正数向下移动）
            ta.setDuration(300);

            //渐变动画
            AlphaAnimation al = new AlphaAnimation(0.6f, 1f);
            al.setDuration(300);

            mAnimationSet = new AnimationSet(true);
            //动画完成后不回到原位
            mAnimationSet.setFillAfter(true);
            mAnimationSet.addAnimation(ta);
            mAnimationSet.addAnimation(al);
        }

        @Override
        public Animation getAnimation() {
            return mAnimationSet;
        }
    }

}
