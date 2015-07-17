package forum.org.hipda.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import forum.org.hipda.R;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.view.transform.GlideCircleTransform;

/**
 * Created by slomka.jin on 2015/7/7.
 */
public class ThreadsAdapter extends RecyclerView.Adapter<ThreadsAdapter.ThreadHolder> {
    private List<Post> posts;
    private Context context;
    private OnItemClickListener itemClickListener;

    public ThreadsAdapter(Context context,List<Post> posts) {
        this.context=context;
        this.posts = posts;
    }

    public void setPosts(List<Post> posts){
        this.posts=posts;
        notifyDataSetChanged();
    }
    public void addPosts(List<Post> posts){
        this.posts.addAll(this.posts.size(),posts);
        notifyDataSetChanged();
    }

    @Override
    public ThreadHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thread_item,viewGroup,false);
        ThreadHolder holder=new ThreadHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ThreadHolder threadHolder, int i) {

        Post post=posts.get(i);
        threadHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public void setItemClickListener(OnItemClickListener listener){
        itemClickListener=listener;
    }
    public static interface OnItemClickListener {
        public void onItemClick(Post post);
    }

    public  class ThreadHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.thread_avator) public ImageView avator;
        @Bind(R.id.thread_author) public TextView author;

        @Bind(R.id.thread_title) public TextView title;
        private Post post;
        public ThreadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener!=null){
                        itemClickListener.onItemClick(post);
                    }
                }
            });
        }
        public void bind(Post post){
            this.post=post;
            int size = context.getResources().getDimensionPixelSize(R.dimen.avatar_size);
            Glide.with(context).load(post.getAvatarUrl()).placeholder(R.drawable.default_avatar)
                    .transform(new GlideCircleTransform(context)).crossFade().into(avator);
            author.setText(post.getAuthor());
            title.setText(post.getTitle());
        }

    }
}
