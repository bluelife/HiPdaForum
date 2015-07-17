package forum.org.hipda.data.entity;

import forum.org.hipda.data.Utils.HiUtils;
import forum.org.hipda.domain.entity.ContentAbs;

/**
 * Created by slomka.jin on 2015/7/16.
 */
public class ContentText extends ContentAbs {

    private StringBuilder mSb;

    public ContentText(String text) {
        mSb = new StringBuilder();
        mSb.append(text);
    }

    public void append(String txt) {
        mSb.append(txt);
    }

    @Override
    public String getContent() {
        return mSb.toString();
    }

    @Override
    public String getCopyText() {
        return HiUtils.fromHtmlAndStrip(mSb.toString()).toString();
    }

}
