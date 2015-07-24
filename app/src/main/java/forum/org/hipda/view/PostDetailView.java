package forum.org.hipda.view;

import forum.org.hipda.domain.entity.PostDetailModel;
import forum.org.hipda.domain.entity.PostListModel;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public interface PostDetailView extends LoadDataView {
    void showPost(PostListModel postListModel);
}
