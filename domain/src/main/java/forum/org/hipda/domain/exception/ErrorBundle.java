package forum.org.hipda.domain.exception;

/**
 * Created by silong on 2015/6/28.
 */
public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
