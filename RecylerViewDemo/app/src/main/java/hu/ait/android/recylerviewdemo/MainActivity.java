package hu.ait.android.recylerviewdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import hu.ait.android.recylerviewdemo.adapter.TodoRecyclerAdapter;
import hu.ait.android.recylerviewdemo.data.Todo;
import hu.ait.android.recylerviewdemo.touch.TodoItemTouchHelperCallback;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TODO_ID = "KEY_TODO_ID";
    public static final int REQUEST_CODE_EDIT = 1001;
    private TodoRecyclerAdapter adapter;

    private int positionToEdit = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                showAddTodoDialog();


            }
        });


        ((TodoApplication)getApplication()).openRealm();

        RecyclerView recyclerViewTodo = (RecyclerView) findViewById(R.id.recyclerTodo);
        adapter = new TodoRecyclerAdapter(this,
                ((TodoApplication)getApplication()).getRealmTodo());

        recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTodo.setHasFixedSize(true);

        ItemTouchHelper.Callback callback = new TodoItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewTodo);

        recyclerViewTodo.setAdapter(adapter);


        new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(findViewById(R.id.fab))
                .setPrimaryText("New todo")
                .setSecondaryText("Click here for new todo")
                .show();
    }

    private void showAddTodoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Title");
        // Set up the input
        final EditText input = new EditText(MainActivity.this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.addTodo(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    @Override
    protected void onDestroy() {
        ((TodoApplication)getApplication()).closeRealm();

        super.onDestroy();
    }

    public void openEditActivity(int adapterPosition, String todoID) {
        positionToEdit = adapterPosition;

        Intent intentEdit = new Intent(this, EditItemActivity.class);
        intentEdit.putExtra(KEY_TODO_ID, todoID);
        startActivityForResult(intentEdit, REQUEST_CODE_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            String todoIDThatWasEdited = data.getStringExtra(KEY_TODO_ID);

            adapter.updateTodo(todoIDThatWasEdited, positionToEdit);

        } else {
            Toast.makeText(this, "Edit was cancelled", Toast.LENGTH_LONG).show();
        }
    }
}
