package forum.org.hipda;

import javax.inject.Inject;
import javax.inject.Singleton;

import forum.org.hipda.domain.executor.PostExecutionThread;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by slomka.jin on 2015/6/30.
 */
@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
