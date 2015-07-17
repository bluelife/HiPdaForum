package forum.org.hipda.presenter;

import forum.org.hipda.domain.entity.PostDetailModel;
import forum.org.hipda.domain.interactor.GetPostDetailUsecase;
import forum.org.hipda.view.PostDetailView;
import rx.Subscriber;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public class PostDetailPresenter extends Subscriber<PostDetailModel> implements Presenter {

    private PostDetailView postDetailView;
    private GetPostDetailUsecase getPostDetailUsecase;
    private int postId;

    public PostDetailPresenter(PostDetailView postDetailView, GetPostDetailUsecase getPostDetailUsecase) {

        this.postDetailView = postDetailView;
        this.getPostDetailUsecase = getPostDetailUsecase;
    }

    public void setView(PostDetailView postDetailView){
        this.postDetailView=postDetailView;
    }
    public void init(int id){
        postId=id;
        postDetailView.showLoading();
        getPostDetailUsecase.execute(this);
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        getPostDetailUsecase.unsubscribe();
    }

    @Override
    public void onCompleted() {
        postDetailView.hideLoading();
    }

    @Override
    public void onError(Throwable e) {

        postDetailView.showError(e.getMessage());
    }

    @Override
    public void onNext(PostDetailModel postDetailModel) {

        postDetailView.showPost(postDetailModel);
    }
}
