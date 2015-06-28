package forum.org.hipda.data.entity;

import lombok.Data;

/**
 * Created by silong on 2015/6/28.
 */
@Data
public class User {
    private String avatarUrl;
    private String detail;
    private String username;
    private String id;
    private String password;
}
