package forum.org.hipda.domain.interactor;

import javax.inject.Inject;

import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.repository.ForumSource;
import rx.Observable;

/**
 * Created by slomka.jin on 2015/7/16.
 */
public class GetPostDetailUsecase extends UseCase {
    private int postId;
    private ForumSource forumSource;
    private int pageIndex=1;

    @Inject
    public GetPostDetailUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,ForumSource source, int postId) {
        super(threadExecutor, postExecutionThread);
        this.postId = postId;
        forumSource=source;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return forumSource.getPostDetailList(postId,pageIndex);
    }
}
