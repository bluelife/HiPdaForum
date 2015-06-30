package forum.org.hipda.exception;

import android.content.Context;

import forum.org.hipda.R;
import forum.org.hipda.data.exception.NetworkConnectionException;

/**
 * Created by slomka.jin on 2015/6/30.
 */
public class ErrorMessageFactory {
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        }

        return message;
    }
}
