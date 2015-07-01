package forum.org.hipda.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.entity.User;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.interactor.LoginUsecase;
import forum.org.hipda.domain.interactor.UseCase;
import forum.org.hipda.domain.repository.GetLogin;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Module
public class LoginModule {

    public LoginModule() {
    }

    @Provides @PerActivity @Named("login")
    LoginUsecase getLoginInfo(GetLogin getLogin, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new LoginUsecase(getLogin, threadExecutor, postExecutionThread);
    }

}
