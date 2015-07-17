package forum.org.hipda.di.component;

import dagger.Component;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.di.module.ActivityModule;
import forum.org.hipda.di.module.ForumDataModule;
import forum.org.hipda.view.fragment.PostDetailFragment;
import forum.org.hipda.view.fragment.ThreadsFragment;

/**
 * Created by silong on 2015/7/5.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules ={ActivityModule.class, ForumDataModule.class} )
public interface ForumDataComponent {
    void inject(ThreadsFragment threadsFragment);
    void inject(PostDetailFragment postDetailFragment);
}
