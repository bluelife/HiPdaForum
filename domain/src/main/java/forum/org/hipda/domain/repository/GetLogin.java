package forum.org.hipda.domain.repository;



import forum.org.hipda.domain.entity.LoginInfo;
import forum.org.hipda.domain.entity.User;
import rx.Observable;

/**
 * Created by slomka.jin on 2015/6/30.
 */
public interface GetLogin {
    Observable<LoginInfo> getInfo(User user);
}
