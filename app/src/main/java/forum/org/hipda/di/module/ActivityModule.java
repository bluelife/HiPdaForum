package forum.org.hipda.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import forum.org.hipda.di.PerActivity;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Module
public class ActivityModule {
    private Activity activity;
    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
