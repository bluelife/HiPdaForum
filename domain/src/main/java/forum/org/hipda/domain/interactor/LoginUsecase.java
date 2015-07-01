package forum.org.hipda.domain.interactor;


import javax.inject.Inject;

import forum.org.hipda.domain.entity.User;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.domain.repository.GetLogin;
import rx.Observable;

/**
 * Created by slomka.jin on 2015/6/30.
 */
public class LoginUsecase extends UseCase {
    private GetLogin getLogin;
    private User user;
    @Inject
    public LoginUsecase(GetLogin getLogin,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.getLogin=getLogin;
    }

    public void setUser(User user){
        this.user=user;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return getLogin.getInfo(user);
    }
}
