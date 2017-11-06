package hu.ait.android.recylerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import hu.ait.android.recylerviewdemo.MainActivity;
import hu.ait.android.recylerviewdemo.R;
import hu.ait.android.recylerviewdemo.data.Todo;
import hu.ait.android.recylerviewdemo.touch.TodoTouchHelperAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>
  implements TodoTouchHelperAdapter {

    private List<Todo> todoList;

    private Context context;
    private Realm realmTodo;


    public TodoRecyclerAdapter(Context context,
                               Realm realmTodo) {
        this.context = context;
        this.realmTodo = realmTodo;

        todoList = new ArrayList<Todo>();

        RealmResults<Todo> todoResult =
                realmTodo.where(Todo.class).findAll().sort("todoTitle",
                Sort.ASCENDING);

        for (Todo todo : todoResult) {
            todoList.add(todo);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_row, parent, false);
        return new ViewHolder(todoRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Todo todoData = todoList.get(position);

        holder.tvDate.setText(todoData.getCreateDate());
        holder.cbTodo.setText(todoData.getTodoTitle());
        holder.cbTodo.setChecked(todoData.isDone());

        holder.cbTodo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realmTodo.beginTransaction();

                todoData.setDone(isChecked);

                realmTodo.commitTransaction();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).openEditActivity(
                        holder.getAdapterPosition(),
                        todoList.get(holder.getAdapterPosition()).getTodoID()
                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        Todo todoToDelete = todoList.get(position);
        realmTodo.beginTransaction();
        todoToDelete.deleteFromRealm();
        realmTodo.commitTransaction();


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

    public void addTodo(String todoTitle) {
        realmTodo.beginTransaction();

        Todo newTodo = realmTodo.createObject(Todo.class, UUID.randomUUID().toString());
        newTodo.setTodoTitle(todoTitle);
        newTodo.setCreateDate(
                new Date(System.currentTimeMillis()).toString());
        newTodo.setDone(false);

        realmTodo.commitTransaction();

        todoList.add(0, newTodo);
        notifyItemInserted(0);
    }

    public void updateTodo(String todoIDThatWasEdited, int positionToEdit) {
        Todo todo = realmTodo.where(Todo.class).
                equalTo("todoID", todoIDThatWasEdited).
                findFirst();

        todoList.set(positionToEdit, todo);

        notifyItemChanged(positionToEdit);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate;
        private CheckBox cbTodo;
        private Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            cbTodo = itemView.findViewById(R.id.cbTodo);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }


}
