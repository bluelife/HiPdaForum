package forum.org.hipda.navigator;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import forum.org.hipda.view.activity.PostDetailActivity;
import forum.org.hipda.view.activity.ThreadsActivity;

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
            Intent intent=new Intent(context, ThreadsActivity.class);
            context.startActivity(intent);
        }
    }
    public void navigateToDetail(Context context,int id){
        if(context!=null){
            Intent intent= PostDetailActivity.getCallingIntent(context,id);
            context.startActivity(intent);
        }
    }
}
