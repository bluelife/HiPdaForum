package forum.org.hipda.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import forum.org.hipda.data.entity.Board;
import forum.org.hipda.data.entity.PostList;
import forum.org.hipda.data.entity.mapper.ForumMapper;
import forum.org.hipda.domain.entity.LoginInfo;

import forum.org.hipda.data.entity.mapper.LoginMapper;
import forum.org.hipda.data.exception.NetworkConnectionException;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.entity.User;
import rx.Observable;

/**
 * Created by silong on 2015/6/28.
 */
@Singleton
public class RestApiImpl implements RestApi {
    private Context context;

    @Inject
    public RestApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<LoginInfo> login(User user) {
        return Observable.create(subscriber -> {
            Log.w("login",user.getUsername());
            if(isThereInternetConnection()){

                try {
                    String response = loginFromApi(user);
                    Log.w("response",response);
                    if (response != null) {

                        subscriber.onNext(new LoginMapper().transform(context,response));
                        subscriber.onCompleted();
                    }
                    else{
                        subscriber.onError(new NetworkConnectionException());
                    }
                }
                catch (Exception ex){
                    Log.w("response",ex+"");
                    subscriber.onError(new NetworkConnectionException());
                }
            }
            else{
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    private String loginFromApi(User user) throws MalformedURLException{

        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("cookietime","2592000");
        map.put("loginfield", "username");

        return ApiConnection.createGET(RestApi.LOGIN_URL).postSyncCall(map);
    }

    private String getThreadsFromApi(int id,int index) throws MalformedURLException{

        return ApiConnection.createGET(RestApi.API_BASE_URL+RestApi.THREADS_URL+id+"&page="+index).requestSyncCall();
    }
    private String getPostDetailsFromApi(int id,int index) throws MalformedURLException{
        return ApiConnection.createGET(RestApi.API_BASE_URL+RestApi.DETAIL_URL+id+"&page="+index).requestSyncCall();
    }

    @Override
    public Observable<Post> getPost() {
        return null;
    }

    @Override
    public Observable<List<Post>> getThreads(int id,int index) {
        return Observable.create(subscriber -> {
            if(isThereInternetConnection()) {

                try {
                    String response = getPostDetailsFromApi(id, index);
                    Log.w("resssss",response);
                    if (response != null) {

                        subscriber.onNext(new ForumMapper().transformPosts(response));
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (Exception ex) {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
            else{
                subscriber.onError(new NetworkConnectionException());
            }
            });
    }

    @Override
    public Observable<PostList> getPostDetails(int id, int index) {
        return Observable.create(subscriber -> {
            if(isThereInternetConnection()) {

                try {
                    String response = getThreadsFromApi(id,index);
                    Log.w("resssss",response);
                    if (response != null) {

                        subscriber.onNext(new ForumMapper().transformDetails(response));
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (Exception ex) {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
            else{
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<List<Board>> getBoards() {
        return null;
    }
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
