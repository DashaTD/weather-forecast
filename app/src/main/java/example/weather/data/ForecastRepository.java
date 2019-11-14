package example.weather.data;

import example.weather.data.db.ICacheRepository;
import example.weather.data.mapper.DarkSkyForecastMapper;
import example.weather.data.mapper.RealmForecastMapper;
import example.weather.data.network.DarkSkyApi;
import example.weather.domain.model.Currently;
import example.weather.domain.model.Forecast;
import example.weather.domain.repository.IForecastRepository;
import io.reactivex.Single;

public class ForecastRepository implements IForecastRepository {
    private static ForecastRepository instance;
    private ICacheRepository cacheRepository;
    private DarkSkyApi darkSkyApi;
    private DarkSkyForecastMapper darkSkyMapper;
    private RealmForecastMapper realmMapper;

    private ForecastRepository(ICacheRepository cacheRepository,
                               DarkSkyApi darkSkyApi) {
        this.cacheRepository = cacheRepository;
        this.darkSkyApi = darkSkyApi;
        this.darkSkyMapper = new DarkSkyForecastMapper();
        this.realmMapper = new RealmForecastMapper();
    }

    public static ForecastRepository getInstance(ICacheRepository cacheRepository, DarkSkyApi darkSkyApi) {
        if (instance == null) {
            instance = new ForecastRepository(cacheRepository, darkSkyApi);
        }
        return instance;
    }

    @Override
    public Forecast getCachedForecast() {
        return realmMapper.mapFromEntity(cacheRepository.getForecast());
    }

    @Override
    public void cacheForecast(Forecast forecast) {
        cacheRepository.insertForecast(realmMapper.mapToEntity(forecast));
    }

    @Override
    public boolean isForecastCacheEmpty() {
        return cacheRepository.getForecast() == null;
    }

    @Override
    public Single<Forecast> getRemoteForecast(double latitude, double longitude) {
        return darkSkyApi.getCurrentWeather(latitude, longitude)
                .map(it -> darkSkyMapper.mapFromEntity(it.body()));
    }

    @Override
    public Forecast getDefaultForecast() {
        return new Forecast(0, 0, getDefaultCurrently());
    }

    private Currently getDefaultCurrently() {
        return new Currently("", 0, 0,
                0, 0);
    }

    @Override
    public void close() {
        cacheRepository.close();
    }
}
