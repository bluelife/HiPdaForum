package forum.org.hipda.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import forum.org.hipda.R;
import forum.org.hipda.di.component.DaggerForumDataComponent;
import forum.org.hipda.di.component.ForumDataComponent;
import forum.org.hipda.di.module.ForumDataModule;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.presenter.HasComponent;
import forum.org.hipda.view.fragment.ThreadsFragment;

/**
 * Created by slomka.jin on 2015/7/7.
 */
public class ThreadsActivity extends BaseActivity implements HasComponent<ForumDataComponent>,ThreadsFragment.ThreadsListener {
    private ForumDataComponent forumDataComponent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);
        init();
    }
    private void init(){

        forumDataComponent= DaggerForumDataComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .forumDataModule(new ForumDataModule(7,1)).build();
    }

    @Override
    public ForumDataComponent getComponent() {
        return forumDataComponent;
    }

    @Override
    public void viewPost(Post post) {
        navigator.navigateToDetail(this,post.getTid());
    }
}
