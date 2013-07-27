package com.yahoo.interviewr;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class DropDownAnim extends Animation {

	public static final int DURATION_200 = 200;
	public static final int DURATION_400 = 400;
	public static final int DEFAULT_DURATION = DURATION_200;

	int mTargetHeight;
    View mView;
    boolean mIsDownDirection;
    
    public DropDownAnim(View target,  boolean d) {
    	this(target, d, DEFAULT_DURATION);
    }

    public DropDownAnim(View target,  boolean d, int duration){
        mView = target;
        // Figure out desired height
        mView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        mTargetHeight = mView.getMeasuredHeight();
        mIsDownDirection = d;
        setDuration(duration);
    }


    protected void applyTransformation(float interpolatedTime, Transformation t){
        int newHeight;
        if (mIsDownDirection) {
              newHeight = (int)(mTargetHeight*interpolatedTime);
        } else {
              newHeight = (int)(mTargetHeight*(1-interpolatedTime));
        }
        mView.getLayoutParams().height = newHeight;
        mView.requestLayout();
    }

    public void initalize(int width, int height, int parentWidth, int parentHeight){
        super.initialize(width,height,parentWidth,parentHeight);

    }

    public boolean willChangeBounds(){
        return true;
    }
}