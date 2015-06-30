package forum.org.hipda.data.entity.mapper;

import forum.org.hipda.domain.entity.LoginInfo;

/**
 * Created by silong on 2015/6/28.
 */
public class LoginMapper {

    public LoginInfo transform(String info){
        LoginInfo loginInfo;
        if(info.contains("")){
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
