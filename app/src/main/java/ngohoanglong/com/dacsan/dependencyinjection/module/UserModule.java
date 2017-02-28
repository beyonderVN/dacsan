package ngohoanglong.com.dacsan.dependencyinjection.module;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ngohoanglong.com.dacsan.view.login.LoginValidator;

/**
 * Created by Long on 2/28/2017.
 */
@Module
public class UserModule {
    @Provides
    public LoginValidator provideLoginValidator(@NonNull Resources resources){
        return new LoginValidator();
    }
}
