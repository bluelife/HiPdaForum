package forum.org.hipda.domain.interactor;

import javax.inject.Inject;

import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.repository.ForumSource;
import rx.Observable;

/**
 * Created by silong on 2015/7/5.
 */
public class GetThreadsUsecase extends UseCase {
    private ForumSource forumSource;
    private int id;
    private int pageIndex;

    @Inject
    public GetThreadsUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ForumSource forumSource,int id,int i) {
        super(threadExecutor, postExecutionThread);
        this.forumSource = forumSource;
        this.id=id;
        pageIndex=i;
    }
    public void addPageIndex(){
        pageIndex++;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return forumSource.getThreadList(id,pageIndex);
    }
}
