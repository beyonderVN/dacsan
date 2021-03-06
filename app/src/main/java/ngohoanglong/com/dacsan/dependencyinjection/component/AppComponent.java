package ngohoanglong.com.dacsan.dependencyinjection.component;

import javax.inject.Singleton;

import dagger.Component;
import ngohoanglong.com.dacsan.dependencyinjection.module.AppModule;
import ngohoanglong.com.dacsan.dependencyinjection.module.DataModule;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;
import ngohoanglong.com.dacsan.dependencyinjection.module.UserModule;
import ngohoanglong.com.dacsan.manager.AuthManager;
import ngohoanglong.com.dacsan.view.main.MainActivity;
import ngohoanglong.com.dacsan.view.signup.SignupActivity;
import ngohoanglong.com.dacsan.view.welcome.WelcomeActivity;

/**
 * Created by Long on 2/28/2017.
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    PostComponent plus(PostModule postModule);
    UserComponent plus(UserModule userModule);
    AuthManager getAuthManager();

    void inject(MainActivity mainActivity);

    void inject(SignupActivity signupActivity);

    void inject(WelcomeActivity welcomeActivity);
}
