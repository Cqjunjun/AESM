//控件动画类，可控制案件的平移，旋转，缩放
package com.example.aesm;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.Button;
import android.widget.ImageView;

public class Animation_change {
    //按钮旋转
    static void startRotation(Button A){
        ObjectAnimator mobjectAnimator = ObjectAnimator.ofFloat(A,"rotation",360f,0f);
        mobjectAnimator.setDuration(3000);
        mobjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
            }
        });
        mobjectAnimator.start();
    }
    //按钮平移
    static void startTranslation(Button B,float B_data_X,float B_data_Y){
        float translationX = B.getTranslationX();
        float translationY = B.getTranslationY();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(B,"translationX",translationX,B_data_X);
        ObjectAnimator.ofFloat(B,"translationY",translationY,B_data_Y);
        objectAnimator.setDuration(3000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
            }
        });
        objectAnimator.start();
    }
    //按钮缩放
    static void startScale(Button C,float C_data){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(C,"scaleY",1f,C_data);
        ObjectAnimator.ofFloat(C,"scaleX",1f,C_data);
        objectAnimator.setDuration(3000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
            }
        });
        objectAnimator.start();
    }
    //手动操作动画集
    static void animatorSet1(Button X){
        float translationX = X.getTranslationX();
        ObjectAnimator animx_1 = ObjectAnimator.ofFloat(X,"translationX",-400f,translationX);
        ObjectAnimator animx_2 = ObjectAnimator.ofFloat(X,"rotation",360f,0f);
        ObjectAnimator animx_3 = ObjectAnimator.ofFloat(X,"scaleY",0.1f,1f);
        ObjectAnimator animx_4 = ObjectAnimator.ofFloat(X,"scaleX",0.1f,1f);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(3000);
        mAnimatorSet.play(animx_1).with(animx_2).with(animx_3).with(animx_4);
        mAnimatorSet.start();
    }
    //警报动画集
    static void animatorSet2(Button Y){
        float translationX = Y.getTranslationX();
        ObjectAnimator animx_1 = ObjectAnimator.ofFloat(Y,"translationX",400f,translationX);
        ObjectAnimator animx_2 = ObjectAnimator.ofFloat(Y,"rotation",0f,360f);
        ObjectAnimator animx_3 = ObjectAnimator.ofFloat(Y,"scaleY",0.1f,1f);
        ObjectAnimator animx_4 = ObjectAnimator.ofFloat(Y,"scaleX",0.1f,1f);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(3000);
        mAnimatorSet.play(animx_1).with(animx_2).with(animx_3).with(animx_4);
        mAnimatorSet.start();
    }
    //关于动画集
    static void animatorSet3(Button Z){
        float translationY = Z.getTranslationY();
        ObjectAnimator animx_1 = ObjectAnimator.ofFloat(Z,"translationY",600f,translationY);
        ObjectAnimator animx_2 = ObjectAnimator.ofFloat(Z,"rotation",360f,0f);
        ObjectAnimator animx_3 = ObjectAnimator.ofFloat(Z,"scaleY",0.1f,1f);
        ObjectAnimator animx_4 = ObjectAnimator.ofFloat(Z,"scaleX",0.1f,1f);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(3000);
        mAnimatorSet.play(animx_1).with(animx_2).with(animx_3).with(animx_4);
        mAnimatorSet.start();
    }
}
