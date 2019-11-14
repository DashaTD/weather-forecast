package example.weather.di.component;

import javax.inject.Singleton;

import dagger.Component;
import example.weather.di.modules.ApplicationModule;
import example.weather.di.modules.CacheModule;
import example.weather.di.modules.RepositoryModule;
import example.weather.di.modules.NetworkModule;
import example.weather.presentation.ui.activities.base.BaseActivity;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, CacheModule.class,
        RepositoryModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity activity);
}