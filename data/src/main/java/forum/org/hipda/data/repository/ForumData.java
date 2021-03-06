package forum.org.hipda.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import forum.org.hipda.data.entity.mapper.ForumDataMapper;
import forum.org.hipda.data.net.RestApi;
import forum.org.hipda.domain.entity.Post;
import forum.org.hipda.domain.entity.PostListModel;
import forum.org.hipda.domain.entity.PostResult;
import forum.org.hipda.domain.entity.PostResultItem;
import forum.org.hipda.domain.repository.ForumSource;
import rx.Observable;

/**
 * Created by silong on 2015/7/5.
 */
@Singleton
public class ForumData implements ForumSource {
    private RestApi restApi;
    private ForumDataMapper forumDataMapper;

    @Inject
    public ForumData(RestApi restApi,ForumDataMapper mapper)
    {
        this.restApi = restApi;
        forumDataMapper=mapper;
    }

    @Override
    public Observable<List<Post>> getThreadList(int id,int index) {
        return restApi.getThreads(id,index);
    }

    @Override
    public Observable<PostListModel> getPostDetailList(int id, int index) {
        return restApi.getPostDetails(id, index).map(postList -> forumDataMapper.transformPostList(postList));
    }

    @Override
    public Observable<PostResult> getPostResultBySearch(String key, int forumId, int index) {
        return restApi.getPostResultBySearch(key,forumId,index);
    }
}
