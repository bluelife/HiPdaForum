package forum.org.hipda.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import forum.org.hipda.R;
import forum.org.hipda.data.Utils.HiUtils;
import forum.org.hipda.data.entity.ContentAttach;
import forum.org.hipda.data.entity.ContentGoToFloor;
import forum.org.hipda.data.entity.ContentQuote;
import forum.org.hipda.data.entity.ContentText;
import forum.org.hipda.domain.entity.ContentAbs;
import forum.org.hipda.domain.entity.ContentImg;
import forum.org.hipda.domain.entity.PostDetailModel;
import forum.org.hipda.view.custom.TextViewWithEmoticon;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public class PostDetailAdapter extends RecyclerView.Adapter<PostDetailAdapter.DetailHolder> {

    private List<PostDetailModel> detailModels;
    private Context context;
    private LayoutInflater mInflater;

    public PostDetailAdapter(List<PostDetailModel> detailModels, Context context) {
        this.detailModels = detailModels;
        this.context = context;
        mInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.thread_item,parent,false);
        DetailHolder detailHolder=new DetailHolder(v,parent);
        return detailHolder;
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, int position) {

        PostDetailModel detailModel=detailModels.get(position);
        holder.bind(detailModel);
    }

    @Override
    public int getItemCount() {
        return detailModels.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_avatar)
        ImageView avatar;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.floor)
        TextView floor;
        @Bind(R.id.post_status)
        TextView status;
        @Bind(R.id.content_layout)
        LinearLayout contentLayout;
        private boolean trimBr;
        private ViewGroup parent;

        public DetailHolder(View itemView,ViewGroup viewGroup) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            parent=viewGroup;
        }
        public void bind(PostDetailModel postDetailModel){
            Glide.with(context).load(postDetailModel.getAvatarUrl()).into(avatar);
            author.setText(postDetailModel.getAuthor());
            time.setText(postDetailModel.getTimePost());
            floor.setText(postDetailModel.getFloor());
            status.setText(postDetailModel.getPostStatus());
            contentLayout.removeAllViews();
            for (int i = 0; i < postDetailModel.getContents().getSize(); i++) {
                ContentAbs content = postDetailModel.getContents().get(i);
                if (content instanceof ContentText) {
                    TextViewWithEmoticon tv = (TextViewWithEmoticon) mInflater.inflate(R.layout.item_textview_withemoticon, parent, false);
                    //tv.setFragmentManager(mFragmentManager);
                    //tv.setTextSize(HiSettingsHelper.getPostTextSize());
                    tv.setPadding(8, 8, 8, 8);

                    //dirty hack, remove extra <br>
                    String cnt = content.getContent();
                    if (trimBr) {
                        if (cnt.startsWith("<br><br><br>")) {
                            cnt = cnt.substring("<br><br>".length());
                        } else if (cnt.startsWith("<br><br>")) {
                            cnt = cnt.substring("<br>".length());
                        }
                    }
                    if (!"<br>".equals(cnt)) {
                        tv.setText(cnt);
                        tv.setFocusable(false);
                        contentLayout.addView(tv);
                    }
                } else if (content instanceof ContentImg) {
                    final String imageUrl = content.getContent();


                } else if (content instanceof ContentAttach) {
                    TextViewWithEmoticon tv = (TextViewWithEmoticon) mInflater.inflate(R.layout.item_textview_withemoticon, parent, false);
                    //tv.setFragmentManager(mFragmentManager);
                    //tv.setTextSize(HiSettingsHelper.getPostTextSize());
                    tv.setText(content.getContent());
                    tv.setFocusable(false);
                    contentLayout.addView(tv);
                } else if (content instanceof ContentQuote && !((ContentQuote) content).isReplyQuote()) {

                    LinearLayout quoteLayout = (LinearLayout) mInflater.inflate(R.layout.item_quote_text_simple, parent, false);
                    TextViewWithEmoticon tv = (TextViewWithEmoticon) quoteLayout.findViewById(R.id.quote_content);

                    //tv.setTextSize(HiSettingsHelper.getPostTextSize() - 1);
                    tv.setAutoLinkMask(Linkify.WEB_URLS);
                    tv.setText(content.getContent());
                    tv.setFocusable(false);    // make convertView long clickable.

                    contentLayout.addView(quoteLayout);
                    trimBr = true;
                } else if (content instanceof ContentGoToFloor || content instanceof ContentQuote) {

                    String author = "";
                    String time = "";
                    String note = "";
                    String text = "";

                    int floor = -1;
                    if (content instanceof ContentGoToFloor) {
                        //floor is not accurate if some user deleted post
                        //use floor to get page, then get cache by postid
                        ContentGoToFloor goToFloor = (ContentGoToFloor) content;
                        author = goToFloor.getAuthor();
                        floor = goToFloor.getFloor();
                        PostDetailModel detailBean = mDetailFragment.getCachedPost(goToFloor.getPostId());
                        if (detailBean != null) {
                            text = detailBean.getContents().getContent();
                            floor = Integer.parseInt(detailBean.getFloor());
                        }
                        note = floor + "#";
                    } else {
                        ContentQuote contentQuote = (ContentQuote) content;
                        PostDetailModel detailBean = null;
                        if (!TextUtils.isEmpty(contentQuote.getPostId()) && TextUtils.isDigitsOnly(contentQuote.getPostId())) {
                            detailBean = mDetailFragment.getCachedPost(contentQuote.getPostId());
                        }
                        if (detailBean != null) {
                            author = contentQuote.getAuthor();
                            text = detailBean.getContents().getContent();
                            floor = Integer.parseInt(detailBean.getFloor());
                            note = floor + "#";
                        } else {
                            author = ((ContentQuote) content).getAuthor();
                            if (!TextUtils.isEmpty(((ContentQuote) content).getTo()))
                                note = "to: " + ((ContentQuote) content).getTo();
                            time = ((ContentQuote) content).getTime();
                            text = ((ContentQuote) content).getText();
                        }
                    }

                    LinearLayout quoteLayout = (LinearLayout) mInflater.inflate(R.layout.item_quote_text, parent, false);

                    TextView tvAuthor = (TextView) quoteLayout.findViewById(R.id.quote_author);
                    TextView tvNote = (TextView) quoteLayout.findViewById(R.id.quote_note);
                    TextViewWithEmoticon tvContent = (TextViewWithEmoticon) quoteLayout.findViewById(R.id.quote_content);
                    TextView tvTime = (TextView) quoteLayout.findViewById(R.id.quote_post_time);

                    tvContent.setTrim(true);

                    tvAuthor.setText(HiUtils.nullToText(author));
                    tvNote.setText(HiUtils.nullToText(note));
                    tvContent.setText(HiUtils.nullToText(text));
                    tvTime.setText(HiUtils.nullToText(time));

                    /*tvAuthor.setTextSize(HiSettingsHelper.getPostTextSize() - 2);
                    tvNote.setTextSize(HiSettingsHelper.getPostTextSize() - 2);
                    tvContent.setTextSize(HiSettingsHelper.getPostTextSize() - 1);
                    tvTime.setTextSize(HiSettingsHelper.getPostTextSize() - 4);*/

                    if (floor > 0) {
                        tvNote.setTag(floor);
                        //tvNote.setOnClickListener(mGoToFloorListener);
                        tvNote.setFocusable(false);
                        tvNote.setClickable(true);
                    }

                    contentLayout.addView(quoteLayout);
                    trimBr = true;
                }
            }
        }
    }
}
