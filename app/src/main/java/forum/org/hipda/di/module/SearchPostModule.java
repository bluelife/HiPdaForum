package forum.org.hipda.di.module;

import dagger.Module;
import dagger.Provides;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.interactor.GetSearchResultUsecase;
import forum.org.hipda.domain.repository.ForumSource;

/**
 * Created by slomka.jin on 2015/7/24.
 */
@Module
public class SearchPostModule {
    private String query;
    private int index;
    private boolean searchAll;
    private int forumId;

    public SearchPostModule(String query, int index, boolean searchAll, int forumId) {
        this.query = query;
        this.index = index;
        this.searchAll = searchAll;
        this.forumId = forumId;
    }
    @Provides @PerActivity
    GetSearchResultUsecase getSearchResultUsecase(ForumSource forumSource,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new GetSearchResultUsecase(threadExecutor,postExecutionThread,forumSource,query,index,forumId);
    }
}
