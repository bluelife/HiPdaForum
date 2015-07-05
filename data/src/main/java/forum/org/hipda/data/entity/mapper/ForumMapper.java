package forum.org.hipda.data.entity.mapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.List;

import forum.org.hipda.data.Utils.HiUtils;
import forum.org.hipda.domain.entity.Post;


/**
 * Created by silong on 2015/6/28.
 */
public class ForumMapper {

    public List<Post> transformPosts(String response){
        List<Post> posts=new ArrayList<>();
        Document doc= Jsoup.parse(response);
        Elements tbodyES = doc.select("tbody[id]");
        for (int i = 0; i < tbodyES.size(); ++i) {

            Element tbodyE = tbodyES.get(i);
            Post thread = new Post();

			/* title and tid */
            String[] idSpil = tbodyE.attr("id").split("_");
            if (idSpil.length != 2) {
                continue;
            }
            String idType = idSpil[0];
            String idNum = idSpil[1];
            String idThread = "thread_" + idNum;
            thread.setTid(idNum);
            // is stick thread or normal thread
            Boolean isStick = idType.startsWith("stickthread");
            thread.setStick(isStick);

            /*if (isStick && !HiSettingsHelper.getInstance().isShowStickThreads()) {
                continue;
            }*/

            Elements titleES = tbodyE.select("span#" + idThread);
            if (titleES.size() == 0) {
                continue;
            }
            String title = titleES.first().text();
            thread.setTitle(title);

            Elements typeES = tbodyE.select("th.subject em a");
            if (typeES.size() > 0) {
                thread.setType(typeES.text());
            }

            Elements threadIsNewES = tbodyE.select("td.folder img");
            if (threadIsNewES.size() > 0) {
                String imgSrc = (threadIsNewES.first().attr("src"));
                thread.setNew(imgSrc.contains("new"));
            }

			/*  author, authorId and create_time  */
            Elements authorES = tbodyE.select("td.author");
            if (authorES.size() == 0) {
                continue;
            }
            Elements authorciteAES = authorES.first().select("cite a");
            if (authorciteAES.size() == 0) {
                continue;
            }
            /*String author = authorciteAES.first().text();
            if (!thread.setAuthor(author)) {
                //author in blacklist
                continue;
            }*/

            String userLink = authorciteAES.first().attr("href");
            if (userLink.length() < "space.php?uid=".length()) {
                continue;
            }
            String authorId = HiUtils.getMiddleString(userLink, "uid=", "&");
            thread.setAuthorId(authorId);

            thread.setAvatarUrl(HiUtils.getAvatarUrlByUid(authorId));

            Elements threadCreateTimeES = authorES.first().select("em");
            if (threadCreateTimeES.size() == 0) {
                continue;
            }
            String threadCreateTime = threadCreateTimeES.first().text();
            thread.setTimeCreate(threadCreateTime);

            Elements threadUpdateTimeES = tbodyE.select("td.lastpost em a");
            if (threadUpdateTimeES.size() > 0) {
                String threadUpdateTime = threadUpdateTimeES.first().text();
                thread.setTimeUpdate(threadUpdateTime);
            }

			/*  comments and views  */
            Elements nums = tbodyE.select("td.nums");
            if (nums.size() == 0) {
                continue;
            }
            Elements comentsES = nums.first().select("strong");
            if (comentsES.size() == 0) {
                continue;
            }
            String comments = comentsES.first().text();
            thread.setCountCmts(comments);

            Elements viewsES = nums.first().select("em");
            if (viewsES.size() == 0) {
                continue;
            }
            String views = viewsES.first().text();
            thread.setCountViews(views);

            // lastpost
            Elements lastpostciteES = tbodyE.select("td.lastpost cite");
            if (lastpostciteES.size() == 0) {
                continue;
            }
            String lastpost = lastpostciteES.first().text();
            thread.setLastPost(lastpost);

            // attachment and picture
            Elements attachs = tbodyE.select("img.attach");
            for (int j = 0; j < attachs.size(); j++) {
                Element attach = attachs.get(j);
                String attach_img_url = attach.attr("src");
                if (attach_img_url.isEmpty()) {
                    continue;
                }
                if (attach_img_url.endsWith("image_s.gif")) {
                    thread.setHavePic(true);
                }
                if (attach_img_url.endsWith("common.gif")) {
                    thread.setHaveAttach(true);
                }
            }
        }
        return posts;
    }
}
