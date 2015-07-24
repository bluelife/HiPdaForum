package forum.org.hipda.domain.entity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public class PostDetailModel {
    private String mAuthor;
    private String mUid;
    private String mAvatarUrl;
    private String mPostId;
    private String mTimePost;
    private String mFloor;
    private String mPostStatus;
    private Contents mContents;
    private Collection<ContentImg> mImages = new ArrayList<>();

    public PostDetailModel() {
        mContents = new Contents();
    }

    public class Contents {
        private ArrayList<ContentAbs> list;
        private int lastTextIdx;
        private Boolean newString;
        private String content;
        private String copyText;

        public Contents() {
            list = new ArrayList<>();
            lastTextIdx = -1;
            newString = true;
        }

        public void setList(ArrayList<ContentAbs> contentAbses){
            list=contentAbses;
        }



        public ContentAbs get(int idx) {
            return list.get(idx);
        }

        public void setCopyText(String copyText){
            this.copyText=copyText;
        }
        public String getCopyText() {
            return copyText;
        }
        public int getSize(){
            return list.size();
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content){
            this.content=content;
        }
    }

    public String getAuthor() {
        return mAuthor;
    }

    public boolean setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;

        //return !HiSettingsHelper.getInstance().isUserBlack(mAuthor);
        return true;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    public String getPostId() {
        return mPostId;
    }

    public void setPostId(String mPostId) {
        this.mPostId = mPostId;
    }

    public String getTimePost() {
        return mTimePost;
    }

    public void setTimePost(String mTimePost) {
        this.mTimePost = mTimePost;
    }

    public String getFloor() {
        return mFloor;
    }

    public void setFloor(String mFloor) {
        this.mFloor = mFloor;
    }

    public String getPostStatus() {
        return mPostStatus;
    }

    public void setPostStatus(String mPostStatus) {
        this.mPostStatus = mPostStatus;
    }

    public Contents getContents() {
        return mContents;
    }

    public void setContents(Contents contents) {
        this.mContents = contents;
    }


    public Collection<ContentImg> getImages() {
        return mImages;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        if (avatarUrl.contains("noavatar")) {
            this.mAvatarUrl = "";
        } else {
            this.mAvatarUrl = avatarUrl;
        }
    }

    private String unEscapeHtml(String str) {
        str = str.replaceAll("&nbsp;", " ");
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&gt;", ">");

        return str;
    }
}
