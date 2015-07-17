package forum.org.hipda.view;

import java.util.List;

import forum.org.hipda.domain.entity.Post;

/**
 * Created by silong on 2015/7/5.
 */
public interface ThreadsView extends LoadDataView {
    void showThreads(List<Post> posts);
    void hideSwipeLoading();
    void refreshThreads(List<Post> posts);
    void updateThreads(List<Post> posts);
}
