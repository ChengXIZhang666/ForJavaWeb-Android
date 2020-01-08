package com.example.forjavaweb.tool;

import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class RotateUtil {
    public static RotateAnimation rotateZeroToHalf() {
        RotateAnimation mFlipAnimation;
        mFlipAnimation = new RotateAnimation(0, 45, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(300);//如果参数是0，就是无限旋转
        mFlipAnimation.setFillAfter(true);
        return mFlipAnimation;
    }

    public static RotateAnimation backAnimations() {
        RotateAnimation mFlipAnimation;
        mFlipAnimation = new RotateAnimation(45, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(300);//如果参数是0，就是无限旋转
        mFlipAnimation.setFillAfter(true);
        return mFlipAnimation;
    }
}
