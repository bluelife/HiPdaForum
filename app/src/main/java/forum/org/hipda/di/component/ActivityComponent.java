package forum.org.hipda.di.component;

import android.app.Activity;

import dagger.Component;
import dagger.Module;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.di.module.ActivityModule;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
