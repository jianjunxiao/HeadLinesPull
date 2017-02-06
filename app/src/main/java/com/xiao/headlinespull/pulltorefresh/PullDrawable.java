package com.xiao.headlinespull.pulltorefresh;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by XiaoJianjun on 2017/2/3.
 * 刷新Drawable。
 */
public class PullDrawable extends Drawable {


    private Paint mPaint;
    private Paint mInnerPaint;

    private Path mBorderPath;
    private Path mBorderDstPath;
    private PathMeasure mBorderPathMeasure;

    private Path mRectPath;
    private Path mRectDstPath;
    private PathMeasure mRectPathMeasure;

    private Path mLine1Path;
    private Path mLine1DstPath;
    private PathMeasure mLine1PathMeasure;

    private Path mLine2Path;
    private Path mLine2DstPath;
    private PathMeasure mLine2PathMeasure;

    private Path mLine3Path;
    private Path mLine3DstPath;
    private PathMeasure mLine3PathMeasure;

    private Path mLine4Path;
    private Path mLine4DstPath;
    private PathMeasure mLine4PathMeasure;

    private Path mLine5Path;
    private Path mLine5DstPath;
    private PathMeasure mLine5PathMeasure;

    private Path mLine6Path;
    private Path mLine6DstPath;
    private PathMeasure mLine6PathMeasure;

    public PullDrawable() {
        iniPaint();
    }

    private void iniPaint() {
        // 外部边框画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xffcccccc);
        mPaint.setStrokeWidth(4f);
        // 内部矩形和线条画笔
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setColor(0xffcccccc);
        mInnerPaint.setStrokeWidth(6f);
    }

