package forum.org.hipda.domain.executor;

import rx.Scheduler;

/**
 * Created by silong on 2015/6/28.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}