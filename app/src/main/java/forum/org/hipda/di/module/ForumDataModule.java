package forum.org.hipda.di.module;

import dagger.Module;
import dagger.Provides;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.interactor.GetPostDetailUsecase;
import forum.org.hipda.domain.interactor.GetThreadsUsecase;
import forum.org.hipda.domain.interactor.RefreshThreadsUsecase;
import forum.org.hipda.domain.repository.ForumSource;

/**
 * Created by silong on 2015/7/5.
 */
@Module
public class ForumDataModule {
    private int forumId;
    private int pageIndex;
    private int postId;
    public ForumDataModule(int id,int i){
        forumId=id;
        pageIndex=i;
    }
    public ForumDataModule(int postId){
        this.postId=postId;
    }

    @Provides @PerActivity
    GetThreadsUsecase getThreads(ForumSource forumSource,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetThreadsUsecase(threadExecutor,postExecutionThread,forumSource,forumId,pageIndex);
    }
    @Provides @PerActivity
    RefreshThreadsUsecase refreshThreads(ForumSource forumSource,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new RefreshThreadsUsecase(threadExecutor,postExecutionThread,forumSource,forumId);
    }
    @Provides @PerActivity
    GetPostDetailUsecase getPostDetailUsecase(ForumSource forumSource,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetPostDetailUsecase(threadExecutor,postExecutionThread,forumSource,postId);
    }
}
