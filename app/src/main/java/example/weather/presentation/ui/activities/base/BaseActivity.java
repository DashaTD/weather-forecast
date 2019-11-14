package example.weather.presentation.ui.activities.base;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import example.weather.WeatherApplication;
import example.weather.di.component.ApplicationComponent;
import example.weather.domain.repository.IForecastRepository;

public abstract class BaseActivity extends MvpAppCompatActivity {
    @Inject
    protected IForecastRepository forecastRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((WeatherApplication) getApplication()).getApplicationComponent();
    }
}
