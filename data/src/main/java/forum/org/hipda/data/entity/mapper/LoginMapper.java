package forum.org.hipda.data.entity.mapper;

import android.content.Context;

import forum.org.hipda.data.R;
import forum.org.hipda.domain.entity.LoginInfo;

/**
 * Created by silong on 2015/6/28.
 */
public class LoginMapper {

    public LoginInfo transform(Context context,String info){
        LoginInfo loginInfo;
        if(info.contains(context.getString(R.string.login_success))){
            loginInfo=LoginInfo.SUCCESS;
        }
        else if(info.contains("")){
            loginInfo=LoginInfo.FAIL;
        }
        else{
            loginInfo=LoginInfo.UNKNOWN_ERROR;
        }
        return loginInfo;
    }
}
