package forum.org.hipda.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import forum.org.hipda.R;
import forum.org.hipda.di.component.ForumDataComponent;
import forum.org.hipda.di.component.LoginComponent;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.presenter.ThreadsPresenter;
import forum.org.hipda.view.ThreadsView;
import forum.org.hipda.view.adapter.ThreadsAdapter;

/**
 * Created by silong on 2015/7/5.
 */
public class ThreadsFragment extends BaseFragment implements ThreadsView, ThreadsAdapter.OnItemClickListener {

    public interface ThreadsListener{
        void viewPost(Post post);
    }
    @Bind(R.id.threads_view)
    RecyclerView threadsView;
    @Bind(R.id.rl_progress)
    ViewGroup progressBar;
    @Bind(R.id.swipeRefreshLayout)
    SwipyRefreshLayout refreshLayout;
    @Inject ThreadsPresenter threadsPresenter;
    private ThreadsAdapter threadsAdapter;
    private ThreadsListener threadsListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView=inflater.inflate(R.layout.fragment_threads,container,true);
        ButterKnife.bind(this,fragmentView);
        return fragmentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ThreadsListener)
            threadsListener= (ThreadsListener) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init(){

        getComponent(ForumDataComponent.class).inject(this);
        threadsPresenter.setView(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        threadsView.setLayoutManager(linearLayoutManager);
        refreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if(swipyRefreshLayoutDirection==SwipyRefreshLayoutDirection.TOP){
                    threadsPresenter.refreshThreads();
                }
                else{
                    threadsPresenter.loadMore();
                }
            }
        });
        loadThreads();
    }

    private void loadThreads(){
        threadsPresenter.init();
    }
    @Override
    public void showThreads(List<Post> posts) {

        Log.w("threads frag posts",posts.get(0).getTitle());
        if(threadsAdapter==null){
            threadsAdapter=new ThreadsAdapter(getContext(),posts);
            threadsAdapter.setItemClickListener(this);
            threadsView.setAdapter(threadsAdapter);
        }
        else{
            threadsAdapter.addPosts(posts);
        }

    }

    @Override
    public void updateThreads(List<Post> posts) {

    }

    @Override
    public void refreshThreads(List<Post> posts) {
        threadsAdapter.setPosts(posts);
    }

    @Override
    public void hideSwipeLoading(){
        refreshLayout.setRefreshing(false);
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
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

        Log.w("threads",message+"");
    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void onItemClick(Post post) {

        threadsListener.viewPost(post);
    }
}
