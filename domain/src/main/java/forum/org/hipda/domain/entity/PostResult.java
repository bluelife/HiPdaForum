package forum.org.hipda.domain.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by slomka.jin on 2015/7/24.
 */
@Data
public class PostResult {
    private List<PostResultItem> postResultItems;
    private String searchIdUrl;
    private int maxPage;

    public PostResult(){
        postResultItems=new ArrayList<>();
    }
    public void addPostItem(PostResultItem item){
        postResultItems.add(item);
    }
}
