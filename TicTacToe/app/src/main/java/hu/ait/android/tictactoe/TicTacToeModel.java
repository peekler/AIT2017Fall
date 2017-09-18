package hu.ait.android.tictactoe;

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

}
