package forum.org.hipda.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import forum.org.hipda.AndroidApplication;
import forum.org.hipda.UIThread;
import forum.org.hipda.data.executor.JobExecutor;
import forum.org.hipda.data.net.RestApi;
import forum.org.hipda.data.net.RestApiImpl;
import forum.org.hipda.data.repository.ForumData;
import forum.org.hipda.data.repository.Login;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.repository.ForumSource;
import forum.org.hipda.domain.repository.GetLogin;
import forum.org.hipda.navigator.Navigator;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
    @Provides @Singleton
    GetLogin provideLogin(Login login){
        return login;
    }
    @Provides @Singleton
    RestApi provideApi(RestApiImpl restApi){
        return restApi;
    }
    @Provides @Singleton
    ForumSource provideForumSource(ForumData data){
        return data;
    }


}
