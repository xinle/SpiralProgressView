package com.live.spiralprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import static android.graphics.Path.Direction.CW;

/***
 * 自定义进度条
 */
public class SpiralProgressView extends View {
    private ProgressAttrs mAttrs;

    private int mWidth, mHeight;
    private float mSection;
    private Paint mPaint1, mPaint2;
    private float mBottomWidth;
    private RectF mContentRect;
    private Path mClipPath;
    private Path mTempPath;

    public SpiralProgressView(Context context) {
        super(context);
        initView();
    }

    public SpiralProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SpiralProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mAttrs = new ProgressAttrs(getContext());

        mPaint1 = new Paint();
        mPaint2 = new Paint();

        mPaint1.setAntiAlias(true);
        mPaint2.setAntiAlias(true);

        mClipPath = new Path();
        mTempPath = new Path();

        mContentRect = new RectF();

        attrChange();

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    private void attrChange() {
        mPaint1.setColor(mAttrs.mBackgroudColor);
        mPaint2.setColor(mAttrs.mLineColor);

        mBottomWidth = (float) (mHeight / Math.tan(Math.PI * mAttrs.mDegrees / 180));
        mClipPath.addRoundRect(mContentRect, mAttrs.mRound, mAttrs.mRound, CW);
    }

    private float moveLength;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        // 设置剪切区域
        canvas.clipPath(mClipPath);

        // 背景
        canvas.drawRoundRect(mContentRect, mAttrs.mRound, mAttrs.mRound, mPaint1);

        if(mAttrs.haveAnimation) {
            moveLength = moveLength + mAttrs.animationSpeed;
            if(moveLength > mWidth/2) {
                moveLength = 0;
            }
        }

        // 斜线
        for (float j = -mWidth + moveLength; j <= mWidth; j += mAttrs.mDisLine + mAttrs.mWLine) {
            mTempPath.reset();
            mTempPath.moveTo(j, mHeight);
            mTempPath.lineTo(j + mBottomWidth, 0);
            mTempPath.lineTo(j + mBottomWidth + mAttrs.mWLine, 0);
            mTempPath.lineTo(j + mAttrs.mWLine, mHeight);
            mTempPath.close();
            canvas.drawPath(mTempPath, mPaint2);
        }

        // 进度条
        canvas.drawRect(mWidth * mSection, 0, mWidth, mHeight, mPaint1);

        canvas.restore();

        if(mAttrs.haveAnimation) {
            postInvalidateDelayed(mAttrs.animationTime);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        mContentRect.set(0 , 0 , mWidth , mHeight);

        attrChange();

        super.onSizeChanged(w, h, oldw, oldh);
    }

    /***
     * 设置最大的进度值
     */
    public void setMax(int maxCount) {
        mAttrs.mMax = maxCount;
        if(mAttrs.mProgress > mAttrs.mMax) {
            mAttrs.mProgress = mAttrs.mMax;
        }
        mSection = mAttrs.mProgress * 1.0f / mAttrs.mMax;
        postInvalidate();
    }

    /***
     * 设置当前的进度值
     */
    public void setProgress(int progress) {
        if (progress == mAttrs.mProgress) {
            return;
        }
        mAttrs.mProgress = progress > mAttrs.mMax ? mAttrs.mMax : progress;
        mSection = mAttrs.mProgress * 1.0f / mAttrs.mMax;
        invalidate();
    }

    /**
     * 设置进度条属性
     * @param attrs
     */
    public void setProgressAttrs(ProgressAttrs attrs) {
        this.mAttrs = attrs;
        attrChange();
        postInvalidate();
    }

    public static class ProgressAttrs {
        public int mMax;       // 最大值
        public int mProgress;  // 进度
        public float mDisLine; // 斜线的距离
        public float mWLine;   // 斜线的宽度
        public int mDegrees;   // 斜线的角度
        public float mRound;   // 圆角
        public int mBackgroudColor; // 背景颜色
        public int mLineColor; // 斜线的颜色
        public boolean haveAnimation;  // 是否有动画
        public int animationTime;   // 动画的时间(前提haveAnimation=true)
        public float animationSpeed; // 动画的速率(前提haveAnimation=true)

        public ProgressAttrs(Context context) {
            mMax = 100;
            mProgress = 0;
            mDisLine = dp2px(context, 10);
            mWLine = dp2px(context, 10);
            mDegrees = 45;
            mRound = dp2px(context, 5);
            mBackgroudColor = Color.parseColor("#F86442");
            mLineColor = Color.parseColor("#D54A2A");

            haveAnimation = false;
            animationTime = 100;
            animationSpeed = mDisLine / 10;
        }

        public static float dp2px(Context context, float dipValue) {
            if (context == null)
                return dipValue;
            final float scale = context.getResources().getDisplayMetrics().density;
            return dipValue * scale + 0.5f;
        }
    }
}
