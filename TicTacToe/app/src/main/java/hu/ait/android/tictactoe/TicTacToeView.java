package hu.ait.android.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeView extends View {

    private Paint paintBg;
    private Paint paintLine;

    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        drawGameArea(canvas);


    }

    private void drawGameArea(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        // vertical lines
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(), paintLine);
        canvas.drawLine(2 * (getWidth() / 3), 0, 2 * (getWidth() / 3), getHeight(), paintLine);
        // horizontal lines
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3, paintLine);
        canvas.drawLine(0, 2 * (getHeight() / 3), getWidth(), 2 * (getHeight() / 3), paintLine);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            invalidate();

            return true;
        }


        return super.onTouchEvent(event);
    }

    public void clearBoard() {

        invalidate();
    }
}
