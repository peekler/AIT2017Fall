package hu.ait.android.recylerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.recylerviewdemo.R;
import hu.ait.android.recylerviewdemo.data.Todo;
import hu.ait.android.recylerviewdemo.touch.TodoTouchHelperAdapter;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>
  implements TodoTouchHelperAdapter {

    private List<Todo> todoList;

    public TodoRecyclerAdapter() {
        todoList = new ArrayList<Todo>();
        for (int i = 0; i < 20; i++) {
            todoList.add(new Todo("Todo"+i, "16. 10. 2017", false));
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_row, parent, false);
        return new ViewHolder(todoRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Todo todoData = todoList.get(position);

        holder.tvDate.setText(todoData.getCreateDate());
        holder.cbTodo.setText(todoData.getTodoTitle());
        holder.cbTodo.setChecked(todoData.isDone());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        todoList.remove(position);
        //notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todoList, i, i - 1);
            }
        }

        //notifyDataSetChanged();
        notifyItemMoved(fromPosition, toPosition);
    }

    public void addTodo(Todo todo) {
        todoList.add(todo);
        notifyDataSetChanged();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate;
        private CheckBox cbTodo;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            cbTodo = itemView.findViewById(R.id.cbTodo);
        }
    }


}
