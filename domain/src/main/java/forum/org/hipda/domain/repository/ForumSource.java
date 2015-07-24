package forum.org.hipda.domain.repository;

import java.util.List;

import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.entity.PostListModel;
import forum.org.hipda.domain.entity.PostResult;
import forum.org.hipda.domain.entity.PostResultItem;
import rx.Observable;

/**
 * Created by silong on 2015/7/5.
 */
public interface ForumSource {
    Observable<List<Post>> getThreadList(int id,int index);
    Observable<PostListModel> getPostDetailList(int id,int index);
    Observable<PostResult> getPostResultBySearch(String key,int forumId,int index);
}
