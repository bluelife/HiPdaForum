package forum.org.hipda.data.entity;

import lombok.Data;

/**
 * Created by silong on 2015/6/28.
 */
@Data
public class Post {
    private String mTitle;
    private String mTid;
    private boolean mIsStick;

    private String mAuthor;
    private String mAuthorId;
    private String mAvatarUrl;
    private String mLastPost;

    private String mCountCmts;
    private String mCountViews;

    private String mTimeCreate;
    private String mTimeUpdate;
    private Boolean mHaveAttach;
    private Boolean mHavePic;
    private boolean mNew;
    private String mType;
}
