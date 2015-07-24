package forum.org.hipda.domain.interactor;

import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.repository.ForumSource;
import rx.Observable;

/**
 * Created by slomka.jin on 2015/7/24.
 */
public class GetSearchResultUsecase extends UseCase {
    private ForumSource forumSource;
    private String key;
    private int id;
    private int pageIndex;

    public GetSearchResultUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ForumSource forumSource, String query,int pageIndex,int id) {
        super(threadExecutor, postExecutionThread);
        this.forumSource = forumSource;
        this.pageIndex = pageIndex;
        this.id=id;
        key=query;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return forumSource.getPostResultBySearch(key,id,pageIndex);
    }
}
