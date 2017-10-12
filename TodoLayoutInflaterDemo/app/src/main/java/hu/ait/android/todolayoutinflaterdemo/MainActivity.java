package hu.ait.android.todolayoutinflaterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTodo)
    EditText etTodo;
    @BindView(R.id.cbImportant)
    CheckBox cbImportant;
    @BindView(R.id.layoutContent)
    LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAdd)
    public void addPressed(Button btnAdd) {
        final View todoRow = getLayoutInflater().inflate(
                R.layout.layout_todo_row,
                null, false);

        TextView tvTodo = (TextView) todoRow.findViewById(R.id.tvTodo);
        tvTodo.setText(etTodo.getText().toString());

        ImageView ivTodo = todoRow.findViewById(R.id.ivTodo);
        if (cbImportant.isChecked()) {
            ivTodo.setImageResource(R.mipmap.ic_launcher_round);
        }


        Button btnDelete = (Button) todoRow.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutContent.removeView(todoRow);

            }
        });

        layoutContent.addView(todoRow, 0);
    }
}
