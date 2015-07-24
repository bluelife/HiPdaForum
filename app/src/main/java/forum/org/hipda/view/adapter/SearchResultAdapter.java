package forum.org.hipda.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import forum.org.hipda.domain.entity.PostResult;
import forum.org.hipda.domain.entity.PostResultItem;

/**
 * Created by slomka.jin on 2015/7/24.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.PostResultHolder> {

    private Context context;
    private List<PostResultItem> postResultItems;
    private OnItemClickListener itemClickListener;

    public SearchResultAdapter(Context context, List<PostResultItem> postResultItems) {
        this.context = context;
        this.postResultItems = postResultItems;
    }

    @Override
    public PostResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_list,parent,false);
        PostResultHolder holder=new PostResultHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PostResultHolder holder, int position) {

        PostResultItem postResult=postResultItems.get(position);
        holder.bind(postResult);
    }

    @Override
    public int getItemCount() {
        return postResultItems.size();
    }

    public void setItemClickListener(OnItemClickListener listener){
        itemClickListener=listener;
    }
    public static interface OnItemClickListener {
        public void onItemClick(PostResultItem post);
    }

    public class PostResultHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_avatar)
        ImageView avatar;
        @Bind(R.id.tv_info)
        TextView info;
        @Bind(R.id.tv_title)
        TextView title;
        @Bind(R.id.tv_forum)
        TextView forum;
        @Bind(R.id.tv_time)
        TextView postTime;
        private PostResultItem resultItem;
        public PostResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener!=null){
                        itemClickListener.onItemClick(resultItem);
                    }
                }
            });
        }
        public void bind(PostResultItem postResultItem){
            resultItem=postResultItem;
            Glide.with(context).load(postResultItem.getAvatarUrl()).into(avatar);
            info.setText(postResultItem.getInfo());
            title.setText(postResultItem.getTitle());
            forum.setText(postResultItem.getForum());
            postTime.setText(postResultItem.getTime());
        }
    }
}
