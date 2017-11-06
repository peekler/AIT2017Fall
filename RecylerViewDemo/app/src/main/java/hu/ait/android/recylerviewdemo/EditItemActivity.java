package hu.ait.android.recylerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import hu.ait.android.recylerviewdemo.data.Todo;

public class EditItemActivity extends AppCompatActivity {

    private EditText etTodoText;
    private CheckBox cbTodoDone;

    private Todo todoToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        if (getIntent().hasExtra(MainActivity.KEY_TODO_ID)) {
            String todoId = getIntent().getStringExtra(MainActivity.KEY_TODO_ID);

            todoToEdit = ((TodoApplication)getApplication()).getRealmTodo().where(Todo.class).
                    equalTo("todoID", todoId).findFirst();
        }

        etTodoText = findViewById(R.id.etTodoText);
        cbTodoDone = findViewById(R.id.cbTodoDone);
        Button btnSave = findViewById(R.id.btnSave);

        if (todoToEdit != null) {
            etTodoText.setText(todoToEdit.getTodoTitle());
            cbTodoDone.setChecked(todoToEdit.isDone());
        }



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TodoApplication)getApplication()).getRealmTodo().beginTransaction();
                todoToEdit.setTodoTitle(etTodoText.getText().toString());
                todoToEdit.setDone(cbTodoDone.isChecked());
                ((TodoApplication)getApplication()).getRealmTodo().commitTransaction();

                Intent intentResult = new Intent();
                intentResult.putExtra(MainActivity.KEY_TODO_ID, todoToEdit.getTodoID());
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }
}
