package forum.org.hipda.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import forum.org.hipda.R;
import forum.org.hipda.di.component.DaggerForumDataComponent;
import forum.org.hipda.di.component.ForumDataComponent;
import forum.org.hipda.di.module.ForumDataModule;
import forum.org.hipda.presenter.HasComponent;
import forum.org.hipda.view.fragment.PostDetailFragment;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public class PostDetailActivity extends BaseActivity implements HasComponent<ForumDataComponent> {
    private ForumDataComponent forumDataComponent;
    private static final String INTENT_EXTRA_PARAM_POST_ID="forum.org.hipda.post.id";
    private int postId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initActivity(savedInstanceState);
        init();
    }
    public static Intent getCallingIntent(Context context,String postId){
        Intent callingIntent = new Intent(context, PostDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_POST_ID, postId);
        return callingIntent;
    }
    private void initActivity(Bundle savedState){
        if(savedState==null){
            postId=getIntent().getIntExtra(INTENT_EXTRA_PARAM_POST_ID,0);
            addFragment(R.id.fragment_post, PostDetailFragment.newInstance(postId));
        }
        else{
            postId=savedState.getInt(INTENT_EXTRA_PARAM_POST_ID);
        }
    }

    private void init(){
        forumDataComponent= DaggerForumDataComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .forumDataModule(new ForumDataModule(postId)).build();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(outState!=null){
            outState.putInt(INTENT_EXTRA_PARAM_POST_ID,postId);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public ForumDataComponent getComponent() {
        return forumDataComponent;
    }
}
