package forum.org.hipda.navigator;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Singleton
public class Navigator {
    @Inject
    public Navigator() {
    }

    public void navigateToForum(Context context){
        if(context!=null){
            Log.w("navigator","go to forum");
        }
    }
}
