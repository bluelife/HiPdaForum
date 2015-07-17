package forum.org.hipda.view;

import forum.org.hipda.domain.entity.PostDetailModel;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public interface PostDetailView extends LoadDataView {
    void showPost(PostDetailModel postDetailModel);
}
