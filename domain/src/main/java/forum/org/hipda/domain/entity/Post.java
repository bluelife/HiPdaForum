package forum.org.hipda.domain.entity;

import lombok.Data;

/**
 * Created by silong on 2015/7/5.
 */
@Data
public class Post {
    private String title;
    private String tid;
    private boolean isStick;

    private String author;
    private String authorId;
    private String avatarUrl;
    private String lastPost;

    private String countCmts;
    private String countViews;

    private String timeCreate;
    private String timeUpdate;
    private Boolean haveAttach;
    private Boolean havePic;
    private boolean isNew;
    private String type;
}