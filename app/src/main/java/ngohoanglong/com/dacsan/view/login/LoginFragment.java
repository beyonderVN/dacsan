package ngohoanglong.com.dacsan.view.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.databinding.ActivityLoginBinding;
import ngohoanglong.com.dacsan.dependencyinjection.module.UserModule;
import ngohoanglong.com.dacsan.view.BaseRxDelegateFragment;
import ngohoanglong.com.dacsan.view.main.MainActivity;

/**
 * Created by Long on 2/28/2017.
 */
public class LoginFragment extends BaseRxDelegateFragment {
    private static final String TAG = "LoginFragment";

    private ActivityLoginBinding binding;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Inject
    LoginViewModel viewModel ;

    @BindView(R.id.vaStateController)
    ViewAnimator viewAnimator;
    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        Log.d(TAG, "onLoginClick: ");
        getCompositeSubscription().add(viewModel.login()
                .takeUntil(stopEvent())
                .subscribe())
        ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DacsanApplication)getActivity().getApplication()).getAppComponent()
                .plus(new UserModule())
                .inject(this);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);
        binding.setViewModel(viewModel);
        setUpViews(rootView);
        return rootView;
    }
    private void setUpViews(View view) {

    }
    @Override
    public void bindViewModel() {
        Log.d(TAG, "bindViewModel: ");
        getCompositeSubscription().add(viewModel.toast()
                .takeUntil(stopEvent())
                .subscribe(this::showMessage));
        getCompositeSubscription().add(viewModel.loadingState()
                .takeUntil(stopEvent())
                .startWith(1)
                .subscribe(this::setLoadingState));
        getCompositeSubscription().add(viewModel.loginIsSuccess()
                .takeUntil(stopEvent())
                .subscribe(this::handleResponse));
    }

    private void showMessage(String value) {
        Log.d(TAG, "showMessage: ");
        new AlertDialog.Builder(getActivity())
                .setMessage(value)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void setLoadingState(Integer value) {
        viewAnimator.setDisplayedChild(value);
    }

    private void handleResponse(Boolean aBoolean){
        if(aBoolean){
            startActivity(MainActivity.getIntentNewTask(getActivity()));
        }
    }


}
