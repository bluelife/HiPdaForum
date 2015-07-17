package forum.org.hipda.domain.interactor;

import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.repository.ForumSource;
import rx.Observable;

/**
 * Created by slomka.jin on 2015/7/7.
 */
public class RefreshThreadsUsecase extends UseCase {
    private ForumSource forumSource;
    private int id;
    private int startIndex=1;

    public RefreshThreadsUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ForumSource forumSource, int id) {
        super(threadExecutor, postExecutionThread);
        this.forumSource = forumSource;
        this.id = id;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return forumSource.getThreadList(id,startIndex);
    }
}
