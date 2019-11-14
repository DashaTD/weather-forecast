package example.weather.presentation.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.weather.R;
import example.weather.domain.model.Forecast;
import example.weather.presentation.presenters.IForecastView;
import example.weather.presentation.presenters.impl.ForecastPresenter;
import example.weather.presentation.ui.activities.base.BaseActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ForecastActivity extends BaseActivity implements IForecastView, EasyPermissions.PermissionCallbacks, LocationListener {
    @BindView(R.id.textView_summary)
    TextView summary;

    @BindView(R.id.textView_temperature)
    TextView temperature;

    @BindView(R.id.textView_apparent_temperature)
    TextView apparentTemperature;

    @BindView(R.id.textView_cloud_cover)
    TextView cloudCover;

    @BindView(R.id.textView_wind_speed)
    TextView windSpeed;

    @InjectPresenter
    ForecastPresenter forecastPresenter;

    @ProvidePresenter
    ForecastPresenter provideWeatherPresenter() {
        return new ForecastPresenter(forecastRepository);
    }

    private static final int RC_COARSE_LOCATION = 100;

    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        forecastPresenter.resume();
    }

    @Override
    @AfterPermissionGranted(RC_COARSE_LOCATION)
    public void requestLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION};
            EasyPermissions.requestPermissions(this, getString(R.string.coarse_location_rationale),
                    RC_COARSE_LOCATION, perms);
        } else {
            mLocationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLocationChanged(final Location location) {
        forecastPresenter.onLocationRetrieved(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void displayForecast(Forecast forecast) {
        summary.setText(forecast.currently.summary);

        String temperatureText = getString(R.string.temperature,
                Math.round(forecast.currently.temperature));
        temperature.setText(temperatureText);

        String apparentTemperatureText = getString(R.string.apparent_temperature,
                Math.round(forecast.currently.apparentTemperature));
        apparentTemperature.setText(apparentTemperatureText);

        String cloudCoverText = getString(R.string.cloud_cover, forecast.currently.cloudCover);
        cloudCover.setText(cloudCoverText);

        String windSpeedText = getString(R.string.wind_speed, forecast.currently.windSpeed);
        windSpeed.setText(windSpeedText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        forecastPresenter.destroy();
    }
}
