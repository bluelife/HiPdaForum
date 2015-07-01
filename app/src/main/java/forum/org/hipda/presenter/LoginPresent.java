package forum.org.hipda.presenter;

import javax.inject.Inject;
import javax.inject.Named;

import forum.org.hipda.di.PerActivity;
import forum.org.hipda.domain.entity.LoginInfo;
import forum.org.hipda.domain.entity.User;
import forum.org.hipda.domain.interactor.DefaultSubscriber;
import forum.org.hipda.domain.interactor.LoginUsecase;
import forum.org.hipda.domain.interactor.UseCase;
import forum.org.hipda.view.LoginView;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@PerActivity
public class LoginPresent extends DefaultSubscriber<LoginInfo> implements Presenter {

    private LoginView loginView;
    private final LoginUsecase loginUsecase;

    @Inject
    public LoginPresent(@Named("login") LoginUsecase loginUsecase) {
        this.loginUsecase = loginUsecase;
    }

    public void setView(LoginView loginView){
        this.loginView=loginView;
    }

    public void onLoginClick(User user){
        loginUsecase.setUser(user);
        loginUsecase.execute(this);
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        loginUsecase.unsubscribe();
    }


    @Override
    public void onCompleted() {
        super.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        //loginView.
        loginView.hideLoading();
        loginView.showError(e.getMessage());
    }

    @Override
    public void onNext(LoginInfo loginInfo) {
        loginView.hideLoading();
        if(loginInfo==LoginInfo.SUCCESS){
            loginView.onloginSuccess();
        }
        else if(loginInfo==LoginInfo.FAIL){
            loginView.showError("login fail");
        }
        else{
            loginView.showError("unknown error");
        }
    }
}
