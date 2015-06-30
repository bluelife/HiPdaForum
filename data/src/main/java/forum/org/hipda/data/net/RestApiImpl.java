package forum.org.hipda.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

import forum.org.hipda.data.entity.Board;
import forum.org.hipda.domain.entity.LoginInfo;
import forum.org.hipda.data.entity.Post;

import forum.org.hipda.data.entity.mapper.LoginMapper;
import forum.org.hipda.data.exception.NetworkConnectionException;
import forum.org.hipda.domain.entity.User;
import rx.Observable;

/**
 * Created by silong on 2015/6/28.
 */
public class RestApiImpl implements RestApi {
    private Context context;

    public RestApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<LoginInfo> login(User user) {
        return Observable.create(subscriber -> {
            if(isThereInternetConnection()){

                try {
                    String response = loginFromApi(user);
                    if (response != null) {

                        subscriber.onNext(new LoginMapper().transform(response));
                        subscriber.onCompleted();
                    }
                    else{
                        subscriber.onError(new NetworkConnectionException());
                    }
                }
                catch (Exception ex){

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
        map.put("user",user.getUsername());
        map.put("password",user.getPassword());
        return ApiConnection.createGET(RestApi.LOGIN_URL).postSyncCall(map);
    }

    @Override
    public Observable<Post> getPost() {
        return null;
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
