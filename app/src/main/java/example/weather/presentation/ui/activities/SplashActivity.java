package example.weather.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Objects;

import example.weather.R;
import example.weather.presentation.presenters.ISplashView;
import example.weather.presentation.presenters.impl.SplashPresenter;
import example.weather.presentation.ui.activities.base.BaseActivity;


public class SplashActivity extends BaseActivity implements ISplashView {

    @InjectPresenter
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.destroy();
    }

    @Override
    public void startWeatherActivity() {
        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
        finish();
    }
}
