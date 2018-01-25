package com.centrahub.focus.sourav.sovcanvascomponents.sovrounddetailsitem;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by sourav on 24-Nov-2018.
 */
/*So far, the earliest finds of modern Homo sapiens skeletons come from Africa. They date to nearly 200,000 years ago on that continent. They appear in Southwest Asia around 100,000 years ago and elsewhere in the Old World by 60,000-40,000 years ago.*/

public class SovRoundedDetailsItem extends View /*implements View.OnClickListener*/ {
    private float iLeftmargin = 10;
    private RectF titleRectTop;
    private Paint backgroundTopPaint;
    private Paint backgroundDetailsPaint;
    private float dateTextSize;
    private float ButtomDetailsSize;
    private float selectTextSize;
    private TextPaint titalpaint;
    TextPaint detailPaint = new TextPaint();

    private int detailPaintColor;
    private String detailsText;

    private int selectBackColor = Color.BLUE;
    int text_height = 0;
    int text_width = 0;
    Rect detailPaintBound = new Rect();

    public SovRoundedDetailsItem(Context context) {
        this(context, null);
    }

    public SovRoundedDetailsItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SovRoundedDetailsItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private int itemWidth;
    private int itemHeight;
    private int rows = 7;

    private Paint bottomDetailPaint;


    private void init(Context context) {
//        setOnClickListener(this);
        initAttrs();
        initTools(context);


    }

    private void initAttrs() {
        dateTextSize = 15;
//        selected day color
        selectTextSize = dateTextSize;

//        initDates();

    }

