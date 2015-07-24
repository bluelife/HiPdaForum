package forum.org.hipda.data.net;



import java.util.List;

import forum.org.hipda.data.entity.Board;
import forum.org.hipda.data.entity.PostList;
import forum.org.hipda.domain.entity.LoginInfo;

import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.entity.PostResult;
import forum.org.hipda.domain.entity.PostResultItem;
import forum.org.hipda.domain.entity.User;
import rx.Observable;

/**
 * Created by silong on 2015/6/28.
 */
public interface RestApi {

    static final String API_BASE_URL="http://www.hi-pda.com/forum/";
    static final String LOGIN_URL="http://www.hi-pda.com/forum/logging.php?action=login&loginsubmit=yes&inajax=1";
    static final String THREADS_URL="forumdisplay.php?fid=";
    static final String DETAIL_URL="viewthread.php?tid=";
    static final String AVATAR_URL=API_BASE_URL+"uc_server/data/avatar/";
    public static final String SEARCH_TITLE = API_BASE_URL + "search.php?srchtype=title&searchsubmit=true&st=on&srchuname=&srchfilter=all&srchfrom=0&before=&orderby=lastpost&ascdesc=desc&srchfid%5B0%5D=";
    public static final String SEARCH_FULL_TEXT = API_BASE_URL + "search.php?srchtype=fulltext&searchsubmit=true&st=on&srchuname=&srchfilter=all&srchfrom=0&before=&orderby=lastpost&ascdesc=desc&srchfid%5B0%5D=";
    public static final String SearchUserThreads = API_BASE_URL + "search.php?srchfid=all&srchfrom=0&searchsubmit=yes&srchuid=";
    public static final String FavoritesUrl = API_BASE_URL + "my.php?item=favorites&type=thread";
    public static final String FavoriteAddUrl = API_BASE_URL + "my.php?item=favorites&inajax=1&ajaxtarget=favorite_msg&tid=";
    public static final String FavoriteRemoveUrl = API_BASE_URL + "my.php?item=favorites&action=remove&inajax=1&ajaxtarget=favorite_msg&tid=";
    public static final String UserInfoUrl = API_BASE_URL + "space.php?uid=";
    public final static String SMILE_PATH = "images/smilies/";

    Observable<LoginInfo> login(User user);
    Observable<Post> getPost();
    Observable<List<Post>> getThreads(int id,int index);
    Observable<List<Board>> getBoards();
    Observable<PostList> getPostDetails(int id,int index);
    Observable<PostResult> getPostResultBySearch(String key, int forumId, int index);
}
