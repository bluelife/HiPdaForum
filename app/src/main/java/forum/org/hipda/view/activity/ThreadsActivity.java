package forum.org.hipda.view.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

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
    private Toolbar toolbar;
    private boolean searchAll=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);
        init();
    }
    private void init(){

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        forumDataComponent= DaggerForumDataComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .forumDataModule(new ForumDataModule(7,1)).build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            //searchView.setSubmitButtonEnabled(true);

        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.search_type_title:
                item.setChecked(true);
                searchAll=false;
                return true;
            case R.id.search_type_all:
                item.setChecked(true);
                searchAll=true;
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void startActivity(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            intent.putExtra(SearchActivity.TYPE,searchAll);
        }
        super.startActivity(intent);
    }

    @Override
    public ForumDataComponent getComponent() {
        return forumDataComponent;
    }

    @Override
    public void viewPost(Post post) {
        navigator.navigateToDetail(this,Integer.valueOf(post.getTid()));
    }
}
