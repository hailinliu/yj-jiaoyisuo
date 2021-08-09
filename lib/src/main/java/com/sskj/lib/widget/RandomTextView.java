package com.sskj.lib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.base.App;

import java.util.ArrayList;

public class RandomTextView extends AppCompatTextView {
    public static final int FIRSTF_FIRST = 0;
    public static final int FIRSTF_LAST = 1;
    public static final int ALL = 2;
    public static final int USER = 3;
    private int pianyiliangTpye;
    private int maxLine = 10;
    private int numLength = 0;
    private String text;
    private int[] pianyilianglist;
    private int[] pianyiliangSum;
    private int[] overLine;
    private Paint p;
    private boolean firstIn = true;
    private boolean auto = true;
    private ArrayList<String> arrayListText;
    private float f0;
    private int baseline;
    private int measuredHeight;
    private static final Handler handler = new Handler();
    private final Runnable task = new Runnable() {
        public void run() {
            if (RandomTextView.this.auto) {
                RandomTextView.handler.postDelayed(this, 35L);

                for (int j = 0; j < RandomTextView.this.numLength; ++j) {
                    int[] var10000 = RandomTextView.this.pianyiliangSum;
                    var10000[j] -= RandomTextView.this.pianyilianglist[j];
                }

                RandomTextView.this.invalidate();
            }

        }
    };
    private float start;
    private float[] widths;
    private float[] widthAlls;
    private float dot = RudenessScreenHelper.pt2px(App.INSTANCE, 0);

    public RandomTextView(Context context) {
        super(context);
    }

    public RandomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RandomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPianyilian(int pianyiliangTpye) {
        this.text = this.getText().toString();
        this.pianyiliangSum = new int[this.text.length()];
        this.overLine = new int[this.text.length()];
        this.pianyilianglist = new int[this.text.length()];
        int i;
        switch (pianyiliangTpye) {
            case 0:
                for (i = 0; i < this.text.length(); ++i) {
                    this.pianyilianglist[i] = 20 - i;
                }

                return;
            case 1:
                for (i = 0; i < this.text.length(); ++i) {
                    this.pianyilianglist[i] = 15 + i;
                }

                return;
            case 2:
                for (i = 0; i < this.text.length(); ++i) {
                    this.pianyilianglist[i] = 15;
                }
        }

    }

    public void setPianyilian(int[] list) {
        this.text = this.getText().toString();
        this.pianyiliangSum = new int[list.length];
        this.overLine = new int[list.length];
        this.pianyilianglist = list;
    }

    protected void onDraw(Canvas canvas) {
//        if (this.firstIn) {
//            this.firstIn = false;
        if (!auto) {
            super.onDraw(canvas);
            return;
        }
        this.p = this.getPaint();
        this.p.setColor(getCurrentTextColor());
        this.p.setTextSize(getTextSize());
        FontMetricsInt fontMetrics = this.p.getFontMetricsInt();
        this.measuredHeight = this.getMeasuredHeight();
        Log.d("EEEEEEE", "onDraw: " + this.measuredHeight);
        this.baseline = (this.measuredHeight - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        widths = new float[getText().toString().length()];
        this.p.getTextWidths(getText().toString(), widths);
        int lenth = 0;
        widthAlls = new float[getText().toString().length()];
        for (int i = 0; i < widths.length; i++) {
            widthAlls[i] = lenth;
            lenth += widths[i];
        }
        start = (getWidth() - lenth) / 2;


        if (widths.length > 0) {
            this.f0 = widths[0];
        } else {
            this.f0 = 0;
        }
//            this.invalidate();
//        }

        this.drawNumber(canvas);
    }

    private void drawNumber(Canvas canvas) {
        try {

            for (int j = 0; j < this.numLength; ++j) {
                for (int i = 1; i < this.maxLine; ++i) {
                    if (i == this.maxLine - 1 && i * this.baseline + this.pianyiliangSum[j] <= this.baseline) {
                        this.pianyilianglist[j] = 0;
                        this.overLine[j] = 1;
                        int auto = 0;

                        for (int k = 0; k < this.numLength; ++k) {
                            auto += this.overLine[k];
                        }

                        if (auto == this.numLength * 2 - 1) {
                            this.auto = false;
                            handler.removeCallbacks(this.task);
                            this.invalidate();
                        }
                    }

                    if (this.overLine[j] == 0) {
                        try {

                            this.drawText(canvas, this.setBack(Integer.valueOf(arrayListText.get(j)), this.maxLine - i - 1) + "", start + widthAlls[j], (float) (i * this.baseline + this.pianyiliangSum[j]), this.p);
                        } catch (Exception e) {
                            this.drawText(canvas, this.arrayListText.get(j), start + widthAlls[j] + dot, (float) (i * this.baseline + this.pianyiliangSum[j]), this.p);

                        }
                    } else if (this.overLine[j] == 1) {
                        ++this.overLine[j];
                        if (this.arrayListText.get(j).equals(".")) {

                            this.drawText(canvas, this.arrayListText.get(j) + "", start + widthAlls[j] + dot, (float) this.baseline, this.p);
                        } else {

                            this.drawText(canvas, this.arrayListText.get(j) + "", start + widthAlls[j], (float) this.baseline, this.p);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    private int setBack(int c, int back) {
        if (back == 0) {
            return c;
        } else {
            back %= 10;
            int re = c - back;
            if (re < 0) {
                re += 10;
            }

            return re;
        }
    }

    public void start() {
        this.text = this.getText().toString();
        this.numLength = this.text.length();
        this.arrayListText = this.getList(this.text);
        handler.postDelayed(this.task, 17L);
        this.auto = true;
    }

    public void setMaxLine(int l) {
        this.maxLine = l;
    }

    private ArrayList<String> getList(String s) {
        ArrayList<String> arrayList = new ArrayList();

        for (int i = 0; i < s.length(); ++i) {
            String ss = s.substring(i, i + 1);

            arrayList.add(ss);
        }

        return arrayList;
    }

    public void destroy() {
        this.auto = false;
        handler.removeCallbacks(this.task);
    }

    private void drawText(Canvas mCanvas, String text, float x, float y, Paint p) {
        if (y >= (float) (-this.measuredHeight) && y <= (float) (2 * this.measuredHeight)) {
            mCanvas.drawText(text + "", x, y, p);
        }
    }
}