    private void initTools(Context context) {
        //color selectio
        detailPaintColor = Color.DKGRAY;
        //top detailsText

        titleRectTop = new RectF();
        backgroundTopPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundTopPaint.setColor(Color.parseColor("#703cc4"));

        backgroundDetailsPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundDetailsPaint.setColor(getResources().getColor(android.R.color.white));

        bottomDetailPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bottomDetailPaint.setTextSize(sp2px(dateTextSize));//dateTextSize * 1.2f
        bottomDetailPaint.setColor(getResources().getColor(android.R.color.darker_gray));
//        backgroundTopPaint.setColor( Color.parseColor("#FF963DB9"));
        titalpaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        titalpaint.setTextSize(sp2px(dateTextSize * 1.2f));//dateTextSize * 1.2f
        titalpaint.setColor(ContextCompat.getColor(context, android.R.color.white));


        detailPaint.setTypeface(Typeface.DEFAULT);// your preference here
        detailPaint.setTextSize(sp2px(18));// have this the same as your detailsText size
        detailPaint.setColor(detailPaintColor);
        detailsText = "So far, iest finds of modern Homo sapiens skeletons come from Africa. They date to iest finds of modern Homo sapiens skeletons come from Africa.\n\n They date to iest finds of modern Homo sapiens skeletons come from Africa. They date to iest finds of modern Homo sapiens skeletons come from Africa. They date to iest finds of modern Homo sapiens skeletons come from Africa. They date to iest finds of modern Homo sapiens skeletons come from Africa. They date to iest finds of modern Homo sapiens skeletons come from Africa. They date to the earliest finds of modern Homo sapiens skeletons come from Africa. They date to nearly 200,000 years ago on that continent.the earliest finds of modern Homo on that continent.";
        detailPaint.getTextBounds(detailsText, 0, detailsText.length(), detailPaintBound);

        text_height = detailPaintBound.height();
        text_width = detailPaintBound.width();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
//        int height=MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
        itemWidth = width / rows;
        itemHeight = itemWidth;
//        setMeasuredDimension(itemWidth * rows, 300);
        final StaticLayout measuringTextLayout = new StaticLayout(detailsText, detailPaint, (int) Math.abs((itemWidth * rows - iLeftmargin) - (itemHeight / 7 + iLeftmargin)),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        final int totalLineCount = measuringTextLayout.getLineCount()+2;
//        totalLineCount+=2;
        setMeasuredDimension(itemWidth * rows, itemHeight+detailPaintBound.height()*totalLineCount);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDetailsTop(canvas);
        drawTitleTop(canvas);
        drawBottomDetails(canvas);
    }

    private void drawBottomDetails(Canvas canvas) {
        String sMonthsYear = "23 Jan 2018  15:00 AM";
        float textWidth = bottomDetailPaint.measureText(sMonthsYear);
        canvas.drawText(sMonthsYear, canvas.getWidth() - textWidth - itemWidth / 4, canvas.getHeight() - iLeftmargin, bottomDetailPaint);
    }

    private void drawDetailsTop(Canvas canvas) {
        titleRectTop.set(iLeftmargin, 0, itemWidth * rows - iLeftmargin, canvas.getHeight());
        canvas.drawRoundRect(titleRectTop, itemHeight / 7, itemHeight / 7, backgroundDetailsPaint);
//        canvas.drawText(detailsText, itemHeight / 7 + iLeftmargin, canvas.getHeight()/2, detailPaint);
        drawMultiLineEllipsizedText(canvas, detailPaint, itemHeight / 7 + iLeftmargin, itemHeight / 2 + itemHeight / 12 + iLeftmargin, itemWidth * rows - iLeftmargin, canvas.getHeight() - iLeftmargin * 2, detailsText);


    }

    private void drawTitleTop(Canvas canvas) {
//        backgroundRect.set(0, 0, itemWidth * rows, itemHeight / 2);
        titleRectTop.set(iLeftmargin, 0, itemWidth * rows - iLeftmargin, itemHeight / 2);
        canvas.drawRoundRect(titleRectTop, itemHeight / 7, itemHeight / 7, backgroundTopPaint);
        titleRectTop.set(iLeftmargin, itemHeight / 7, itemWidth * rows - iLeftmargin, itemHeight / 2 + itemHeight / 14);
        canvas.drawRect(titleRectTop, backgroundTopPaint);
        String sMonthsYear = "Homologies Details_Details_Details";
        String truncatedText = sMonthsYear.substring(0, Math.max(0, 25));

        if (truncatedText.length() < sMonthsYear.length()) {
            truncatedText = truncatedText.substring(0, Math.max(0, truncatedText.length() - 3));
            truncatedText += "...";
        }
        canvas.drawText(truncatedText, itemHeight / 7 + iLeftmargin, itemHeight / 2.5f, titalpaint);
    }

    public static void drawMultiLineEllipsizedText(final Canvas _canvas, final TextPaint _textPaint, final float _left,
                                                   final float _top, final float _right, final float _bottom, final String _text) {
        final float height = _bottom - _top;

        final StaticLayout measuringTextLayout = new StaticLayout(_text, _textPaint, (int) Math.abs(_right - _left),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        int line = 0;
        final int totalLineCount = measuringTextLayout.getLineCount();
        for (line = 0; line < totalLineCount; line++) {
            final int lineBottom = measuringTextLayout.getLineBottom(line);
            if (lineBottom > height) {
                break;
            }
        }
        line--;

        if (line < 0) {
            return;
        }

        int lineEnd;
        try {
            lineEnd = measuringTextLayout.getLineEnd(line);
        } catch (Throwable t) {
            lineEnd = _text.length();
        }
        String truncatedText = _text.substring(0, Math.max(0, lineEnd));

        if (truncatedText.length() < 3) {
            return;
        }

        if (truncatedText.length() < _text.length()) {
            truncatedText = truncatedText.substring(0, Math.max(0, truncatedText.length() - 3));
            truncatedText += "...";
        }
        final StaticLayout drawingTextLayout = new StaticLayout(truncatedText, _textPaint, (int) Math.abs(_right
                - _left), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        _canvas.save();
        _canvas.translate(_left, _top);
        drawingTextLayout.draw(_canvas);
        _canvas.restore();
    }

    private PointF startPoint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startPoint = new PointF(event.getX(), event.getY());
//            Log.i(TAG,startPoint.toString());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();
//            Log.i(TAG,x+","+y);
            if (Math.abs(startPoint.x - x) < 20 && Math.abs(startPoint.y - y) < 20) {
//                for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    if (x > itemWidth * j && x < itemWidth * (j + 1) && y > itemHeight * (0.6f) && y < itemHeight * (1.5)) {
//                        preSelectDate = SELECTED_DATE;
//                        s7dates[j]="3";
//                        CLCalendarView.MY_SELECTED_DATE = weekDates[j];
                        if (onItemSelectListener != null) {
//                            onItemSelectListener.onSelectDate(CLCalendarWeekUtil.getNewCalender(year, month, SELECTED_DATE), weekDates[j]);
                        }
                        invalidate();
                    }
//                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }


//    private void initDates(int year, int month, int day) {
//        CLCalendarWeekDTO calenderBean = CLCalendarWeekUtil.getNewCalender(year, month, day);
//        weekDates = calenderBean.getBeanDate();
//        for (int i = 0; i < 7; i++) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(weekDates[i]);
//            int tempDay = cal.get(Calendar.DAY_OF_MONTH);
//            int tempMonth = cal.get(Calendar.MONTH);
//            int tempyear = cal.get(Calendar.YEAR);
//            s7dates[i] = tempDay;
//
//
//            Calendar currentCalender = Calendar.getInstance();
//            int currentdate = currentCalender.get(Calendar.DATE);
//            int currentmonth = currentCalender.get(Calendar.MONTH);
//            int currentyear = currentCalender.get(Calendar.YEAR);
//
//            if (tempDay == currentdate && tempMonth == currentmonth && tempyear == currentyear) {
//                if (onItemSelectListener != null) {
//                    onItemSelectListener.onSelectDate(CLCalendarWeekUtil.getNewCalender(year, month, SELECTED_DATE), weekDates[i]);
//                    Toast.makeText(getContext(), "" + tempDay, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//
//        setSelectDate(-1);
//    }


    public int sp2px(float sp) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (sp * scale + 0.5f);
    }


    private OnItemSelectListener onItemSelectListener;

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }


    public interface OnItemSelectListener {
//        void onSelectDate(CLCalendarWeekDTO calenderBean, Date date);

    }

}
