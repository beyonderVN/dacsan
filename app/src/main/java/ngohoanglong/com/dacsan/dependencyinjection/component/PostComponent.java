package ngohoanglong.com.dacsan.dependencyinjection.component;

import dagger.Subcomponent;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;
import ngohoanglong.com.dacsan.view.main.LastPostFragment;
import ngohoanglong.com.dacsan.view.main.ProductsByTypeFragment;

/**
 * Created by Long on 2/28/2017.
 */
@ActivityScope
@Subcomponent(modules = {PostModule.class})
public interface PostComponent {
    void inject(LastPostFragment lastPostFragment);

    void inject(ProductsByTypeFragment productsByTypeFragment);
}
