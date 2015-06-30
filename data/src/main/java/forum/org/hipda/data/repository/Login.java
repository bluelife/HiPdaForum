package forum.org.hipda.data.repository;

import forum.org.hipda.domain.entity.LoginInfo;

import forum.org.hipda.data.net.RestApi;
import forum.org.hipda.domain.entity.User;
import forum.org.hipda.domain.repository.GetLogin;
import rx.Observable;

/**
 * Created by slomka.jin on 2015/6/30.
 */
public class Login implements GetLogin {
    private RestApi restApi;

    public Login(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<LoginInfo> getInfo(User user) {
        return restApi.login(user);
    }
}
