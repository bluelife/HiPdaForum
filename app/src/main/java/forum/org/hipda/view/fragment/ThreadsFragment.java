package forum.org.hipda.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import forum.org.hipda.R;
import forum.org.hipda.di.component.ForumDataComponent;
import forum.org.hipda.di.component.LoginComponent;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.presenter.ThreadsPresenter;
import forum.org.hipda.view.ThreadsView;

/**
 * Created by silong on 2015/7/5.
 */
public class ThreadsFragment extends BaseFragment implements ThreadsView {

    private ThreadsPresenter threadsPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView=inflater.inflate(R.layout.fragment_login,container,true);
        ButterKnife.bind(this,fragmentView);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init(){

        getComponent(ForumDataComponent.class).inject(this);
        threadsPresenter.setView(this);
        loadThreads();
    }

    private void loadThreads(){
        threadsPresenter.init();
    }
    @Override
    public void showThreads(List<Post> posts) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
        return null;
    }
}
