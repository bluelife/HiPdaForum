package forum.org.hipda.domain.repository;

import java.util.List;

import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.entity.PostListModel;
import rx.Observable;

/**
 * Created by silong on 2015/7/5.
 */
public interface ForumSource {
    Observable<List<Post>> getThreadList(int id,int index);
    Observable<PostListModel> getPostDetailList(int id,int index);
}
