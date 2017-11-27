package hu.autsoft.android.aitdiscussions.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import hu.autsoft.android.aitdiscussions.R;
import hu.autsoft.android.aitdiscussions.data.Post;

/**
 * Created by Peter on 2017. 11. 23..
 */

public class PostsAdapter
        extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> postList;
    private List<String> postKeys;
    private String uId;
    private int lastPosition = -1;
    private DatabaseReference postsRef;

    public PostsAdapter(Context context, String uId) {
        this.context = context;
        this.uId = uId;

        postList = new ArrayList<Post>();
        postKeys = new ArrayList<String>();

        postsRef = FirebaseDatabase.getInstance().getReference();
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_posts, parent, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.tvAuthor.setText(post.getAuthor());
        holder.tvBody.setText(post.getBody());
        holder.tvTitle.setText(post.getTitle());

        if (post.getImgUrl() != null) {
            Glide.with(context).load(post.getImgUrl()).into(holder.ivPostImg);
            holder.ivPostImg.setVisibility(View.VISIBLE);
        } else {
            holder.ivPostImg.setVisibility(View.GONE);
        }

        if (uId.equals(post.getUid())) {
            holder.btnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.btnDelete.setVisibility(View.INVISIBLE);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePost(holder.getAdapterPosition());
            }
        });
    }


    public void removePost(int index) {
        //postsRef = FirebaseDatabase.getInstance().getReference();

        postsRef.child("posts").child(postKeys.get(index)).removeValue();

        postList.remove(index);
        postKeys.remove(index);
        notifyItemRemoved(index);
    }

    public void removePostByKey(String key) {
        int index = postKeys.indexOf(key);
        if (index != -1) {
            postList.remove(index);
            postKeys.remove(index);
            notifyItemRemoved(index);
        }
    }



    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPost(Post post, String key) {
        postList.add(post);
        postKeys.add(key);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvAuthor;
        public TextView tvTitle;
        public TextView tvBody;
        public Button btnDelete;
        public ImageView ivPostImg;


        public ViewHolder(View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ivPostImg = itemView.findViewById(R.id.ivPostImg);
        }
    }


}
