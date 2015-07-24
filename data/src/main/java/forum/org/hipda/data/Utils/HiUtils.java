package forum.org.hipda.data.Utils;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import forum.org.hipda.data.net.RestApi;

/**
 * Created by silong on 2015/7/5.
 */
public class HiUtils {
    private static Whitelist mWhitelist = null;
    private final static String AVATAR_BASE = "000000000";
    public final static String ENCODE_FORMAT="GBK";
    public static String getMiddleString(String source, String start, String end) {
        int start_idx = source.indexOf(start) + start.length();
        int end_idx = 0;
        if (end.isEmpty()) {
            end_idx = source.length();
        } else {
            end_idx = source.indexOf(end, start_idx);
            if (end_idx <= 0) {
                end_idx = source.length();
            }
        }

        if (start_idx <= 0 || end_idx <= 0 || end_idx <= start_idx) {

            return null;
        }

        return source.substring(start_idx, end_idx);
    }
    public static String getAvatarUrlByUid(String uid) {
        if (TextUtils.isEmpty(uid)
                || uid.length() > AVATAR_BASE.length()
                || !TextUtils.isDigitsOnly(uid))
            return "";

        String fullUid = AVATAR_BASE.substring(0, AVATAR_BASE.length() - uid.length()) + uid;
        String url = RestApi.AVATAR_URL
                + fullUid.substring(0, 3) + "/"
                + fullUid.substring(3, 5) + "/"
                + fullUid.substring(5, 7) + "/"
                + fullUid.substring(7, 9) + "_avatar_middle.jpg";
        return url;
    }
    public static String getFullUrl(String particalUrl) {
        return RestApi.API_BASE_URL + particalUrl;
    }
    public static String nullToText(String text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        return text;
    }
    public static CharSequence fromHtmlAndStrip(String s) {
        return Html.fromHtml(s).toString().replace((char) 160, (char) 32).replace((char) 65532, (char) 32);
    }
    public static int getIntFromString(String s) {
        String tmp = s.replaceAll("[^\\d]", "");
        if (!tmp.isEmpty()) {
            return Integer.parseInt(tmp);
        } else {
            return 0;
        }
    }
    /**
     * return parsable html for TextViewWithEmoticon
     */
    public static String clean(String html) {
        if (mWhitelist == null) {
            mWhitelist = new Whitelist();
            mWhitelist.addTags(
                    "a",
                    "br", "p",
                    "b", "i", "strike", "strong", "u",
                    "font")

                    .addAttributes("a", "href")
                    .addAttributes("font", "color")

                    .addProtocols("a", "href", "http", "https");
        }
        return Jsoup.clean(html, "", mWhitelist, new Document.OutputSettings().prettyPrint(false));
    }
}
