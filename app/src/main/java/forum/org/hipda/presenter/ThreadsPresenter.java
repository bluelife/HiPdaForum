package forum.org.hipda.presenter;

import java.util.List;

import javax.inject.Inject;

import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.interactor.DefaultSubscriber;
import forum.org.hipda.domain.interactor.GetThreadsUsecase;
import forum.org.hipda.view.ThreadsView;

/**
 * Created by silong on 2015/7/5.
 */
@PerActivity
public class ThreadsPresenter extends DefaultSubscriber<List<Post>> implements Presenter {
    private ThreadsView threadsView;
    private GetThreadsUsecase getThreadsUsecase;

    @Inject

    public ThreadsPresenter(ThreadsView threadsView, GetThreadsUsecase getThreadsUsecase) {
        this.getThreadsUsecase = getThreadsUsecase;
    }
    public void setView(ThreadsView threadsView){
        this.threadsView=threadsView;
    }

    public void init(){
        threadsView.showLoading();
        getThreadsUsecase.execute(this);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void onCompleted() {
        super.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        threadsView.hideLoading();
        threadsView.showError(e.getMessage());
    }

    @Override
    public void onNext(List<Post> posts) {
        threadsView.hideLoading();
        threadsView.showThreads(posts);
    }

    @Override
    public void destroy() {

        getThreadsUsecase.unsubscribe();
    }
}
