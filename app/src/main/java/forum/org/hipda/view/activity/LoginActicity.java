package forum.org.hipda.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;

import forum.org.hipda.R;
import forum.org.hipda.di.component.DaggerLoginComponent;
import forum.org.hipda.di.component.LoginComponent;
import forum.org.hipda.presenter.HasComponent;
import forum.org.hipda.view.fragment.LoginFragment;

/**
 * Created by slomka.jin on 2015/7/1.
 */
public class LoginActicity extends BaseActivity implements HasComponent<LoginComponent>,LoginFragment.LoginListener {

    private LoginComponent loginComponent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){

        loginComponent= DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public LoginComponent getComponent() {
        return loginComponent;
    }

    @Override
    public void onLogin() {
        this.navigator.navigateToForum(this);
    }
}
