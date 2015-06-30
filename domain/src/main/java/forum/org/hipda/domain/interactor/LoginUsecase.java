package forum.org.hipda.domain.interactor;


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
    protected LoginUsecase(GetLogin getLogin,User user,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.getLogin=getLogin;
        this.user=user;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return getLogin.getInfo(user);
    }
}
