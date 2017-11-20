package hu.autsoft.android.aitdiscussions;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.autsoft.android.aitdiscussions.data.Post;

public class CreatePostActivity extends AppCompatActivity {

    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.etBody)
    EditText etBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSend)
    void sendClick() {

        String newKey = FirebaseDatabase.getInstance().
                getReference().child("posts").push().getKey();

        Post newPost = new Post(
                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                etTitle.getText().toString(),
                etBody.getText().toString()
        );

        FirebaseDatabase.getInstance().getReference().
                child("posts").child(newKey).setValue(newPost).
          addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreatePostActivity.this, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreatePostActivity.this,
                            "Error: "+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
