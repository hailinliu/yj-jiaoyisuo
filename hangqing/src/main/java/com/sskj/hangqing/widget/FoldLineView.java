package com.sskj.hangqing.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.base.App;
import com.sskj.hangqing.R;

import java.util.List;

public class FoldLineView extends View {

    private int timeNum = 5;
    private float dataNum = 119f;
    private int width;
    private int height;
    private List<IFoldLineData> data;
    private int bgColor;
    private float section;
    private Paint timeTextPaint;
    private int maxPos;
    private int minPos;
    private double sectionPrice;
    private double distancePrice;
    private Double minPrice;
    private Paint linePaint;
    private Paint shaderPaint;
    private UpDown upDown = UpDown.UP;

    public FoldLineView(Context context) {
        super(context);
        init();
    }

    public FoldLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FoldLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bgColor = ContextCompat.getColor(App.INSTANCE, R.color.hangBg);
        timeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        timeTextPaint.setColor(ContextCompat.getColor(App.INSTANCE, R.color.hangTextHint));
        timeTextPaint.setTextSize(RudenessScreenHelper.pt2px(App.INSTANCE, 20));
        timeTextPaint.setTextAlign(Paint.Align.CENTER);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(ContextCompat.getColor(App.INSTANCE, R.color.hangGreen));
        linePaint.setStrokeWidth(3);

        shaderPaint = new Paint();
        Shader mShader = new LinearGradient(0.0F, 0.0F, 0.0F, 1000, new int[]{Color.parseColor("#601ED189"), Color.parseColor("#301ED189")}, new float[]{0.1F, 0.3F}, Shader.TileMode.REPEAT);
        shaderPaint.setShader(mShader);
        shaderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = measureWidthOrHeight(widthMeasureSpec);
        height = measureWidthOrHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);

    }

    private int measureWidthOrHeight(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) {
            result = size;
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            result = 50;
            result = Math.min(result, size);
        }
        return result;
    }

    public void setData(List<IFoldLineData> data) {
        this.data = data;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(bgColor);
        section = width / dataNum;
        if (data == null || data.isEmpty()) {
            return;
        }
        drawTime(canvas);
        drawFoldLine(canvas);
        drawShader(canvas);


    }

    private void drawShader(Canvas canvas) {
        Path path = new Path();
        path.reset();
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                path.moveTo(i, height);
                path.lineTo(i, getDrawY(data.get(i).getPrice()));
            } else if (i + 1 == data.size()) {
                path.lineTo((i) * section, getDrawY(data.get(i).getPrice()));

                path.lineTo(i * section, height);
                path.close();
            } else {
                path.lineTo((i) * section, getDrawY(data.get(i).getPrice()));
            }

        }
        canvas.drawPath(path, shaderPaint);
    }

    private void drawFoldLine(Canvas canvas) {
        getMaxMinPrice();
        for (int i = 0; i < data.size(); i++) {
            if (i + 1 == data.size()) {
                canvas.drawCircle(section * i, getDrawY(data.get(i).getPrice()), 5, linePaint);
            } else {
                canvas.drawLine(section * i, getDrawY(data.get(i).getPrice()), section * (i + 1), getDrawY(data.get(i + 1).getPrice()), linePaint);

            }
        }
    }

    private float getDrawY(String price) {
        return (float) (height-sectionPrice*((Double.valueOf(price) - minPrice)));
    }

    private void getMaxMinPrice() {
        Double max = Double.MIN_VALUE;
        Double min = Double.MAX_VALUE;
        maxPos = 0;
        minPos = 0;
        for (int i = 0; i < data.size(); i++) {
            Double price = Double.valueOf(data.get(i).getPrice());
            if (price > max) {
                maxPos = i;
                max = price;
            }
            if (price < min) {
                minPos = i;
                min = price;
            }
        }
        minPrice = Double.valueOf(data.get(minPos).getPrice());
        distancePrice = Double.valueOf(data.get(maxPos).getPrice()) - minPrice;
        sectionPrice = (height ) / distancePrice;
    }

    public void setUpDown(UpDown upDown) {

        if (this.upDown == upDown) {
            return;
        }
        this.upDown = upDown;
        if (upDown == UpDown.UP) {
            linePaint.setColor(ContextCompat.getColor(App.INSTANCE, R.color.hangGreen));
            Shader mShader = new LinearGradient(0.0F, 0.0F, 0.0F, 1000, new int[]{Color.parseColor("#601ED189"), Color.parseColor("#301ED189")}, new float[]{0.1F, 0.3F}, Shader.TileMode.REPEAT);
            shaderPaint.setShader(mShader);
//            linePaint.setShadowLayer(10, 15, 15, ContextCompat.getColor(App.INSTANCE, R.color.hangGreen));

        } else {
            linePaint.setColor(ContextCompat.getColor(App.INSTANCE, R.color.hangRed));
            Shader mShader = new LinearGradient(0.0F, 0.0F, 0.0F, 1000, new int[]{Color.parseColor("#60FF5755"), Color.parseColor("#30FF5755")}, new float[]{0.1F, 0.3F}, Shader.TileMode.REPEAT);
            shaderPaint.setShader(mShader);
//            linePaint.setShadowLayer(10, 15, 15, ContextCompat.getColor(App.INSTANCE, R.color.hangRed));

        }
        invalidate();

    }

    private void drawTime(Canvas canvas) {
        for (int i = 0; i < timeNum; i++) {
            int timeDataPos = (int) (dataNum / (timeNum + 1) * (i + 1)) - 1;
            if (timeDataPos < data.size()) {
                String time = data.get(timeDataPos).getTime();
                canvas.drawText(time, section * (timeDataPos + 1), height - 30, timeTextPaint);
            }
        }
    }

    public enum UpDown {
        UP, DOWN
    }
}
