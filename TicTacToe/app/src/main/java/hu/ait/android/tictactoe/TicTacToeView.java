package hu.ait.android.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TicTacToeView extends View {

    Paint paintBg;
    Paint paintLine;

    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getWidth(),getHeight(),paintBg);

        canvas.drawLine(0,0,getWidth(),getHeight(), paintLine);
    }
}
