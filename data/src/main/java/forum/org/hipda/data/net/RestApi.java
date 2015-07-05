package forum.org.hipda.data.net;



import java.util.List;

import forum.org.hipda.data.entity.Board;
import forum.org.hipda.domain.entity.LoginInfo;

import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.entity.User;
import rx.Observable;

/**
 * Created by silong on 2015/6/28.
 */
public interface RestApi {

    static final String API_BASE_URL="http://www.hi-pda.com/forum/";
    static final String LOGIN_URL="http://www.hi-pda.com/forum/logging.php?action=login&loginsubmit=yes&inajax=1";
    static final String THREADS_URL="forumdisplay.php?fid=";
    static final String AVATAR_URL=API_BASE_URL+"uc_server/data/avatar/";

    Observable<LoginInfo> login(User user);
    Observable<Post> getPost();
    Observable<List<Post>> getThreads(int id);
    Observable<List<Board>> getBoards();
}
