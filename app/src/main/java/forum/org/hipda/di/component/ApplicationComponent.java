package forum.org.hipda.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import forum.org.hipda.di.module.ApplicationModule;
import forum.org.hipda.domain.executor.PostExecutionThread;
import forum.org.hipda.domain.executor.ThreadExecutor;
import forum.org.hipda.view.activity.BaseActivity;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

}
