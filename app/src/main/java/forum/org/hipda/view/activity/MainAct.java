package forum.org.hipda.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import forum.org.hipda.R;

/**
 * Created by slomka.jin on 2015/7/1.
 */
public class MainAct extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
    }
}
