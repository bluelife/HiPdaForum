package forum.org.hipda.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import forum.org.hipda.data.entity.PostDetail;
import forum.org.hipda.data.entity.PostList;
import forum.org.hipda.domain.entity.PostDetailModel;
import forum.org.hipda.domain.entity.PostListModel;

/**
 * Created by slomka.jin on 2015/7/17.
 */
@Singleton
public class ForumDataMapper {
    @Inject
    public ForumDataMapper(){
    }

    public PostDetailModel transformPostDetail(PostDetail postDetail){
        PostDetailModel detailModel=null;

        if(postDetail!=null){
            detailModel=new PostDetailModel();
            detailModel.setAuthor(postDetail.getAuthor());
            detailModel.setAvatarUrl(postDetail.getAvatarUrl());
            detailModel.getContents().setContent(postDetail.getContents().getContent());
            detailModel.getContents().setCopyText(postDetail.getContents().getCopyText());
            detailModel.getContents().setList(postDetail.getContents().getList());
            detailModel.setFloor(postDetail.getFloor());
            detailModel.setPostId(postDetail.getPostId());
            detailModel.setPostStatus(postDetail.getPostStatus());
            detailModel.setTimePost(postDetail.getTimePost());
            detailModel.setUid(postDetail.getUid());
        }
        return detailModel;
    }

    public PostListModel transformPostList(PostList postList){

        PostListModel postListModel=null;

        if(postList!=null){
            List<PostDetailModel> detailModels=new ArrayList<>();
            postListModel=new PostListModel();
            postListModel.setFId(postList.getFId());
            postListModel.setLastPage(postList.getLastPage());
            postListModel.setPage(postList.getPage());
            postListModel.setTId(postList.getTId());
            postListModel.setTitle(postList.getTitle());
            for(int i=0;i<postList.getPostDetails().size();i++){
                PostDetailModel detailModel=transformPostDetail(postList.getPostDetails().get(i));
                detailModels.add(detailModel);
            }
            postListModel.setPostDetails(detailModels);
        }
        return postListModel;
    }

}
