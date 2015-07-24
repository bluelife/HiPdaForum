package forum.org.hipda.view.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import forum.org.hipda.R;
import forum.org.hipda.di.component.DaggerSearchResultComponent;
import forum.org.hipda.di.component.SearchResultComponent;
import forum.org.hipda.di.module.SearchPostModule;
import forum.org.hipda.domain.entity.PostResultItem;
import forum.org.hipda.presenter.HasComponent;
import forum.org.hipda.presenter.SearchResultPresenter_Factory;
import forum.org.hipda.view.fragment.SearchFragment;

/**
 * Created by slomka.jin on 2015/7/23.
 */
public class SearchActivity extends BaseActivity implements HasComponent<SearchResultComponent>,SearchFragment.ViewResultDetail{
    public static final String TYPE="type";

    private SearchResultComponent searchResultComponent;
    private String query;
    private boolean searchAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        handleIntent(getIntent());
        init();
    }

    private void init() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        searchResultComponent= DaggerSearchResultComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchPostModule(new SearchPostModule(query,0,searchAll,-1)).build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            query=intent.getStringExtra(SearchManager.QUERY);
            searchAll=intent.getBooleanExtra(TYPE, false);
            Log.w("search",searchAll+" ");
        }
    }
    @Override
    public SearchResultComponent getComponent(){
        return searchResultComponent;
    }

    @Override
    public void viewDetail(PostResultItem item) {
        navigator.navigateToDetail(this,Integer.valueOf(item.getTid()));
    }
}
