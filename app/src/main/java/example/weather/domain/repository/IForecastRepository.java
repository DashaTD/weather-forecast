package example.weather.domain.repository;

import example.weather.domain.model.Forecast;
import io.reactivex.Single;

public interface IForecastRepository {

    Forecast getCachedForecast();

    void cacheForecast(Forecast forecast);

    boolean isForecastCacheEmpty();

    Single<Forecast> getRemoteForecast(double latitude, double longitude);

    Forecast getDefaultForecast();

    void close();

}