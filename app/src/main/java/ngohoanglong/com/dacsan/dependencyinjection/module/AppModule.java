package ngohoanglong.com.dacsan.dependencyinjection.module;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ngohoanglong.com.dacsan.manager.AuthManager;
import ngohoanglong.com.dacsan.manager.NetworkingManager;
import ngohoanglong.com.dacsan.utils.FileUtils;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.ThreadSchedulerImpl;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Long on 2/28/2017.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    public NetworkingManager provideNetworkingManager(Context context) {
        return new NetworkingManager(context);
    }

    @Provides
    public FileUtils provideFileManager(Context context) {
        return new FileUtils(context);
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public ThreadScheduler provideThreadScheduler() {
        return new ThreadSchedulerImpl(AndroidSchedulers.mainThread(), Schedulers.io());
    }

    @Provides
    @Singleton
    public AuthManager providerAuthManager(Context context, Gson gson) {
        return new AuthManager(context, gson);
    }
}
