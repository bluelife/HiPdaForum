package forum.org.hipda.domain.entity;

import java.util.List;

import lombok.Data;

/**
 * Created by slomka.jin on 2015/7/17.
 */
@Data
public class PostListModel {
    private List<PostDetailModel> postDetails;
    private String title;
    private String tId;
    private String fId;
    private int lastPage=1;
    private int page=0;
}
