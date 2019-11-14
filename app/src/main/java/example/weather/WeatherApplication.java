package example.weather;

import android.app.Application;

import example.weather.di.component.ApplicationComponent;
import example.weather.di.component.DaggerApplicationComponent;
import example.weather.di.modules.ApplicationModule;

public class WeatherApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
