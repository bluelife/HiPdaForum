package forum.org.hipda.di.module;

import dagger.Module;
import dagger.Provides;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.interactor.GetThreadsUsecase;
import forum.org.hipda.domain.repository.ForumSource;

/**
 * Created by silong on 2015/7/5.
 */
@Module
public class ForumDataModule {

    @Provides @PerActivity
    GetThreadsUsecase getThreads(ForumSource forumSource,int forumId,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetThreadsUsecase(threadExecutor,postExecutionThread,forumSource,forumId);
    }
}
