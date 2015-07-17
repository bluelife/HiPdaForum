package forum.org.hipda.data.entity;

import java.util.List;

import lombok.Data;

/**
 * Created by slomka.jin on 2015/7/17.
 */
@Data
public class PostList {

    private List<PostDetail> postDetails;
    private String title;
    private String tId;
    private String fId;
    private int lastPage=1;
    private int page=0;
}
