package forum.org.hipda.di.component;

import dagger.Component;
import forum.org.hipda.di.PerActivity;
import forum.org.hipda.di.module.ActivityModule;

import forum.org.hipda.di.module.SearchPostModule;
import forum.org.hipda.view.fragment.SearchFragment;

/**
 * Created by slomka.jin on 2015/7/24.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules ={ActivityModule.class, SearchPostModule.class} )
public interface SearchResultComponent {
    void inject(SearchFragment searchFragment);
}
