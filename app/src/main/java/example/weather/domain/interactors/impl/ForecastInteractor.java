package example.weather.domain.interactors.impl;

import android.location.Location;

import java.util.concurrent.TimeUnit;

import example.weather.domain.interactors.IForecastInteractor;
import example.weather.domain.model.Forecast;
import example.weather.domain.repository.IForecastRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ForecastInteractor implements IForecastInteractor {
    private final int REQUEST_LOCATION_DELAY_HOURS = 1;

    private final int REQUEST_FORECAST_DELAY_HOURS = 1;

    private boolean isLocationScheduleRunning = false;

    private boolean isForecastScheduleRunning = false;

    private IForecastInteractor.Callback callback;

    private IForecastRepository forecastRepository;

    private CompositeDisposable compositeDisposable;


    public ForecastInteractor(Callback callback, IForecastRepository forecastRepository) {
        this.callback = callback;
        this.forecastRepository = forecastRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void run() {
        if (!forecastRepository.isForecastCacheEmpty()) {
            callback.onForecastRetrieved(getCachedForecast());
            updateForecast();
            startScheduleRequestForecast();
        } else {
            callback.onForecastRetrieved(forecastRepository.getDefaultForecast());
        }

        callback.requestLocationUpdate();
        startScheduleRequestLocation();
    }

    private void startScheduleRequestForecast() {
        if (!isForecastScheduleRunning) {
            scheduleRequestForecast();
            isForecastScheduleRunning = true;
        }
    }

    private void startScheduleRequestLocation() {
        if (!isLocationScheduleRunning) {
            scheduleRequestLocation();
            isLocationScheduleRunning = true;
        }
    }

    private void scheduleRequestForecast() {
        compositeDisposable.add(Observable.timer(REQUEST_FORECAST_DELAY_HOURS, TimeUnit.HOURS)
                .subscribeOn(Schedulers.newThread())
                .repeat()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> updateForecast())
        );
    }

    private void scheduleRequestLocation() {
        compositeDisposable.add(Observable.timer(REQUEST_LOCATION_DELAY_HOURS, TimeUnit.HOURS)
                .subscribeOn(Schedulers.newThread())
                .repeat()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> callback.requestLocationUpdate()));
    }

    @Override
    public void onLocationUpdate(Location location) {
        updateForecast(location.getLatitude(), location.getLongitude());
    }

    private void updateForecast(double latitude, double longitude) {
        compositeDisposable.add(
                forecastRepository.getRemoteForecast(latitude, longitude)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                forecast -> {
                                    forecastRepository.cacheForecast(forecast);
                                    callback.onForecastRetrieved(forecast);
                                },
                                throwable -> System.out.println(throwable.getLocalizedMessage())
                        ));
    }

    private void updateForecast() {
        Forecast forecast = getCachedForecast();
        if (forecast != null) {
            updateForecast(forecast.latitude, forecast.longitude);
        }
    }

    @Override
    public Forecast getCachedForecast() {
        return forecastRepository.isForecastCacheEmpty() ? null : forecastRepository.getCachedForecast();
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
        forecastRepository.close();
    }
}
