package forum.org.hipda.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import forum.org.hipda.R;
import forum.org.hipda.di.component.ForumDataComponent;
import forum.org.hipda.di.component.SearchResultComponent;
import forum.org.hipda.domain.entity.PostResult;
import forum.org.hipda.domain.entity.PostResultItem;
import forum.org.hipda.presenter.SearchResultPresenter;
import forum.org.hipda.view.SearchResultView;
import forum.org.hipda.view.adapter.SearchResultAdapter;

/**
 * Created by slomka.jin on 2015/7/24.
 */
public class SearchFragment extends BaseFragment implements SearchResultView,SearchResultAdapter.OnItemClickListener {

    public interface ViewResultDetail{
        void viewDetail(PostResultItem item);
    }
    @Inject
    SearchResultPresenter searchResultPresenter;
    @Bind(R.id.rl_progress)
    ViewGroup progressBar;
    @Bind(R.id.search_result_view)
    RecyclerView searchResultListView;
    @Bind(R.id.swipeRefreshLayout)
    SwipyRefreshLayout refreshLayout;
    private SearchResultAdapter searchResultAdapter;
    private ViewResultDetail viewResultDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView=inflater.inflate(R.layout.fragment_search_result,container,false);
        ButterKnife.bind(this,fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        getComponent(SearchResultComponent.class).inject(this);
        searchResultPresenter.setSearchResultView(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        searchResultListView.setLayoutManager(linearLayoutManager);
        refreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.TOP) {
                    //searchResultPresenter.refreshThreads();
                } else {
                    //searchResultPresenter.loadMore();
                }
            }
        });
        loadResults();
    }

    private void loadResults() {
        searchResultPresenter.init();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ViewResultDetail){
            viewResultDetail=(ViewResultDetail)activity;
        }
    }

    @Override
    public void showResult(PostResult postResult) {

        if(searchResultAdapter==null){
            searchResultAdapter=new SearchResultAdapter(getContext(),postResult.getPostResultItems());
            searchResultListView.setAdapter(searchResultAdapter);
            searchResultAdapter.setItemClickListener(this);
        }
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideSwipeLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void onItemClick(PostResultItem post) {
        if(viewResultDetail!=null){
            viewResultDetail.viewDetail(post);
        }
    }
}
