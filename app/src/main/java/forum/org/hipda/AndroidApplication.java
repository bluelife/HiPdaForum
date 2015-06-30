package forum.org.hipda;

import android.app.Application;

import forum.org.hipda.di.component.ApplicationComponent;
import forum.org.hipda.di.component.DaggerApplicationComponent;
import forum.org.hipda.di.module.ApplicationModule;

/**
 * Created by slomka.jin on 2015/6/30.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}