package example.weather.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.weather.WeatherApplication;

@Module
public class ApplicationModule {
    private final WeatherApplication application;

    public ApplicationModule(WeatherApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

}
