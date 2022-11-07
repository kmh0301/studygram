package com.example.studygram.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studygram.Adapter.PostAdapter;
import com.example.studygram.Post;
import com.example.studygram.R;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter <PostAdapter.MyViewHolder>{
    Context context;
    private List<Post> PostList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.PostList = postList;
    }

    @NonNull
    @Override
    //將Item 拿到
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //infalter作用可以將layout變成view對象
        View view = LayoutInflater.from(context).inflate(R.layout.post_item,parent,false);//view拿到
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //顯示item時要做的事情
        //Step1: 先拿到Post
        Post post = PostList.get(position);
        //Step2: 設置item 從對應post(object) 獲得對應的數據
        holder.usernameText.setText(post.getUsername());
        holder.postdateText.setText(post.getPostdate());
        holder.likecountText.setText(post.getLikecount());
        holder.postContentText.setText(post.getPostContent());
        holder.userIconImage.setImageResource(post.getUsericon_id());
        holder.userpostImage.setImageResource(post.getPostImage_id());
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

    //用來裝部件的類
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        TextView postdateText;
        TextView likecountText;
        TextView postContentText;
        ImageView userIconImage;
        ImageView userpostImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.usernameText = itemView.findViewById(R.id.tv_username_1);
            this.postdateText = itemView.findViewById(R.id.tv_postdate_1);
            this.likecountText = itemView.findViewById(R.id.tv_likecount_1);
            this.postContentText = itemView.findViewById(R.id.tv_postcontent_1);
            this.userIconImage = itemView.findViewById(R.id.iv_usericon_1);
            this.userpostImage = itemView.findViewById(R.id.iv_postiImage_1);
        }
    }


}

