package forum.org.hipda.data.repository;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import forum.org.hipda.domain.entity.LoginInfo;

import forum.org.hipda.data.net.RestApi;
import forum.org.hipda.domain.entity.User;
import forum.org.hipda.domain.repository.GetLogin;
import rx.Observable;
import rx.functions.Action0;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Singleton
public class Login implements GetLogin {
    private RestApi restApi;

    @Inject
    public Login(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<LoginInfo> getInfo(User user) {
        Observable<LoginInfo> observable=restApi.login(user);
        observable.doOnCompleted(()-> Log.w("geo","done"));
        observable.doOnEach(observable1->{
            Log.w("geo",observable1.getValue().toString());
        });
        Log.w("getinfo","");
        return observable;
    }
}
