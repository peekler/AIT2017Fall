package hu.ait.android.recylerviewdemo.touch;

public interface TodoTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);

}
