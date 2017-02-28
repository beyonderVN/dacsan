package ngohoanglong.com.dacsan.dependencyinjection.component;

import dagger.Subcomponent;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;

/**
 * Created by Long on 2/28/2017.
 */
@ActivityScope
@Subcomponent(modules = {PostModule.class})
public interface PostComponent {
}
