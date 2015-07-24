package forum.org.hipda.domain.entity;

import forum.org.hipda.domain.entity.ContentAbs;

/**
 * Created by slomka.jin on 2015/7/16.
 */
public class ContentImg extends ContentAbs {
    private String mUrl;
    private String mFloor;
    private int mIndexInPage;

    public ContentImg(String url) {
        mUrl = url;

    }

    @Override
    public String getContent() {
        return mUrl;
    }

    @Override
    public String getCopyText() {
        return "[图片:" + mUrl + "]";
    }

    public String getFloor() {
        return mFloor;
    }

    public void setFloor(String floor) {
        mFloor = floor;
    }

    public int getIndexInPage() {
        return mIndexInPage;
    }

    public void setIndexInPage(int indexInPage) {
        mIndexInPage = indexInPage;
    }
}