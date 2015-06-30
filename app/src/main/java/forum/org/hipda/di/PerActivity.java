package forum.org.hipda.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
