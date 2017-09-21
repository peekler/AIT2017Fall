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

        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        // draw board
        drawGameArea(canvas);

        // draw players
        drawPlayers(canvas);
    }

    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, j) ==
                        TicTacToeModel.CIRCLE) {
                    // draw circle
                    int centerX = i*getWidth() / 3 + getWidth() / 6;
                    int centerY = j*getHeight() / 3 + getHeight() / 6;

                    canvas.drawCircle(centerX, centerY,
                            getWidth() / 6 -2, paintLine);

                } else if (TicTacToeModel.getInstance().getFieldContent(i, j) ==
                        TicTacToeModel.CROSS) {
                    // draw cross

                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3, paintLine);
                }
            }
        }
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
            int tX = ((int) event.getX()) / (getWidth() / 3);
            int tY = ((int) event.getY()) / (getHeight() / 3);

            if (TicTacToeModel.getInstance().getFieldContent(tX, tY) ==
                    TicTacToeModel.EMPTY) {
                TicTacToeModel.getInstance().setFieldContent(tX, tY,
                        TicTacToeModel.getInstance().getNextPlayer());
                TicTacToeModel.getInstance().switchNextPlayer();

                String nextPlayer = "O";
                if (TicTacToeModel.getInstance().getNextPlayer() ==
                        TicTacToeModel.CROSS) {
                    nextPlayer = "X";
                }
                ((MainActivity)getContext()).showNextPlayer(nextPlayer);

                invalidate();
            }

            return true;
        }

        return super.onTouchEvent(event);
    }

    public void clearBoard() {
        TicTacToeModel.getInstance().resetGame();

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

}
