package ngohoanglong.com.dacsan.dependencyinjection.component;

import dagger.Subcomponent;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.dependencyinjection.module.UserModule;
import ngohoanglong.com.dacsan.view.login.LoginActivity;
import ngohoanglong.com.dacsan.view.login.LoginFragment;

/**
 * Created by Long on 2/28/2017.
 */
@ActivityScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
    void inject(LoginActivity loginActivity);

    void inject(LoginFragment loginFragment);
}
