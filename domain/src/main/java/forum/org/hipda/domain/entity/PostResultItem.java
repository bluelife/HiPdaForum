package forum.org.hipda.domain.entity;

import lombok.Data;

/**
 * Created by slomka.jin on 2015/7/24.
 */
@Data
public class PostResultItem {
    private String tid;
    private String pid;
    private String title;
    private String info;
    private String forum;
    private String time;
    private String uid;
    private String author;
    private String avatarUrl = "";
    private boolean isNew = false;
}
