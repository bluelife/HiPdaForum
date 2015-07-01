package forum.org.hipda.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.widget.Toast;

import forum.org.hipda.presenter.HasComponent;

/**
 * Created by slomka.jin on 2015/7/1.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }
}
