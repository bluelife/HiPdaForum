package forum.org.hipda.data.net;



import java.util.List;

import forum.org.hipda.data.entity.Board;
import forum.org.hipda.domain.entity.LoginInfo;
import forum.org.hipda.data.entity.Post;

import forum.org.hipda.domain.entity.User;
import rx.Observable;

/**
 * Created by silong on 2015/6/28.
 */
public interface RestApi {

    static final String API_BASE_URL="";
    static final String LOGIN_URL="";

    Observable<LoginInfo> login(User user);
    Observable<Post> getPost();
    Observable<List<Board>> getBoards();
}