    private void initPath(Rect bounds) {
        int w = bounds.width();
        int h = bounds.height();
        int r = (int) (w * 2 / 15f); // 圆角半径
        int p = (int) (r / 3f);      // 内边距

        // 外边框(圆角矩形)
        mBorderDstPath = new Path();
        mBorderPath = new Path();

        mBorderPath.moveTo(w - p, p + r);
        RectF rectFRightTop = new RectF(w - p - 2 * r, p, w - p, 2 * r + p);
        mBorderPath.arcTo(rectFRightTop, 0, -90);
        mBorderPath.lineTo(p + r, p);
        RectF rectFLeftTop = new RectF(p, p, p + 2 * r, p + 2 * r);
        mBorderPath.arcTo(rectFLeftTop, -90, -90);
        mBorderPath.lineTo(p, h - p - r);
        RectF rectFLeftBottom = new RectF(p, h - p - 2 * r, p + 2 * r, h - p);
        mBorderPath.arcTo(rectFLeftBottom, -180, -90);
        mBorderPath.lineTo(w - p - r, h - p);
        RectF rectFRightBottom = new RectF(w - p - 2 * r, h - p - 2 * r, w - p, h - p);
        mBorderPath.arcTo(rectFRightBottom, -270, -90);
        mBorderPath.lineTo(w - p, p + r);
        mBorderPathMeasure = new PathMeasure(mBorderPath, true);
        ////////////////////////////////////////////////////////////////////////////////////////////
        float d = p;            // 内部矩形与水平方向上的三条横线的距离
        float xp = 0.8f * r;    // 水平方向相对于外边框的边距
        float yp = 1.2f * r;    // 竖直方向相对于外边框的边距

        // 内部矩形
        mRectPath = new Path();
        mRectDstPath = new Path();
        mRectPath.moveTo(w / 2f - d / 2f, p + yp);
        mRectPath.lineTo(p + xp, p + yp);
        mRectPath.lineTo(p + xp, (h - 2 * p - 2 * yp) * 0.4f + yp + p);
        mRectPath.lineTo(w / 2f - d / 2f, (h - 2 * p - 2 * yp) * 0.4f + yp + p);
        mRectPath.lineTo(w / 2f - d / 2f, p + yp);
        mRectPathMeasure = new PathMeasure(mRectPath, true);

        // 第1根线条
        mLine1Path = new Path();
        mLine1DstPath = new Path();
        mLine1Path.moveTo(w / 2f + d / 2f, p + yp);
        mLine1Path.lineTo(w - p - xp, p + yp);
        mLine1PathMeasure = new PathMeasure(mLine1Path, false);
        // 第2根线条
        mLine2Path = new Path();
        mLine2DstPath = new Path();
        mLine2Path.moveTo(w / 2f + d / 2f, (h - 2 * p - 2 * yp) * 0.2f + yp + p);
        mLine2Path.lineTo(w - p - xp, (h - 2 * p - 2 * yp) * 0.2f + yp + p);
        mLine2PathMeasure = new PathMeasure(mLine2Path, false);
        // 第3根线条
        mLine3Path = new Path();
        mLine3DstPath = new Path();
        mLine3Path.moveTo(w / 2f + d / 2f, (h - 2 * p - 2 * yp) * 0.4f + yp + p);
        mLine3Path.lineTo(w - p - xp, (h - 2 * p - 2 * yp) * 0.4f + yp + p);
        mLine3PathMeasure = new PathMeasure(mLine3Path, false);
        // 第4根线条
        mLine4Path = new Path();
        mLine4DstPath = new Path();
        mLine4Path.moveTo(p + xp, (h - 2 * p - 2 * yp) * 0.6f + yp + p);
        mLine4Path.lineTo(w - p - xp, (h - 2 * p - 2 * yp) * 0.6f + yp + p);
        mLine4PathMeasure = new PathMeasure(mLine4Path, false);
        // 第5根线条
        mLine5Path = new Path();
        mLine5DstPath = new Path();
        mLine5Path.moveTo(p + xp, (h - 2 * p - 2 * yp) * 0.8f + yp + p);
        mLine5Path.lineTo(w - p - xp, (h - 2 * p - 2 * yp) * 0.8f + yp + p);
        mLine5PathMeasure = new PathMeasure(mLine5Path, false);
        // 第6根线条
        mLine6Path = new Path();
        mLine6DstPath = new Path();
        mLine6Path.moveTo(p + xp, (h - 2 * p - 2 * yp) * 1f + yp + p);
        mLine6Path.lineTo(w - p - xp, (h - 2 * p - 2 * yp) * 1f + yp + p);
        mLine6PathMeasure = new PathMeasure(mLine6Path, false);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        initPath(bounds);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mBorderDstPath, mPaint);
        canvas.drawPath(mRectDstPath, mInnerPaint);
        canvas.drawPath(mLine1DstPath, mInnerPaint);
        canvas.drawPath(mLine2DstPath, mInnerPaint);
        canvas.drawPath(mLine3DstPath, mInnerPaint);
        canvas.drawPath(mLine4DstPath, mInnerPaint);
        canvas.drawPath(mLine5DstPath, mInnerPaint);
        canvas.drawPath(mLine6DstPath, mInnerPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    /**
     * 更新
     *
     * @param percent 更新百分比
     */
    public void update(float percent) {

        // 每次更新前重置
        mBorderDstPath.reset();
        // 截取制定百分比percent长度的路径，截取后的路径保存到mBorderDstPath中
        mBorderPathMeasure.getSegment(0, mBorderPathMeasure.getLength() * percent, mBorderDstPath, true);

        // 完整路径长度
        float rectLength = mRectPathMeasure.getLength();
        float line1Length = mLine1PathMeasure.getLength();
        float line2Length = mLine2PathMeasure.getLength();
        float line3Length = mLine3PathMeasure.getLength();
        float line4Length = mLine4PathMeasure.getLength();
        float line5Length = mLine5PathMeasure.getLength();
        float line6Length = mLine6PathMeasure.getLength();
        float totalLength = rectLength
                + line1Length
                + line2Length
                + line3Length
                + line4Length
                + line5Length
                + line6Length;
        // 百分比临界点
        float pRect = rectLength / totalLength;
        float pLine1 = line1Length / totalLength + pRect;
        float pLine2 = line2Length / totalLength + pLine1;
        float pLine3 = line3Length / totalLength + pLine2;
        float pLine4 = line4Length / totalLength + pLine3;
        float pLine5 = line5Length / totalLength + pLine4;
        float pLine6 = line6Length / totalLength + pLine5;
        // 根据指定的百分比以及临界点切取路径
        mRectDstPath.reset();
        mRectPathMeasure.getSegment(0, rectLength * (percent / pRect), mRectDstPath, true);
        mLine1DstPath.reset();
        mLine1PathMeasure.getSegment(0, line1Length * ((percent - pRect) / (pLine1 - pRect)), mLine1DstPath, true);
        mLine2DstPath.reset();
        mLine2PathMeasure.getSegment(0, line2Length * ((percent - pLine1) / (pLine2 - pLine1)), mLine2DstPath, true);
        mLine3DstPath.reset();
        mLine3PathMeasure.getSegment(0, line3Length * ((percent - pLine2) / (pLine3 - pLine2)), mLine3DstPath, true);
        mLine4DstPath.reset();
        mLine4PathMeasure.getSegment(0, line4Length * ((percent - pLine3) / (pLine4 - pLine3)), mLine4DstPath, true);
        mLine5DstPath.reset();
        mLine5PathMeasure.getSegment(0, line5Length * ((percent - pLine4) / (pLine5 - pLine4)), mLine5DstPath, true);
        mLine6DstPath.reset();
        mLine6PathMeasure.getSegment(0, line6Length * ((percent - pLine5) / (pLine6 - pLine5)), mLine6DstPath, true);
        // 重绘
        invalidateSelf();
    }

    public void clear() {
        mBorderDstPath.reset();
        mRectDstPath.reset();
        mLine1DstPath.reset();
        mLine2DstPath.reset();
        mLine3DstPath.reset();
        mLine4DstPath.reset();
        mLine5DstPath.reset();
        mLine6DstPath.reset();
        invalidateSelf();
    }
}
