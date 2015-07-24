package forum.org.hipda.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import forum.org.hipda.R;
import forum.org.hipda.di.component.ForumDataComponent;
import forum.org.hipda.domain.entity.PostDetailModel;
import forum.org.hipda.domain.entity.PostListModel;
import forum.org.hipda.presenter.PostDetailPresenter;
import forum.org.hipda.view.PostDetailView;
import forum.org.hipda.view.adapter.PostDetailAdapter;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public class PostDetailFragment extends BaseFragment implements PostDetailView {

    @Bind(R.id.rl_progress)
    ViewGroup progressBar;
    @Bind(R.id.details_recyclerView)
    RecyclerView detailsView;

    @Inject
    PostDetailPresenter postDetailPresenter;
    private static final String KEY_POST_ID="forum.org.hipda.detail.id";
    private int postId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView=inflater.inflate(R.layout.fragment_post_detail,container,false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }
    public static PostDetailFragment newInstance(int id){
        PostDetailFragment postDetailFragment=new PostDetailFragment();
        Bundle args=new Bundle();
        args.putInt(KEY_POST_ID, id);
        postDetailFragment.setArguments(args);
        return postDetailFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(postId);
    }
    private void init(int id){

        getComponent(ForumDataComponent.class).inject(this);
        postDetailPresenter.setView(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        detailsView.setLayoutManager(linearLayoutManager);
        loadPosts(id);
    }
    private void loadPosts(int id){
        postDetailPresenter.init(id);
    }

    @Override
    public void showPost(PostListModel postListModel) {

        PostDetailAdapter postDetailAdapter=new PostDetailAdapter(postListModel.getPostDetails(),getContext());
        detailsView.setAdapter(postDetailAdapter);
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

        Log.w("laod error",message+"");
        Snackbar.make(detailsView,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }
}
