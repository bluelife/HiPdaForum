package forum.org.hipda.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.interactor.DefaultSubscriber;
import forum.org.hipda.domain.interactor.GetThreadsUsecase;
import forum.org.hipda.domain.interactor.RefreshThreadsUsecase;
import forum.org.hipda.view.ThreadsView;

/**
 * Created by silong on 2015/7/5.
 */
@PerActivity
public class ThreadsPresenter implements Presenter {
    private ThreadsView threadsView;
    private GetThreadsUsecase getThreadsUsecase;
    private RefreshThreadsUsecase refreshThreadsUsecase;

    @Inject
    public ThreadsPresenter(GetThreadsUsecase getThreadsUsecase,RefreshThreadsUsecase refreshThreadsUsecase) {
        this.getThreadsUsecase = getThreadsUsecase;
        this.refreshThreadsUsecase=refreshThreadsUsecase;
    }
    public void setView(ThreadsView threadsView){
        this.threadsView=threadsView;
    }

    public void init(){
        threadsView.showLoading();
        getThreadsUsecase.execute(new LoadMoreSubscriber());

    }
    public void refreshThreads(){
        refreshThreadsUsecase.execute(new RefreshSubscriber());
    }
    public void loadMore(){
        getThreadsUsecase.addPageIndex();
        getThreadsUsecase.execute(new LoadMoreSubscriber());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }


    @Override
    public void destroy() {

        refreshThreadsUsecase.unsubscribe();
        getThreadsUsecase.unsubscribe();
    }
    private class LoadMoreSubscriber extends DefaultSubscriber<List<Post>> {
        @Override
        public void onCompleted() {
            threadsView.hideSwipeLoading();
        }

        @Override
        public void onError(Throwable e) {
            threadsView.hideLoading();
            threadsView.hideSwipeLoading();
            threadsView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<Post> posts) {
            threadsView.hideLoading();
            threadsView.hideSwipeLoading();
            threadsView.showThreads(posts);
        }
    }
    private class RefreshSubscriber extends DefaultSubscriber<List<Post>> {
        @Override
        public void onCompleted() {
            threadsView.hideSwipeLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Post> posts) {
            threadsView.refreshThreads(posts);
        }
    }
}
