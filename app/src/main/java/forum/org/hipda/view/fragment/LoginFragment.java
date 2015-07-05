package forum.org.hipda.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import forum.org.hipda.R;
import forum.org.hipda.di.component.LoginComponent;
import forum.org.hipda.domain.entity.User;
import forum.org.hipda.presenter.LoginPresent;
import forum.org.hipda.view.LoginView;

/**
 * Created by slomka.jin on 2015/7/1.
 */
public class LoginFragment extends BaseFragment implements LoginView{
    @Inject
    LoginPresent loginPresent;

    @Bind(R.id.rl_progress)
    View progressBar;
    @Bind(R.id.username)
    EditText userName;
    @Bind(R.id.password) EditText password;
    @OnClick(R.id.login_btn) void submit(){
        User user=new User();
        user.setUsername(userName.getText().toString());
        user.setPassword(password.getText().toString());
        loginPresent.onLoginClick(user);
    }

    public LoginFragment() {
        super();
    }

    private LoginListener loginListener;
    public interface LoginListener{
        void onLogin();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof LoginListener)
            loginListener=(LoginListener)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView=inflater.inflate(R.layout.fragment_login,container,true);
        ButterKnife.bind(this,fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        getComponent(LoginComponent.class).inject(this);
        loginPresent.setView(this);
    }

    @Override
    public void onloginSuccess() {

        if(loginListener!=null)
            loginListener.onLogin();
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {

        getActivity().setProgressBarIndeterminateVisibility(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

        showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return null;
    }
}
