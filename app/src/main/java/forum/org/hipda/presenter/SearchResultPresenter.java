package forum.org.hipda.presenter;

import javax.inject.Inject;

import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.entity.PostResult;
import forum.org.hipda.domain.interactor.DefaultSubscriber;
import forum.org.hipda.domain.interactor.GetSearchResultUsecase;
import forum.org.hipda.view.SearchResultView;

/**
 * Created by slomka.jin on 2015/7/24.
 */
@PerActivity
public class SearchResultPresenter implements Presenter {
    private SearchResultView searchResultView;
    private GetSearchResultUsecase searchResultUsecase;

    @Inject
    public SearchResultPresenter(GetSearchResultUsecase searchResultUsecase) {

        this.searchResultUsecase = searchResultUsecase;
    }
    public void setSearchResultView(SearchResultView resultView){
        searchResultView=resultView;
    }
    public void init(){
        searchResultView.showLoading();
        searchResultUsecase.execute(new ResultSubscriber());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        searchResultUsecase.unsubscribe();
    }

    private class ResultSubscriber extends DefaultSubscriber<PostResult>{
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            searchResultView.hideLoading();
            searchResultView.hideSwipeLoading();
            searchResultView.showError(e.getMessage());
        }

        @Override
        public void onNext(PostResult postResult) {
            searchResultView.hideLoading();
            searchResultView.hideSwipeLoading();
            searchResultView.showResult(postResult);
        }
    }

}
