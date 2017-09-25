package hu.ait.android.tictactoe.data;

public class Field {

    private int x;
    private int y;
    private boolean isMine;
    private boolean hasUserClickHere;
    private boolean isFlag;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isHasUserClickHere() {
        return hasUserClickHere;
    }

    public void setHasUserClickHere(boolean hasUserClickHere) {
        this.hasUserClickHere = hasUserClickHere;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
