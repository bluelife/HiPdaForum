package forum.org.hipda.di.component;

import dagger.Component;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.di.module.ActivityModule;
import forum.org.hipda.di.module.LoginModule;
import forum.org.hipda.view.fragment.LoginFragment;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules ={ActivityModule.class, LoginModule.class})
public interface LoginComponent extends ActivityComponent {
    void inject(LoginFragment loginFragment);
}

