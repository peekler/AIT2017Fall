package hu.ait.android.tictactoe.data;

public class TicTacToeModel {

    private static TicTacToeModel ticTacToeModel = null;

    private TicTacToeModel(){
    }

    public static TicTacToeModel getInstance() {
        if (ticTacToeModel == null) {
            ticTacToeModel = new TicTacToeModel();
        }

        return ticTacToeModel;
    }

    public static final int EMPTY = 0;
    public static final int CIRCLE = 1;
    public static final int CROSS = 2;

    private int[][] model = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private int nextPlayer = CIRCLE;

    public void switchNextPlayer() {
        nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;

        /*if (nextPlayer == CIRCLE) {
            nextPlayer = CROSS;
        } else {
            nextPlayer = CIRCLE;
        }*/
    }

    public int getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void setFieldContent(int x, int y, int content) {
        model[x][y] = content;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model[i][j] = EMPTY;
            }
        }

        nextPlayer = CIRCLE;
    }
}
