package ngohoanglong.com.dacsan.dependencyinjection.module;

import dagger.Module;
import dagger.Provides;
import ngohoanglong.com.dacsan.data.repo.UserRepo;
import ngohoanglong.com.dacsan.data.repo.UserRepoImpl;

/**
 * Created by Long on 2/28/2017.
 */
@Module
public class DataModule {
    @Provides
    public UserRepo provideUserRepo() {
        return new UserRepoImpl();
    }
}
