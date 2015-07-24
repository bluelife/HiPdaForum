package forum.org.hipda.view;

import forum.org.hipda.domain.entity.PostResult;

/**
 * Created by slomka.jin on 2015/7/24.
 */
public interface SearchResultView extends LoadDataView {
    void showResult(PostResult postResult);
    void hideSwipeLoading();
}
